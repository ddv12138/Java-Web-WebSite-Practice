package ddvudo.ORM.Mapper;

import ddvudo.ORM.POJO.WireGuardConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.File;
import java.util.List;

@Mapper
@Repository
public class WireGuardConfigMapper {
	@Value("${wireguard.config.dir:/etc/wireguard/}")
	private String configPath;

	public boolean SaveConfigs(List<WireGuardConfig> configs) {
		Assert.isTrue(StringUtils.isEmpty(configPath), "配置文件夹路径获取失败");
		File configDir = new File(configPath);
		Assert.isTrue(configDir.isDirectory(), "配置文件夹不存在，请检查是否有wireguard环境");
		return false;
	}
}
