package GlobalUtils.AspectJ;

import GlobalUtils.Global;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointCut {

	@Around("execution(* WebComponent.Controller.*.*(..))")
	public Object controllerAnalysis(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj = joinPoint.proceed(joinPoint.getArgs());
		long end = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append("调用Controller：" + joinPoint.getSignature().getName() + ",");
		sb.append("耗时：" + (end - start) + "ms" + "");
		Global.Logger().info(sb.toString());
		return obj;
	}

	@AfterThrowing(value = "execution(* ORM.Mapper.*.*(..))", throwing = "throwable")
	public void controllerException(Throwable throwable) throws Throwable {
		Global.Logger().error(throwable);
		throw throwable;
	}
}
