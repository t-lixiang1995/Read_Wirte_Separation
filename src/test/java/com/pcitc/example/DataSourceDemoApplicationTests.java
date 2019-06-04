package com.pcitc.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcitc.example.entity.Member;
import com.pcitc.example.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceDemoApplicationTests {

	@Autowired
	private MemberService memberService;
	
	@Test
	public void testWrite(){
		Member member = new Member();
		member.setName("zhangsan");
		memberService.insert(member);
	}
	
	@Test
	public void testRead(){
		for(int i=0;i<4;i++){
			memberService.selectAll();
		}
	}
	
	@Test
	public void testSave(){
		Member member = new Member();
		member.setName("wangwu");
		memberService.save(member);
	}
	
	@Test
	public void testReadFromMaster(){
		memberService.getToken("1234");
	}
}
