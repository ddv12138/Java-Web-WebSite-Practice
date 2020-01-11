package WebComponent.Controller;

import ORM.Mapper.UploadFileMapper;
import ORM.POJO.UploadFile;
import ORM.POJO.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("uploadfile")
public class FileController {
	UploadFileMapper fileMapper;

	public FileController(UploadFileMapper fileMapper) {
		this.fileMapper = fileMapper;
	}

	@PostMapping
	public Boolean addOne(@RequestPart("file") MultipartFile file, @AuthenticationPrincipal User user) throws IOException {
		UploadFile uploadFile = new UploadFile();
		uploadFile.setOwner(user.getName());
		uploadFile.setOwnerid(user.getId());
		uploadFile.setCreatetime(new Date());
		uploadFile.setFilename(file.getOriginalFilename());
		uploadFile.setFilecontent(file.getBytes());
		fileMapper.insertSelective(uploadFile);
		return true;
	}
}
