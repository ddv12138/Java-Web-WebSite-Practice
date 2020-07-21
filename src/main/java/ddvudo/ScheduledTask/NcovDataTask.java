package ddvudo.ScheduledTask;

import ddvudo.GlobalUtils.Global;
import ddvudo.ORM.POJO.Ncov;
import ddvudo.Service.Services.NcovService;
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

	@Scheduled(fixedRate = 1000 * 60 * 30, initialDelay = 1 * 60 * 1000)
	public void downloadData() {
		try {
//			Global.downLoadFromUrl("https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.json", "nCoVData.json", location);
			Global.downLoadFromUrl("https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.csv", "nCoVData.csv", location);
		} catch (Exception e) {
			Global.Logger(this).error(e);
		}
		try {
			File csvFile = new File(location + "/nCoVData.csv");
			if (csvFile.exists()) {
				Reader reader = new FileReader(csvFile);
				Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
				SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
				List<Ncov> ncovList = new LinkedList<>();
				for (CSVRecord record : records) {
					Ncov ncov = new Ncov();
					ncov.setProvince(record.get("province").equals("") ? null : record.get("province"));
					ncov.setCountrycode(record.get("countryCode").equals("") ? null : record.get("countryCode"));
					ncov.setCountry(record.get("country").equals("") ? null : record.get("country"));
					ncov.setConfirmed(record.get("confirmed").equals("") ? null : record.get("confirmed"));
					ncov.setCitycode(record.get("cityCode").equals("") ? null : record.get("cityCode"));
					ncov.setCity(record.get("city").equals("") ? null : record.get("city"));
					ncov.setCured(record.get("cured").equals("") ? null : record.get("cured"));
					ncov.setDate(ymd.parse(record.get("date")));
					ncov.setDead(record.get("dead").equals("") ? null : record.get("dead"));
					ncov.setProvincecode(record.get("provinceCode").equals("") ? null : record.get("provinceCode"));
					ncov.setSuspected(record.get("suspected").equals("") ? null : record.get("suspected"));
					ncovList.add(ncov);
				}
				ncovService.insertAll(ncovList);
			}
		} catch (Exception e) {
			Global.Logger(this).error(e);
		}
	}
}
