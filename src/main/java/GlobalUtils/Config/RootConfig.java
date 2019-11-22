package GlobalUtils.Config;

import GlobalUtils.AspectJ.ControllerPointCut;
import GlobalUtils.CommonResult;
import GlobalUtils.Global;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
@ComponentScan(basePackages = {"WebComponent", "Services", "GlobalUtils"}, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DataSourceConfig.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = RootConfig.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SecurityConfig.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SecurityWebApplicationInitializer.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebAppInitializer.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebConfig.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CommonResult.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Global.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ControllerPointCut.class)
})
public class RootConfig {
	@Bean
	public MultipartResolver getMultipartResolver() {
		return new StandardServletMultipartResolver();
	}
}
