package ddvudo.GlobalUtils;

import ddvudo.ORM.POJO.UploadFile;
import ddvudo.ORM.POJO.User;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileStorageUtil {
	@Autowired
	MinioClient minioClient;

	public Boolean saveOne(MultipartFile part, UploadFile file, User user) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, InvalidArgumentException, RegionConflictException {
		if (!minioClient.bucketExists(user.getName())) {
			minioClient.makeBucket(user.getName());
		}
		minioClient.putObject(user.getName(), file.getFilename(), part.getInputStream(), part.getSize(), null, null, null);
		return true;
	}

	public String getUrl(UploadFile filedesc) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
		return minioClient.getObjectUrl(filedesc.getOwner(), filedesc.getFilename());
	}
}
