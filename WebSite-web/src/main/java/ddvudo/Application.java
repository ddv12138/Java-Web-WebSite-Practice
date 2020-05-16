package ddvudo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@EnableScheduling
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
@SpringBootApplication
public class Application {
	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}
}
