package ddvudo.web.utils.aspectj;

import ddvudo.web.utils.Global;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointCut {

	@Around("execution(* ddvudo.web.controller.*.*(..))")
	public Object controllerAnalysis(ProceedingJoinPoint joinPoint) throws Throwable {
		return processPointCut(joinPoint, "controller");
	}

	private Object processPointCut(ProceedingJoinPoint joinPoint, String cutType) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj = joinPoint.proceed(joinPoint.getArgs());
		long end = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append("调用" + cutType + ":" + joinPoint.getSignature().getName() + ",");
		sb.append("耗时：" + (end - start) + "ms" + "");
		Global.Logger(joinPoint.getThis()).info(sb.toString());
		return obj;
	}
}
