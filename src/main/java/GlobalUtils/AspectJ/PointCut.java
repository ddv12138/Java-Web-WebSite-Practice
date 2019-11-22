package GlobalUtils.AspectJ;

import GlobalUtils.Global;
import org.aspectj.lang.ProceedingJoinPoint;

public class PointCut {
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

	public void controllerException(Throwable throwable) {
		Global.Logger().error(throwable);
	}
}
