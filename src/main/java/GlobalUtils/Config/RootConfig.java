package GlobalUtils.Config;

import GlobalUtils.CommonMethodReturnHanlder;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
@MapperScan("ORM.Mapper")
@ComponentScan(value = "ORM.Mapper")
@EnableCaching
@ComponentScan(basePackages = {"WebComponent", "GlobalUtils", "ScheduledTask"}, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
})
@EnableConfigurationProperties
@EnableScheduling
class RootConfig {
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory,
									 RedisCacheConfiguration redisCacheConfiguration) {
		//初始化一个RedisCacheWriter
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
		//初始化RedisCacheManager
		return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		//使用fastjson序列化
		GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
		redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
		return redisTemplate;
	}

	@Bean
	public RedisCacheConfiguration redisCacheConfiguration() {
		//JDK方式
//		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
		//FastJson方式
		FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);

		//重要的地方在于，pojo存到redis时要附加类名，以便反序列化的时候指定到特定的pojo，无论是自己写序列化器还是使用fastjson自带的
		//只要满足这一点，其实都可以，csdn上的解答全都说要自己实现序列化器那都是知其然不知其所以然
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteClassName);
		fastJsonRedisSerializer.setFastJsonConfig(fastJsonConfig);
		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
		configuration = configuration.serializeValuesWith(
				RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer))
				.entryTtl(Duration.ofDays(1));
		//设置白名单---非常重要********
        /*
        使用fastjson的时候：序列化时将class信息写入，反解析的时候，
        fastjson默认情况下会开启autoType的检查，相当于一个白名单检查，
        如果序列化信息中的类路径不在autoType中，
        反解析就会报com.alibaba.fastjson.JSONException: autoType is not support的异常
        可参考 https://blog.csdn.net/u012240455/article/details/80538540
         */
		ParserConfig.getGlobalInstance().addAccept("ORM.POJO.");

		//Jackson序列化方式
//		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
//		RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
//		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
		return configuration;
	}

	@Bean
	//springboot2中消息转换器的介绍在
	// https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-message-converters
	public HttpMessageConverters fastJsonMessageConverters(List<HttpMessageConverter<?>> converters) {
		//需要定义一个convert转换消息的对象;
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		//添加fastJson的配置信息;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		//全局时间配置
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonConfig.setCharset(StandardCharsets.UTF_8);
		//处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON);
		//在convert中添加配置信息.
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(0, fastConverter);
		return new HttpMessageConverters(true, converters);
	}

	@Bean
	public CommonMethodReturnHanlder commonMethodReturnHanlder(RequestMappingHandlerAdapter handlerAdapter, HttpMessageConverters converters) {
		List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();
		CommonMethodReturnHanlder commonMethodReturnHanlder = new CommonMethodReturnHanlder(converters.getConverters());
		handlers.add(commonMethodReturnHanlder);
		handlers.addAll(Objects.requireNonNull(handlerAdapter.getReturnValueHandlers()));
		handlerAdapter.setReturnValueHandlers(handlers);
		return commonMethodReturnHanlder;
	}
}
