package WebComponent.Service.ServicesImpl;

import GlobalUtils.FileStorageUtil;
import ORM.Mapper.UploadFileMapper;
import ORM.POJO.UploadFile;
import ORM.POJO.User;
import WebComponent.Service.Services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service("FileService")
@Transactional
public class FileServiceImpl implements FileService {
	UploadFileMapper fileMapper;
	FileStorageUtil fileStorageUtil;

	public FileServiceImpl(UploadFileMapper fileMapper, FileStorageUtil fileStorageUtil) {
		this.fileMapper = fileMapper;
		this.fileStorageUtil = fileStorageUtil;
	}

	@Override
	public UploadFile saveOne(MultipartFile file, User user) {
		UploadFile uploadFile = new UploadFile();
		uploadFile.setOwner(user.getName());
		uploadFile.setOwnerid(user.getId());
		uploadFile.setCreatetime(new Date());
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String fileExtendName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String randomFileName = uuid + fileExtendName;
		uploadFile.setFilename(randomFileName);
		uploadFile.setRealname(file.getOriginalFilename());
		fileStorageUtil.saveOne(file, uploadFile, user);
		fileMapper.insertSelective(uploadFile);
		Assert.notNull(uploadFile.getId(), "文件插入出现问题，数据库插入回显id为null");
		return uploadFile;
	}

	@Override
	public UploadFile getById(Integer id) {
		return null;
	}
}
