package ddvudo.root.GlobalUtils.Config;

import ddvudo.root.GlobalUtils.Config.Propertis.MinIOPropertis;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {

	@Bean
	public MinioClient MinioClient(MinIOPropertis minIOPropertis) throws InvalidPortException, InvalidEndpointException {
		MinioClient minioClient = new MinioClient(minIOPropertis.getEndPoint(), minIOPropertis.getAccessKey(), minIOPropertis.getSecretKey());
		return minioClient;
	}
}
