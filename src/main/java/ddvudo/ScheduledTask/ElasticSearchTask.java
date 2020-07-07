package ddvudo.ScheduledTask;

import com.alibaba.fastjson.JSON;
import ddvudo.GlobalUtils.Global;
import ddvudo.ORM.Mapper.CityMapper;
import ddvudo.ORM.Mapper.EnterpriseMapper;
import ddvudo.ORM.POJO.Enterprise;
import ddvudo.ORM.POJO.EnterpriseExample;
import org.apache.http.HttpHost;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Profile("server")
@Component
public class ElasticSearchTask {
	private static final String ELASTICSEARCH_URL = "127.0.0.1";
	private static final short ELASTICSEARCH_PORT = 9200;
	private static final RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
			new HttpHost(ELASTICSEARCH_URL, ELASTICSEARCH_PORT)));
	EnterpriseMapper enterpriseMapper;
	static RedisTemplate redisTemplate = null;
	CityMapper cityMapper;

	public ElasticSearchTask(EnterpriseMapper enterpriseMapper,
							 RedisTemplate redisTemplate, CityMapper cityMapper) {
		this.enterpriseMapper = enterpriseMapper;
		this.cityMapper = cityMapper;
		ElasticSearchTask.redisTemplate = redisTemplate;
	}

	@Scheduled(fixedDelay = 1000L * 60L * 60L * 24L * 30L)
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void doTask() {
		EnterpriseExample example = new EnterpriseExample();
//		example.setOrderByClause("\"Id\"");
		myResultHandler<Enterprise> resultHandler = new myResultHandler<>();
		Global.Logger(this).info(cityMapper.selectByName("深圳").toString());
		Global.Logger(this).info(enterpriseMapper.selectByPrimaryKey("1").toString());
		enterpriseMapper.selectByExample_Map_Forward(example, resultHandler);
//		resultHandler.handle();
//		client.close();
	}

	static class myResultHandler<E> implements ResultHandler<E> {
		private final short MAX_POOL_SIZE = 500;
		private final Set<IndexRequest> requestSet = new HashSet<>();
		int count = 0;

		@Override
		public void handleResult(ResultContext resultContext) {
			Enterprise tmp = (Enterprise) resultContext.getResultObject();
			IndexRequest request = new IndexRequest().id(String.valueOf(tmp.getId())).index("enterprise");
			request.source(JSON.toJSONString(tmp), XContentType.JSON);
			requestSet.add(request);
			if (requestSet.size() == MAX_POOL_SIZE) {
				this.handle();
			}
		}

		private void handle() {
			if (requestSet.size() == 0)
				return;
			BulkRequest bulkRequest = new BulkRequest();
			requestSet.forEach(bulkRequest::add);
			try {
				client.bulk(bulkRequest, RequestOptions.DEFAULT);
				count += MAX_POOL_SIZE;
				System.out.println(new Date().toString() + "--累计" + count + "条");
				redisTemplate.opsForValue().set("已处理es数据累计", (long) count);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				requestSet.clear();
			}
		}
	}
}
