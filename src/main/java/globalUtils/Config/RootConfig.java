package globalUtils.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
@ComponentScan(basePackages = {"globalUtils.AspectJ"})
public class RootConfig {
	@Bean
	public MultipartResolver getMultipartResolver() {
		return new StandardServletMultipartResolver();
	}
}
