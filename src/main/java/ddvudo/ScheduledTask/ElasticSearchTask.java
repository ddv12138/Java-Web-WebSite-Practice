package ddvudo.ScheduledTask;

import com.alibaba.fastjson.JSON;
import ddvudo.GlobalUtils.Global;
import ddvudo.ORM.Mapper.EnterpriseMapper;
import ddvudo.ORM.POJO.Enterprise;
import ddvudo.ORM.POJO.EnterpriseExample;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Profile("tencentserver")
@Component
public class ElasticSearchTask {
	private static final String ELASTICSEARCH_URL = "127.0.0.1";
	private static final short ELASTICSEARCH_PORT = 9200;
	private static final short MAX_POOL_SIZE = 500;
	private static final RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
			new HttpHost(ELASTICSEARCH_URL, ELASTICSEARCH_PORT)));
	Set<IndexRequest> requestSet = new HashSet<>();
	@Autowired
	EnterpriseMapper enterpriseMapper;
	//	@Autowired
//	MyBatisCursorItemReader<Enterprise> myBatisCursorItemReader;
	@Resource
	RedisTemplate<String, String> redisTemplate;
	long count = 0;

	@Scheduled(fixedDelay = 1000L * 60L * 60L * 24L * 30L)
	public void doTask() {
		EnterpriseExample example = new EnterpriseExample();
		example.setOrderByClause("\"Id\"");
		boolean stop = false;
		for (int i = 0, j = 100; !stop; i += 100) {
			example.setOffset((long) i);
			example.setLimit(j);
			List<Enterprise> tmp = enterpriseMapper.selectByExample(example);
			if (null == tmp) {
				stop = true;
				this.handle();
			}
			for (Enterprise enterprise : tmp) {
				IndexRequest request = new IndexRequest().id(String.valueOf(enterprise.getId())).type("_doc")
						.index("enterprise");
				request.source(JSON.toJSONString(enterprise), XContentType.JSON);
				requestSet.add(request);
			}
			if (requestSet.size() >= MAX_POOL_SIZE) {
				this.handle();
			}
		}
	}

	//	@Scheduled(fixedDelay = 1000L * 60L * 60L * 24L * 30L)
//	public void doTask() {
//		EnterpriseExample example = new EnterpriseExample();
//		example.setOrderByClause("\"Id\"");
//		Map<String,Object> paramsMap = new HashMap<>();
//		paramsMap.put("orderByClause",example.getOrderByClause());
//		myBatisCursorItemReader.setParameterValues(paramsMap);
//		myBatisCursorItemReader.open(new ExecutionContext());
//		Enterprise enterprise;
//		try{
//			while ((enterprise= (Enterprise) myBatisCursorItemReader.read())!=null) {
//				IndexRequest request = new IndexRequest().id(String.valueOf(enterprise.getId())).index("enterprise");
//				request.source(JSON.toJSONString(enterprise), XContentType.JSON);
//				requestSet.add(request);
//				if (requestSet.size() == MAX_POOL_SIZE) {
//					this.handle();
//				}
//			}
//		}catch(Exception e){
//			Global.Logger(this).error(e);
//		}
//	}
	public void handle() {
		if (null == requestSet)
			return;
		BulkRequest bulkRequest = new BulkRequest();
		requestSet.forEach(bulkRequest::add);
		try {
			client.bulk(bulkRequest, RequestOptions.DEFAULT);
			count += requestSet.size();
			Global.Logger(this).info(new Date().toString() + "--累计" + count + "条");
			redisTemplate.opsForValue().set("已处理es数据累计", count + "");
		} catch (Exception e) {
			Global.Logger(this).error(e);
			e.printStackTrace();
		} finally {
			requestSet.clear();
		}
	}
}
