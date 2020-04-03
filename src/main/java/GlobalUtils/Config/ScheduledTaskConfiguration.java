package GlobalUtils.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;

@Configuration
@EnableScheduling
public class ScheduledTaskConfiguration implements SchedulingConfigurer {
	/**
	 * Callback allowing a {@link TaskScheduler
	 * TaskScheduler} and specific {@link Task Task}
	 * instances to be registered against the given the {@link ScheduledTaskRegistrar}
	 *
	 * @param taskRegistrar the registrar to be configured.
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(2);
		taskScheduler.initialize();
		taskRegistrar.setTaskScheduler(taskScheduler);
	}
}
