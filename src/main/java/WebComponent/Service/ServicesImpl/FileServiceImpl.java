package WebComponent.Service.ServicesImpl;

import ORM.Mapper.UploadFileMapper;
import WebComponent.Service.Services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("FileService")
@Transactional
public class FileServiceImpl implements FileService {
	UploadFileMapper fileMapper;

	public FileServiceImpl(UploadFileMapper fileMapper) {
		this.fileMapper = fileMapper;
	}

}
