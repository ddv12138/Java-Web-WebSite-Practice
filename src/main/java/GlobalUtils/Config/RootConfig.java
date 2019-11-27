package GlobalUtils.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class RootConfig {
	@Bean
	public MultipartResolver getMultipartResolver() {
		return new StandardServletMultipartResolver();
	}
}
