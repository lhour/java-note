package config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Java based application context configuration class.
 *
 * @author Rugal Bernstein
 * @since 0.2
 */
@Configuration
@EnableTransactionManagement
@PropertySource(
    {
        "classpath:jdbc.properties"
    })
@ComponentScan(value = "ga.rugal.ssm")
@MapperScan("ga.rugal.ssm.core.mapper")
public class ApplicationContext
{

    @Autowired
    private Environment env;

//<editor-fold defaultstate="collapsed" desc="HikariCP Datasoure Configuration" >
    @Bean(destroyMethod = "close")
    @Autowired
    public DataSource dataSource()
    {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setConnectionTestQuery("SELECT 1;");
        dataSource.setMaximumPoolSize(3);
        dataSource.setAutoCommit(false);
        //------------------------------
        return dataSource;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Mybatis sql session factory">
    @Bean
    @Autowired
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception
    {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.put("lazyLoadingEnabled", true);
        sqlSessionFactory.setConfigurationProperties(properties);
        return sqlSessionFactory.getObject();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Transaction Manager">
    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager(DataSource dataSource)
    {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        return txManager;
    }
//</editor-fold>

}
