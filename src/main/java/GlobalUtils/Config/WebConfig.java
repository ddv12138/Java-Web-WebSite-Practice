package GlobalUtils.Config;

//@SpringBootConfiguration
//@Configuration
//@Import(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class)
//@EnableConfigurationProperties({WebMvcProperties.class, ResourceProperties.class})
//@ComponentScan(basePackages = {"WebComponent"})
//先注释，不然静态资源和主页加载有问题，研究好源码再写，源码在-WebMvcAutoConfigurationAdapter
//此类自spring-boot2之后弃用，原因是spring-boot2不再适用如下配置方法,springboot2自带autoconfigurer来处理，要自定义其中的实现可参照官方文档
//如消息转换器的介绍在https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-message-converters
public class WebConfig {


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

}
