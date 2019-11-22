package GlobalUtils.AspectJ;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerPointCut extends PointCut {

	@Around("execution(* WebComponent.Controller.*.*(..))")
	public Object controllerAnalysis(ProceedingJoinPoint joinPoint) throws Throwable {
		return super.controllerAnalysis(joinPoint);
	}

	@AfterThrowing(value = "execution(* WebComponent.Controller.*.*(..))", throwing = "throwable")
	public void controllerException(Throwable throwable) {
		super.controllerException(throwable);
	}
}
