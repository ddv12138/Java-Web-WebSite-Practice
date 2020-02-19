package GlobalUtils.Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@MapperScan("ORM.Mapper")
@ComponentScan(value = "ORM.Mapper")
@Configuration
public class DataSourceConfig {
}
