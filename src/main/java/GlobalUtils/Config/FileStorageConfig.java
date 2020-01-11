package GlobalUtils.Config;

import GlobalUtils.FileStorageUtil;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FileStorageConfig {
	Environment env;

	public FileStorageConfig(Environment env) {
		this.env = env;
	}

	@Bean
	public MinioClient MinioClient() throws InvalidPortException, InvalidEndpointException {
		String endpoint = env.getProperty("ddvudo.MinIO.endpoint");
		String accessKey = env.getProperty("ddvudo.MinIO.accessKey");
		String secretKey = env.getProperty("ddvudo.MinIO.secretKey");
		MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
		return minioClient;
	}

	@Bean
	public FileStorageUtil FileStorageUtil() {
		return new FileStorageUtil();
	}
}
