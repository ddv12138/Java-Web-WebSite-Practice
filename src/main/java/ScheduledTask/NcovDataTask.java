package ScheduledTask;

import GlobalUtils.Global;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class NcovDataTask {
	//commons-csv官方文档
	//https://commons.apache.org/proper/commons-csv/user-guide.html#Example:_Parsing_an_Excel_CSV_File
	@Value("${file.tmp.location}")
	String location;

	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void downloadData() {
		try {
			File file = new File(location);
			if (!file.exists()) {
				file.mkdirs();
			}
			Global.downLoadFromUrl("https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.json", "nCoVData.json", location);
			Global.downLoadFromUrl("https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.csv", "nCoVData.csv", location);
		} catch (Exception e) {
			Global.Logger(this).error(e);
		}
	}
}
