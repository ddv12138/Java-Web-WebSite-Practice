package ddvudo.WebComponent.Service.Services;

import ddvudo.ORM.POJO.UploadFile;
import ddvudo.ORM.POJO.User;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface FileService {
	UploadFile saveOne(MultipartFile file, User user) throws IOException, XmlPullParserException, NoSuchAlgorithmException, RegionConflictException, InvalidKeyException, InvalidArgumentException, InvalidResponseException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InsufficientDataException, InternalException;

	String getURLById(Integer id) throws IOException, XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InsufficientDataException, InternalException;
}
