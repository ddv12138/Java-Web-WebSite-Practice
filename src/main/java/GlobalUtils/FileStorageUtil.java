package GlobalUtils;

import ORM.POJO.UploadFile;
import ORM.POJO.User;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class FileStorageUtil {
	@Autowired
	MinioClient minioClient;

	public Boolean saveOne(MultipartFile part, UploadFile file, User user) {
		try {
			if (!minioClient.bucketExists(user.getName())) {
				minioClient.makeBucket(user.getName());
			}
			minioClient.putObject(user.getName(), file.getFilename(), part.getInputStream(), part.getSize(), null, null, null);
			return true;
		} catch (Exception e) {
			Global.Logger(this).error(e);
			e.printStackTrace();
		}
		return false;
	}
}
