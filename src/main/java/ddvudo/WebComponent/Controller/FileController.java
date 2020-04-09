package ddvudo.WebComponent.Controller;

import ddvudo.ORM.POJO.User;
import ddvudo.WebComponent.Service.Services.FileService;
import io.minio.errors.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/uploadfile")
public class FileController {
	FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping
	public Boolean addOne(@RequestPart("file") MultipartFile file, @AuthenticationPrincipal User user)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException, XmlPullParserException,
			InvalidArgumentException, InvalidResponseException, ErrorResponseException, NoResponseException,
			InvalidBucketNameException, InsufficientDataException, InternalException, RegionConflictException {
		return fileService.saveOne(file, user).getId() > 0;
	}
}
