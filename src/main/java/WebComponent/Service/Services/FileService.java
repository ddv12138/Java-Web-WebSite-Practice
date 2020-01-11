package WebComponent.Service.Services;

import ORM.POJO.UploadFile;

public interface FileService {
	Boolean saveOne();

	UploadFile getById(Integer id);
}
