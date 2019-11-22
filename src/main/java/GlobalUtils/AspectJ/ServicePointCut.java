package GlobalUtils.AspectJ;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServicePointCut extends PointCut {

//	@Around("execution(* Services.*.*(..))")
//	public Object controllerAnalysis(ProceedingJoinPoint joinPoint) throws Throwable {
//		return super.controllerAnalysis(joinPoint);
//	}
//
//	@AfterThrowing(value = "execution(* Services.*.*(..))", throwing = "throwable")
//	public void controllerException(Throwable throwable) {
//		super.controllerException(throwable);
//	}
}
