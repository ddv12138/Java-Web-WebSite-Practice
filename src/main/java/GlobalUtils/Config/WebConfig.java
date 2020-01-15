package GlobalUtils.Config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@SpringBootConfiguration
@Configuration(proxyBeanMethods = false)
@Import(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class)
@EnableConfigurationProperties({WebMvcProperties.class, ResourceProperties.class})
@EnableWebMvc
@EnableAspectJAutoProxy
//@ComponentScan(basePackages = {"WebComponent"})
//先注释，不然静态资源和主页加载有问题，研究好源码再写，源码在-WebMvcAutoConfigurationAdapter
public class WebConfig implements WebMvcConfigurer {


//	private final WebMvcProperties mvcProperties;
//
//	private final ListableBeanFactory beanFactory;
//
//
//	final ResourceHandlerRegistrationCustomizer resourceHandlerRegistrationCustomizer;
//
//
//	public WebConfig(WebMvcProperties mvcProperties,
//					 ListableBeanFactory beanFactory,
//					 ObjectProvider<ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider) {
//		this.mvcProperties = mvcProperties;
//		this.beanFactory = beanFactory;
//		this.resourceHandlerRegistrationCustomizer = resourceHandlerRegistrationCustomizerProvider.getIfAvailable();
//	}
////	@Override
////	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
////		this.messageConvertersProvider
////				.ifAvailable((customConverters) -> converters.addAll(customConverters.getConverters()));
////	}
//
//	@Override
//	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//		if (this.beanFactory.containsBean(TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME)) {
//			Object taskExecutor = this.beanFactory
//					.getBean(TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME);
//			if (taskExecutor instanceof AsyncTaskExecutor) {
//				configurer.setTaskExecutor(((AsyncTaskExecutor) taskExecutor));
//			}
//		}
//		Duration timeout = this.mvcProperties.getAsync().getRequestTimeout();
//		if (timeout != null) {
//			configurer.setDefaultTimeout(timeout.toMillis());
//		}
//	}
//
//	@Override
//	public void configurePathMatch(PathMatchConfigurer configurer) {
//		configurer.setUseSuffixPatternMatch(this.mvcProperties.getPathmatch().isUseSuffixPattern());
//		configurer.setUseRegisteredSuffixPatternMatch(
//				this.mvcProperties.getPathmatch().isUseRegisteredSuffixPattern());
//	}
//
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		WebMvcProperties.Contentnegotiation contentnegotiation = this.mvcProperties.getContentnegotiation();
//		configurer.favorPathExtension(contentnegotiation.isFavorPathExtension());
//		configurer.favorParameter(contentnegotiation.isFavorParameter());
//		if (contentnegotiation.getParameterName() != null) {
//			configurer.parameterName(contentnegotiation.getParameterName());
//		}
//		Map<String, MediaType> mediaTypes = this.mvcProperties.getContentnegotiation().getMediaTypes();
//		mediaTypes.forEach(configurer::mediaType);
//	}
//
//	@Bean
//	@ConditionalOnMissingBean
//	public InternalResourceViewResolver defaultViewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix(this.mvcProperties.getView().getPrefix());
//		resolver.setSuffix(this.mvcProperties.getView().getSuffix());
//		return resolver;
//	}
//
//	@Bean
//	@ConditionalOnBean(View.class)
//	@ConditionalOnMissingBean
//	public BeanNameViewResolver beanNameViewResolver() {
//		BeanNameViewResolver resolver = new BeanNameViewResolver();
//		resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
//		return resolver;
//	}
//
//	@Bean
//	@ConditionalOnBean(ViewResolver.class)
//	@ConditionalOnMissingBean(name = "viewResolver", value = ContentNegotiatingViewResolver.class)
//	public ContentNegotiatingViewResolver viewResolver(BeanFactory beanFactory) {
//		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//		resolver.setContentNegotiationManager(beanFactory.getBean(ContentNegotiationManager.class));
//		// ContentNegotiatingViewResolver uses all the other view resolvers to locate
//		// a view so it should have a high precedence
//		resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return resolver;
//	}
//
//	@Bean
//	@ConditionalOnMissingBean
//	@ConditionalOnProperty(prefix = "spring.mvc", name = "locale")
//	public LocaleResolver localeResolver() {
//		if (this.mvcProperties.getLocaleResolver() == WebMvcProperties.LocaleResolver.FIXED) {
//			return new FixedLocaleResolver(this.mvcProperties.getLocale());
//		}
//		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
//		localeResolver.setDefaultLocale(this.mvcProperties.getLocale());
//		return localeResolver;
//	}
//
//	@Override
//	public MessageCodesResolver getMessageCodesResolver() {
//		if (this.mvcProperties.getMessageCodesResolverFormat() != null) {
//			DefaultMessageCodesResolver resolver = new DefaultMessageCodesResolver();
//			resolver.setMessageCodeFormatter(this.mvcProperties.getMessageCodesResolverFormat());
//			return resolver;
//		}
//		return null;
//	}
//
//	@Override
//	public void addFormatters(FormatterRegistry registry) {
//		ApplicationConversionService.addBeans(registry, this.beanFactory);
//	}
//
//
//	@Bean
//	@ConditionalOnMissingBean({RequestContextListener.class, RequestContextFilter.class})
//	@ConditionalOnMissingFilterBean(RequestContextFilter.class)
//	public static RequestContextFilter requestContextFilter() {
//		return new OrderedRequestContextFilter();
//	}
//	@Bean // 配置生成模板解析器
//	@Primary
//	public ITemplateResolver templateResolver(ApplicationContext applicationContext) {
//		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//		resolver.setApplicationContext(applicationContext);
//		resolver.setPrefix("classpath:/templates/");
//		resolver.setSuffix(".html");
//		resolver.setTemplateMode(TemplateMode.HTML);
//		resolver.setCharacterEncoding("UTF-8");
//		resolver.setCacheable(false);
//		resolver.setCheckExistence(true);
//		return resolver;
//	}
//
//	@Bean // 生成模板引擎并为模板引擎注入模板解析器
//	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
//		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		templateEngine.setTemplateResolver(templateResolver);
//		templateEngine.addDialect(new SpringSecurityDialect());
//		return templateEngine;
//	}

