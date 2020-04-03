package ScheduledTask;

import GlobalUtils.Application;
import GlobalUtils.Global;
import ORM.Mapper.NcovMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration
class NcovDataTaskTest {

	@Autowired
	NcovMapper ncovMapper;
	@Autowired
	NcovDataTask task;

	@Test
	void insertData() throws IOException {
		task.downloadData();
	}

	@Test
	void clearTable() throws IOException {
		Global.Logger(this).info(ncovMapper.cleartable());
	}
}