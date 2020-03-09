package GlobalUtils.Config;

//@SpringBootConfiguration
//@EnableWebMvc
//@EnableAspectJAutoProxy
//@ComponentScan(basePackages = {"WebComponent"})
//@AutoConfigureAfter(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class)
//先注释，不然静态资源和主页加载有问题，研究好源码再写，源码在-WebMvcAutoConfigurationAdapter
//此类自spring-boot2之后弃用，原因是spring-boot2不再适用如下配置方法,springboot2自带autoconfigurer来处理，要自定义其中的实现可参照官方文档
//如消息转换器的介绍在https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-message-converters
//public class WebConfig implements WebMvcConfigurer {
public class WebConfig {

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
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		//创建消息转换器
//		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//		//创建配置类
//		com.alibaba.fastjson.support.config.FastJsonConfig config = new com.alibaba.fastjson.support.config.FastJsonConfig();
//		//返回内容的过滤
//		config.setSerializerFeatures(
//				SerializerFeature.DisableCircularReferenceDetect,
//				SerializerFeature.WriteMapNullValue,
//				SerializerFeature.WriteNullListAsEmpty
//		);
//		List<MediaType> supportedMediaTypes = new ArrayList<>();
//		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
//		supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
//		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
//		supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
//		supportedMediaTypes.add(MediaType.APPLICATION_PDF);
//		supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
//		supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
//		supportedMediaTypes.add(MediaType.APPLICATION_XML);
//		supportedMediaTypes.add(MediaType.IMAGE_GIF);
//		supportedMediaTypes.add(MediaType.IMAGE_JPEG);
//		supportedMediaTypes.add(MediaType.IMAGE_PNG);
//		supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
//		supportedMediaTypes.add(MediaType.TEXT_HTML);
//		supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
//		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
//		supportedMediaTypes.add(MediaType.TEXT_XML);
//		fastConverter.setSupportedMediaTypes(supportedMediaTypes);
//		fastConverter.setFastJsonConfig(config);
//		//将fastjson添加到视图消息转换器列表内
//		converters.add(fastConverter);
//	}


}
