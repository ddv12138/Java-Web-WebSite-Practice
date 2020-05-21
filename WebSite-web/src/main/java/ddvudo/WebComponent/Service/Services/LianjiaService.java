package ddvudo.WebComponent.Service.Services;


import ddvudo.GlobalUtils.CommonResult;
import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.Community;
import feign.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "lianjia", configuration = LianjiaService.Config.class)
public interface LianjiaService {

	@RequestMapping(value = "/city/getCityInfo", method = RequestMethod.GET, consumes = "application/json")
	CommonResult getCityInfo(String cityName);

	@RequestMapping(value = "/city/available", method = RequestMethod.GET)
	List<City> selectAvailableCity();

	@RequestMapping(value = "/city/selectByName", method = RequestMethod.GET, consumes = "application/json")
	City selectByName(String cityName);

	@RequestMapping(value = "/community/list", method = RequestMethod.POST)
	List<Community> getCommunityByCity(@RequestBody String cityName);

	class Config {
		@Autowired
		private ObjectFactory<HttpMessageConverters> messageConverters;

		// new一个form编码器，实现支持form表单提交
//		@Bean
//		public Encoder feignFormEncoder() {
//			return new SpringFormEncoder(new SpringEncoder(messageConverters));
//		}
		// 开启Feign的日志
		@Bean
		public Logger.Level logger() {
			return Logger.Level.FULL;
		}
	}
}
