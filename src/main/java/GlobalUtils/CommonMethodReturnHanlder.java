package GlobalUtils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.util.List;

public class CommonMethodReturnHanlder extends RequestResponseBodyMethodProcessor implements HandlerMethodReturnValueHandler {
	public CommonMethodReturnHanlder(List<HttpMessageConverter<?>> converters) {
		super(converters);
	}

	public CommonMethodReturnHanlder(List<HttpMessageConverter<?>> converters, ContentNegotiationManager manager) {
		super(converters, manager);
	}

	public CommonMethodReturnHanlder(List<HttpMessageConverter<?>> converters, List<Object> requestResponseBodyAdvice) {
		super(converters, requestResponseBodyAdvice);
	}

	public CommonMethodReturnHanlder(List<HttpMessageConverter<?>> converters, ContentNegotiationManager manager, List<Object> requestResponseBodyAdvice) {
		super(converters, manager, requestResponseBodyAdvice);
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		Class<?> controllerClass = returnType.getContainingClass();
		returnType.getMethodAnnotation(ResponseBody.class);

		return controllerClass.isAnnotationPresent(RestController.class)
				|| controllerClass.isAnnotationPresent(ResponseBody.class)
				|| returnType.getMethodAnnotation(ResponseBody.class) != null;
	}
	@Override
	public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException {
		CommonResult responseInfo;
		if (returnValue instanceof CommonResult) {
			responseInfo = (CommonResult) returnValue;
		} else {
			responseInfo = new CommonResult(true, "success", returnValue);
		}

		// 标识请求是否已经在该方法内完成处理
		mavContainer.setRequestHandled(true);
		ServletServerHttpRequest inputMessage = createInputMessage(webRequest);
		ServletServerHttpResponse outputMessage = createOutputMessage(webRequest);

		writeWithMessageConverters(JSONObject.toJSONString(responseInfo), returnType, inputMessage, outputMessage);
	}

}
