package com.pcitc.example.config;

import java.util.HashMap;
import java.util.Map;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pcitc.example.bean.MyRoutingDataSource;
import com.pcitc.example.enums.DBTypeEnum;

/**
 * 
 * 项目名称： security-service
 * 包名称：   com.sinopec.smcc.notice.controller
 * 类名称：   NoticeController
 * 类描述：   安全通报控制类
 * 创建人：   lixiang
 * 创建时间： 2018年3月23日 下午6:07:53
 * 修改人： 
 * 修改时间:
 * 修改备注:
 */
@Configuration
public class DtaSourceConfig {

	@Bean
	@ConfigurationProperties("spring.datasource.master")
	public DataSource masterDataSource(){
		return (DataSource) DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource.slave1")
	public DataSource slave1DataSource(){
		return (DataSource) DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource.slave2")
	public DataSource slave2DataSource(){
		return (DataSource) DataSourceBuilder.create().build();
	}
	
	@Bean
	public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
			@Qualifier("slave1DataSource") DataSource slave1DataSource,@Qualifier("slave2DataSource") DataSource slave2DataSource){
		Map<Object, Object> targetDataSource = new HashMap<>();
		targetDataSource.put(DBTypeEnum.MASTER, masterDataSource);
		targetDataSource.put(DBTypeEnum.SLAVE1, slave1DataSource);
		targetDataSource.put(DBTypeEnum.SLAVE2, slave2DataSource);
		MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
		myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
		myRoutingDataSource.setTargetDataSources(targetDataSource);
		return (DataSource) myRoutingDataSource;
	}
}
