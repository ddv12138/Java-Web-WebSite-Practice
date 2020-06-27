package ddvudo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaServer
@EnableConfigServer
@Configuration
@EnableDiscoveryClient
public class EurekaApplication {
	public static void main(String... args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
}
