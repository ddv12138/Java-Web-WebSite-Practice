package GlobalUtils.AspectJ;

import GlobalUtils.Global;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
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
		Global.Logger().info(sb.toString());
		return obj;
	}

	@AfterThrowing(value = "execution(* WebComponent.Controller.*.*(..))", throwing = "throwable")
	public void controllerException(Throwable throwable) {
		Global.Logger().error(throwable);
	}
}
