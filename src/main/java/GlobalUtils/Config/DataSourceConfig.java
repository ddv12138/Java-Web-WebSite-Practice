package GlobalUtils.Config;

import GlobalUtils.Config.Propertis.DataSourcePropertis;
import GlobalUtils.Global;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


@MapperScan("ORM.Mapper")
@ComponentScan("ORM.Mapper")
@Configuration
public class DataSourceConfig {
	@Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource dataSource(DataSourcePropertis dataSourcePropertis) {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(dataSourcePropertis.getJdbcUrl());
		dataSource.setUsername(dataSourcePropertis.getUser());
		dataSource.setPassword(dataSourcePropertis.getPassword());
		dataSource.setMaxActive(10);
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		dataSource.setInitialSize(1);
		dataSource.setDriverClassName(dataSourcePropertis.getDriverClass());
		Global.Logger(this).info("druid dataSource pool created");
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DruidDataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:ORM/Mapper/*.xml");
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
