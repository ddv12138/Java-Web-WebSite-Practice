package WebComponent.Service.Services;

import ORM.POJO.UploadFile;
import ORM.POJO.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
	UploadFile saveOne(MultipartFile file, User user) throws IOException;

	UploadFile getById(Integer id);
}
