package ScheduledTask;

import GlobalUtils.Application;
import GlobalUtils.Global;
import ORM.Mapper.NcovMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration
class NcovDataTaskTest {

	@Autowired
	NcovMapper ncovMapper;

	@Test
	void insertData() throws IOException {
//		List<Ncov> data = new LinkedList<>();
//		for(int i=0;i<100;i++){
//			Ncov ncov = new Ncov();
//			ncov.setCity(i+"");
//			ncov.setCitycode(i+"");
//			ncov.setConfirmed(i+"");
//			ncov.setCountry(i+"");
//			ncov.setProvince(i+"");
//			data.add(ncov);
//		}
//		Global.Logger(this).info(ncovMapper.insertAll(data));

		//-----------------------------------------------
		URL url = new URL("http://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.csv");
		Reader reader = new InputStreamReader(new BOMInputStream(url.openStream()), StandardCharsets.UTF_8);
		CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withFirstRecordAsHeader());
		try {
			for (CSVRecord record : parser) {
				Global.Logger(this).info(record.toString());
			}
		} finally {
			parser.close();
			reader.close();
		}
	}
}