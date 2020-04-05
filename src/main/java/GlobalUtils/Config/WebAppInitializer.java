package GlobalUtils.Config;

@Deprecated
public class WebAppInitializer
//		extends AbstractAnnotationConfigDispatcherServletInitializer
{
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return new Class[]{RootConfig.class, FileStorageConfig.class};
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return new Class[]{SecurityConfig.class, MethodSecurityConfig.class};
//	}

//	@Override
//	protected String[] getServletMappings() {
//		return new String[]{"/"};
//	}

//	@Override
//	protected Filter[] getServletFilters() {
//		return new Filter[]{new CharacterEncodingFilter("UTF-8")};
//	}

	/*
	 *添加自定义filter和servlet或者listener可以在这里注册，如果是注册到dispatherservlet的就不用在这里注册，直接重写 getServletFilters() 即可
	 * */
//	@Override
//	public void onStartup(ServletContext context) throws ServletException {
//		super.onStartup(context);
////		FilterRegistration.Dynamic filterRegistration = context.addFilter("encodeFilter",new EncodeFilter());
////		filterRegistration.addMappingForUrlPatterns(null,false,"/**");
//	}
}
