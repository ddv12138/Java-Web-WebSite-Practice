package ddvudo.GlobalUtils.Config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching
class CacheConfig
		extends CachingConfigurerSupport {
	final
	RedisConnectionFactory redisConnectionFactory;

	public CacheConfig(RedisConnectionFactory redisConnectionFactory) {
		this.redisConnectionFactory = redisConnectionFactory;
	}

	@Override
	public CacheManager cacheManager() {
		//初始化一个RedisCacheWriter
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
		//初始化RedisCacheManager
		return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration());
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
		ParserConfig.getGlobalInstance().addAccept("ddvudo.ORM.POJO.");

		//Jackson序列化方式
//		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
//		RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
//		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
		return configuration;
	}
}
