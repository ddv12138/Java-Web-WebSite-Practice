package WebComponent.Controller;

import ORM.POJO.User;
import WebComponent.Service.Services.FileService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("uploadfile")
public class FileController {
	FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping
	public Boolean addOne(@RequestPart("file") MultipartFile file, @AuthenticationPrincipal User user) throws IOException {
		return fileService.saveOne(file, user).getId() > 0;
	}
}
