package GlobalUtils.Config;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@SpringBootConfiguration
//@EnableWebMvc
//@EnableAspectJAutoProxy
//@ComponentScan(basePackages = {"WebComponent"})
//@AutoConfigureAfter(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class)
//先注释，不然静态资源和主页加载有问题，研究好源码再写，源码在-WebMvcAutoConfigurationAdapter
//此类自spring-boot2之后弃用，原因是spring-boot2不再适用如下配置方法,springboot2自带autoconfigurer来处理，要自定义其中的实现可参照官方文档
//如消息转换器的介绍在
//https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-message-converters
//public class WebConfig implements WebMvcConfigurer {
//@Configuration
@Deprecated
public class WebConfig implements WebMvcConfigurer {

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**")
//				.addResourceLocations("classpath:/META-INF/resources/")
//				.addResourceLocations("classpath:/resources/")
//				.addResourceLocations("classpath:/static/")
//				.addResourceLocations("classpath:/public/");
//		WebMvcConfigurer.super.addResourceHandlers(registry);
//	}

	//	@Bean
//	public InternalResourceViewResolver viewResolver(WebMvcProperties mvcProperties) {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix(mvcProperties.getView().getPrefix());
//		resolver.setSuffix(mvcProperties.getView().getSuffix());
//		return resolver;
//	}
}
