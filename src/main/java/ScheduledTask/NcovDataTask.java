package ScheduledTask;

import GlobalUtils.Global;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;

@Component
public class NcovDataTask {
	//commons-csv官方文档
	//https://commons.apache.org/proper/commons-csv/user-guide.html#Example:_Parsing_an_Excel_CSV_File
	@Scheduled(fixedRate = 60)
	public void downloadData() {
		try {
			File file = new File(ResourceUtils.getURL("classpath:ncovData\\").getPath());
			if (!file.exists()) {
				file.mkdirs();
			}
			Global.Logger(this).info(file.getAbsolutePath());
//			Global.downLoadFromUrl("https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.json", "nCoVData.json", ".\\ncovData\\");
//			Global.downLoadFromUrl("https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.csv", "nCoVData.csv", ".\\ncovData\\");
		} catch (Exception e) {
			Global.Logger(this).error(e);
		}
	}
}
