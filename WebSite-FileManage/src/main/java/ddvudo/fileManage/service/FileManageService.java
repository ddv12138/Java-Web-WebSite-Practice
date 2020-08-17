package ddvudo.fileManage.service;

import java.io.File;
import java.io.InputStream;

public interface FileManageService {
	String save(File file);

	String save(InputStream inputStream);
}
