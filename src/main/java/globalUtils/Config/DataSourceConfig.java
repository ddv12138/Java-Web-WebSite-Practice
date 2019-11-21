package globalUtils.Config;

import com.alibaba.druid.pool.DruidDataSource;
import globalUtils.Global;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


@MapperScan("ORM.Mapper")
@PropertySource(value = "classpath:db.properties")
public class DataSourceConfig {
	@Autowired
	Environment env;

	@Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(env.getProperty("db.jdbcUrl"));
		dataSource.setUsername(env.getProperty("db.user"));
		dataSource.setPassword(env.getProperty("db.password"));
		dataSource.setMaxActive(10);
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		dataSource.setInitialSize(1);
		dataSource.setDriverClassName(env.getProperty("db.driverclass"));
		Global.Logger().info("druid dataSource pool created");
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DruidDataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:ORM/*.xml");
		sessionFactory.setMapperLocations(resources);
		return sessionFactory.getObject();
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DruidDataSource dataSource) {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(dataSource);
		return manager;
	}
}
