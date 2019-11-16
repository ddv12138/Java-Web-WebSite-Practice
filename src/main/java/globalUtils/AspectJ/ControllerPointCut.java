package globalUtils.AspectJ;

import globalUtils.CommonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerPointCut {

	@Around("execution(* WebComponent.Controller.*.*(..))")
	public Object controllerAnalysis(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj = joinPoint.proceed(joinPoint.getArgs());
		long end = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append("调用控制器：" + joinPoint.getSignature().getName() + ",");
		sb.append("耗时：" + (end - start) + "ms" + "");
		CommonUtils.Logger().info(sb.toString());
		return obj;
	}
//	@Before("execution(* WebComponent.Controller.*.*(..))")
//	public void controllerAnalysis2(ProceedingJoinPoint joinPoint) throws Throwable {
//		StringBuilder sb = new StringBuilder();
//		sb.append("2调用控制器：" + joinPoint.getSignature().getName() + ",");
//		CommonUtils.Logger().info(sb.toString());
//	}
}
