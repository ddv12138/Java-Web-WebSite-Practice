package GlobalUtils.Config;

import GlobalUtils.CommonResult;
import GlobalUtils.Global;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

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
	public SimpleCacheManager cacheManager(RedisTemplate template) {
		SimpleCacheManager manager = new SimpleCacheManager();
		RedisCache cache = new RedisCache();
	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.afterPropertiesSet();
		return factory;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory factory) {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.afterPropertiesSet();
		return template;
	}

}
