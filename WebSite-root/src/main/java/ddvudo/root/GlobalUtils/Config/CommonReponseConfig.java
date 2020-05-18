package ddvudo.root.GlobalUtils.Config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import ddvudo.root.GlobalUtils.CommonMethodReturnHanlder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
public class CommonReponseConfig {
	@Bean
	public CommonMethodReturnHanlder CommonMethodReturnHanlder(RequestMappingHandlerAdapter requestMappingHandlerAdapter, List<HttpMessageConverter<?>> converters) {
		CommonMethodReturnHanlder commonMethodReturnHanlder = new CommonMethodReturnHanlder(converters);
		List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();
		handlers.add(commonMethodReturnHanlder);
		handlers.addAll(Objects.requireNonNull(requestMappingHandlerAdapter.getReturnValueHandlers()));
		requestMappingHandlerAdapter.setReturnValueHandlers(handlers);
		return commonMethodReturnHanlder;
	}

	@Bean
	//springboot2中消息转换器的介绍在
	// https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-message-converters
	public HttpMessageConverters fastJsonMessageConverters(List<HttpMessageConverter<?>> converters) {
		//需要定义一个convert转换消息的对象;
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		//添加fastJson的配置信息;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		//全局时间配置
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonConfig.setCharset(StandardCharsets.UTF_8);
		//处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON);
		//在convert中添加配置信息.
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(0, fastConverter);
		return new HttpMessageConverters(true, converters);
	}
}
