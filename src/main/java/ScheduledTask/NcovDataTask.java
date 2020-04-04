package ScheduledTask;

import GlobalUtils.Global;
import ORM.POJO.Ncov;
import WebComponent.Service.Services.NcovService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Component
@Profile("server")
public class NcovDataTask {
	//commons-csv官方文档
	//https://commons.apache.org/proper/commons-csv/user-guide.html#Example:_Parsing_an_Excel_CSV_File
	@Value("${file.tmp.location}")
	String location;

	NcovService ncovService;
	RedisTemplate redisTemplate;

	public NcovDataTask(NcovService ncovService, RedisTemplate redisTemplate) {
		this.ncovService = ncovService;
		this.redisTemplate = redisTemplate;
	}

	@Scheduled(fixedRate = 1000 * 60 * 30, initialDelay = 6 * 60 * 1000)
	public void downloadData() {
		try {
			Global.downLoadFromUrl("https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.json", "nCoVData.json", location);
			Global.downLoadFromUrl("https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.csv", "nCoVData.csv", location);
			File csvFile = new File(location + "/nCoVData.csv");
			if (csvFile.exists()) {
				Reader reader = new FileReader(csvFile);
				Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
				SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
				List<Ncov> ncovList = new LinkedList<>();
				for (CSVRecord record : records) {
					Ncov ncov = new Ncov();
					ncov.setProvince(record.get("province"));
					ncov.setCountrycode(record.get("countryCode"));
					ncov.setCountry(record.get("country"));
					ncov.setConfirmed(record.get("confirmed"));
					ncov.setCitycode(record.get("cityCode"));
					ncov.setCity(record.get("city"));
					ncov.setCured(record.get("cured"));
					ncov.setDate(ymd.parse(record.get("date")));
					ncov.setDead(record.get("dead"));
					ncov.setProvincecode(record.get("provinceCode"));
					ncov.setSuspected(record.get("suspected"));
					ncovList.add(ncov);
				}
				ncovService.cleartable();
				int pageSize = 100;
				int pageCount = (int) Math.ceil(ncovList.size() / (double) pageSize);
				for (int i = 0; i < pageCount; i++) {
					int endIndex = (i + 1) * pageSize > ncovList.size() ? ncovList.size() - 1 : (i + 1) * pageSize;
					ncovService.insertAll(ncovList.subList(i * pageSize, endIndex));
				}
				ncovService.setLastUpdateTime();
			}
		} catch (Exception e) {
			Global.Logger(this).error(e);
		}
	}
}
