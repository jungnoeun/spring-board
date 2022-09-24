package hello.board.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
@MapperScan(value = "hello.board.mapper") // 페키지 경로를 지정하여 이하 위치에있는 인터페이스들은 전부 맵퍼로 사용
// MyBatis와 HikariCP 연동을 위한 설정 클래스
public class DBConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    // HikariCP를 이용해 DB 접속, 데이터풀을 가져옴
    @Bean
    public DataSource dataSource() throws Exception{
        DataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }

    // MyBatis 설정 관련 빈, 데이터풀에서 sql문을 관리하는 공장
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // DB(Mysql)에서 읽어온 데이터를 담아올 도메인(DTO)의 패키지 경로를 설정 -> Mapper 파일에 편하게 클래스 명만 작성해서 사용
        sqlSessionFactoryBean.setTypeAliasesPackage("hello.board.domain");
        // 쿼리를 작성할 mapper 파일을 생성해 둘 경로(resources/mapper) -> 해당 경로에. xml로 끝나는 파일들을 스캔해서 설정
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml"));
        // ---------sql 관리설정, sql문을 정의한 문서 추가----------
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        // ------ 추가 끝 ------------
        return sqlSessionFactoryBean.getObject();
    }

    // 공장에서 관리하는 하나하나의 생성자를 정의, SqlSessionTemplate은 SqlSession을 구현
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
