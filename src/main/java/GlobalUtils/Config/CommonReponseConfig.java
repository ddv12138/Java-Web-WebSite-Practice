package GlobalUtils.Config;

import GlobalUtils.CommonMethodReturnHanlder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
public class CommonReponseConfig implements ApplicationContextAware {
	@Bean
	public CommonMethodReturnHanlder CommonMethodReturnHanlder(List<HttpMessageConverter<?>> converters) {
		return new CommonMethodReturnHanlder(converters);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		RequestMappingHandlerAdapter handlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
		List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();
		handlers.add(applicationContext.getBean(CommonMethodReturnHanlder.class));
		handlers.addAll(Objects.requireNonNull(handlerAdapter.getReturnValueHandlers()));
		handlerAdapter.setReturnValueHandlers(handlers);
	}
}
