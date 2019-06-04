package com.pcitc.example.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.pcitc.example.bean.DBContextHolder;

@Aspect
@Component
public class DataSourceAop {

	@Pointcut("!@annotation(com.pcitc.example.annotation.Master)" +
			"&&(execution(*com.pcitc.example.service..*.select*(..))" +
			"||execution(*com.pcitc.example.service..*.get*(..)))")
	public void readPointcut(){
		
	}
	
	@Pointcut("!@annotation(com.pcitc.example.annotation.Master)" +
			"||execution(*com.pcitc.example.service..*.insert*(..))" +
			"||execution(*com.pcitc.example.service..*.add*(..))" +
			"||execution(*com.pcitc.example.service..*.update*(..))" +
			"||execution(*com.pcitc.example.service..*.edit*(..))" +
			"||execution(*com.pcitc.example.service..*.delete*(..))" +
			"||execution(*com.pcitc.example.service..*.remove*(..))")
	public void writePointcut(){
		
	}
	
	@Before("readPointcut")
	public void read(){
		DBContextHolder.slave();
	}
	
	@Before("writePointcut")
	public void write(){
		DBContextHolder.master();
	}
}
