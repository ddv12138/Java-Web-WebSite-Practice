package ScheduledTask;

import GlobalUtils.Application;
import GlobalUtils.Global;
import ORM.Mapper.NcovMapper;
import ORM.POJO.Ncov;
import com.alibaba.fastjson.JSONArray;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration
class NcovDataTaskTest {

	@Autowired
	NcovMapper ncovMapper;

	@Test
	void insertData() {
		List<Ncov> data = new LinkedList<>();
		for(int i=0;i<100;i++){
			Ncov ncov = new Ncov();
			ncov.setCity(i+"");
			ncov.setCitycode(i+"");
			ncov.setConfirmed(i+"");
			ncov.setCountry(i+"");
			ncov.setProvince(i+"");
			data.add(ncov);
		}
		Global.Logger(this).info(ncovMapper.insertAll(data));
	}
}