package globalUtils.Config;

import globalUtils.CommonUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"WebComponent.Controller", "Services"})
@PropertySource(value = "classpath:jdbc.properties")
public class MainConfig {
	@Autowired
	Environment env;

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(env.getProperty("db.jdbcUrl"), env.getProperty("db.user"), env.getProperty("db.password"));
		dataSource.setDriverClassName(env.getProperty("db.driverClass"));
		return dataSource;
	}

	@Bean
	@DependsOn(value = "dataSource")
	public SqlSessionFactoryBean sqlSessionFactory(DriverManagerDataSource dataSource) {
		try {
			SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
			factoryBean.setDataSource(dataSource);
			factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			factoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:mappers/*.xml"));
			factoryBean.setTypeAliasesPackage("ORM.Mapper");
			return factoryBean;
		} catch (Exception e) {
			CommonUtils.Logger().error(e);
		}
		return null;
	}

	@Bean
	@DependsOn(value = "dataSource")
	public DataSourceTransactionManager transactionManager(DriverManagerDataSource dataSource) {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(dataSource);
		return manager;
	}

}
