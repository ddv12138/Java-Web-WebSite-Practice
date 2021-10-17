package ddvudo.web.GlobalUtils;

import ddvudo.root.GlobalUtils.SystemCode;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.util.List;

public class CommonMethodReturnHanlder extends RequestResponseBodyMethodProcessor {

	public CommonMethodReturnHanlder(List<HttpMessageConverter<?>> converters) {
		super(converters);
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
		CommonResult responseInfo = null;
		if (returnValue instanceof CommonResult) {
			responseInfo = (CommonResult) returnValue;
		} else if (returnValue instanceof ResponseEntity) {//Swagger2的特殊处理
			ResponseEntity responseEntity = (ResponseEntity) returnValue;
			mavContainer.setRequestHandled(true);
			ServletServerHttpRequest inputMessage = createInputMessage(webRequest);
			ServletServerHttpResponse outputMessage = createOutputMessage(webRequest);
			writeWithMessageConverters(responseEntity.getBody(), returnType, inputMessage, outputMessage);
		} else {
			responseInfo = new CommonResult(SystemCode.OK, "success", returnValue);
		}

		// 标识请求是否已经在该方法内完成处理
		mavContainer.setRequestHandled(true);
		ServletServerHttpRequest inputMessage = createInputMessage(webRequest);
		ServletServerHttpResponse outputMessage = createOutputMessage(webRequest);

		writeWithMessageConverters(responseInfo, returnType, inputMessage, outputMessage);
	}

}
