package com.example.gc_coffee;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("com.example.gc_coffee.repository")
public class GcCoffeeApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(GcCoffeeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource( dataSource );
//sqlSessionFactoryBean.setMapperLocations( ctx.getResource( "classpath:/mappers/OrderRepository.xml" ) );

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        SqlSession sqlSession = sqlSessionFactory.openSession( true );

        sqlSession.close();
    }
}