	//	@Bean // 生成视图解析器并未解析器注入模板引擎
//	public ViewResolver viewResolver(TemplateEngine templateEngine) {
//		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//		viewResolver.setContentType("text/html");
//		viewResolver.setTemplateEngine((ISpringTemplateEngine) templateEngine);
//		viewResolver.setCache(false);
//		viewResolver.setCacheUnresolved(false);
//		viewResolver.setCharacterEncoding("UTF-8");
//		return viewResolver;
//	}

//	/**
//	 * 这里有个坑，SpringBoot2 必须重写该方法，否则静态资源无法访问
//	 * 参照 https://blog.csdn.net/zhang_Red/article/details/81739005
//	 */
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**")
//				.addResourceLocations("classpath:/META-INF/resources/")
//				.addResourceLocations("classpath:/resources/")
//				.addResourceLocations("classpath:/static/")
//				.addResourceLocations("classpath:/public/");
//		WebMvcConfigurer.super.addResourceHandlers(registry);
//	}
//
//	@Bean
//	public InternalResourceViewResolver defaultViewResolver(WebMvcProperties mvcProperties) {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix(mvcProperties.getView().getPrefix());
//		resolver.setSuffix(mvcProperties.getView().getSuffix());
//		return resolver;
//	}
//
//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		configurer.enable();
//	}

	@Bean
	public HttpMessageConverters customConverters() {
		//创建消息转换器
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		//创建配置类
		com.alibaba.fastjson.support.config.FastJsonConfig config = new com.alibaba.fastjson.support.config.FastJsonConfig();
		//返回内容的过滤
		config.setSerializerFeatures(
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullListAsEmpty
		);
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
		supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
		supportedMediaTypes.add(MediaType.APPLICATION_PDF);
		supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XML);
		supportedMediaTypes.add(MediaType.IMAGE_GIF);
		supportedMediaTypes.add(MediaType.IMAGE_JPEG);
		supportedMediaTypes.add(MediaType.IMAGE_PNG);
		supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		supportedMediaTypes.add(MediaType.TEXT_XML);
		fastConverter.setSupportedMediaTypes(supportedMediaTypes);
		fastConverter.setFastJsonConfig(config);
		HttpMessageConverter another = new HttpMessageConverter() {
			@Override
			public boolean canRead(Class clazz, MediaType mediaType) {
				return false;
			}

			@Override
			public boolean canWrite(Class clazz, MediaType mediaType) {
				return false;
			}

			@Override
			public List<MediaType> getSupportedMediaTypes() {
				return null;
			}

			@Override
			public Object read(Class clazz, HttpInputMessage inputMessage)
					throws IOException, HttpMessageNotReadableException {
				return null;
			}

			@Override
			public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage)
					throws IOException, HttpMessageNotWritableException {

			}
		};
		return new HttpMessageConverters(another);
	}

}
