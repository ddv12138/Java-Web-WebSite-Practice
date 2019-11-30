package GlobalUtils.Config;

import GlobalUtils.CommonResult;
import GlobalUtils.Global;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching
@ComponentScan(basePackages = {"WebComponent", "GlobalUtils"}, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DataSourceConfig.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = RootConfig.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SecurityConfig.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebAppInitializer.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebConfig.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CommonResult.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Global.class)
})
public class RootConfig {
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		//初始化一个RedisCacheWriter
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
		//序列化方式1
		//设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下(4行)注释代码为默认实现
//      ClassLoader loader = this.getClass().getClassLoader();
//      JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
//      RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
//      RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
		//序列化方式1---另一种实现方式
		//RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();//该语句相当于序列化方式1

		//序列化方式2
		//Jackson2JsonRedisSerializer serializer=new Jackson2JsonRedisSerializer(Object.class);
		//RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
		//RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);

		//序列化方式3  JSONObject
		FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
		RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer);
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
		//设置过期时间 30天
		defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofDays(30));
		//初始化RedisCacheManager
		RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
		//设置白名单---非常重要********
        /*
        使用fastjson的时候：序列化时将class信息写入，反解析的时候，
        fastjson默认情况下会开启autoType的检查，相当于一个白名单检查，
        如果序列化信息中的类路径不在autoType中，
        反解析就会报com.alibaba.fastjson.JSONException: autoType is not support的异常
        可参考 https://blog.csdn.net/u012240455/article/details/80538540
         */
//		ParserConfig.getGlobalInstance().addAccept("ORM.POJO.");
		return cacheManager;
	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("188.131.157.4", 6379);
		configuration.setPassword(RedisPassword.of("liukang951006"));
		JedisConnectionFactory factory = new JedisConnectionFactory(configuration);
		factory.afterPropertiesSet();
		return factory;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory factory) {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		//使用fastjson序列化
		FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
		// value值的序列化采用fastJsonRedisSerializer
		template.setValueSerializer(fastJsonRedisSerializer);
		template.setHashValueSerializer(fastJsonRedisSerializer);
		// key的序列化采用StringRedisSerializer
		template.setKeySerializer(fastJsonRedisSerializer);
		template.setHashKeySerializer(fastJsonRedisSerializer);
		template.setConnectionFactory(factory);
		return template;
	}

}
