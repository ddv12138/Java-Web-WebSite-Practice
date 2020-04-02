package ScheduledTask;

import GlobalUtils.Global;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

public class NcovDataTask {
	@Scheduled(fixedDelay = 60 * 60 * 60)
	public void downloadData() {
		try {
			Global.downLoadFromUrl("https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.json", "nCoVData.json", ".\\ncovData\\");
			Global.downLoadFromUrl("https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.csv", "nCoVData.csv", ".\\ncovData\\");
		} catch (IOException e) {
			Global.Logger(this).error(e);
		}
	}
}
