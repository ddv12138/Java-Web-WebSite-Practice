package WebComponent.Service.ServicesImpl;

import ORM.Mapper.NcovMapper;
import ORM.POJO.Ncov;
import WebComponent.Service.Services.NcovService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("NcovService")
@Transactional
public class NcovServiceImpl implements NcovService {
	NcovMapper ncovMapper;
	RedisTemplate<String, String> redisTemplate;
	String lastUpdateTimeKey = "lastncovupdatetime";
	SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public NcovServiceImpl(NcovMapper ncovMapper, RedisTemplate<String, String> redisTemplate) {
		this.ncovMapper = ncovMapper;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public int insertAll(List<Ncov> ncovList) {
		long t1 = System.currentTimeMillis();
		int pageSize = 500;
		int pageCount = (int) Math.ceil(ncovList.size() / (double) pageSize);
		for (int i = 0; i < pageCount; i++) {
			int endIndex = (i + 1) * pageSize > ncovList.size() ? ncovList.size() - 1 : (i + 1) * pageSize;
			ncovMapper.insertAll(ncovList.subList(i * pageSize, endIndex));
		}
		long t2 = System.currentTimeMillis();
		redisTemplate.opsForValue().set("timeuse", String.valueOf(t2 - t1));
		return ncovList.size();
	}

	@Override
	public int cleartable() {
		return ncovMapper.cleartable();
	}

	@Override
	public void setLastUpdateTime() {
		redisTemplate.opsForValue().set(lastUpdateTimeKey, ymdhms.format(new Date()));
	}

	@Override
	public Date getLastUpdateTime() throws ParseException {
		return ymdhms.parse(redisTemplate.opsForValue().get(lastUpdateTimeKey));
	}

	@Override
	public int insert(Ncov ncov) {
		return ncovMapper.insertSelective(ncov);
	}
}
