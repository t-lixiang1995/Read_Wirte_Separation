package com.pcitc.example.service;

import java.util.List;

import com.pcitc.example.entity.Member;

public interface MemberService {

	int insert(Member member);
	
	int save(Member member);
	
	List<Member> selectAll();
	
	String getToken(String appId);
}
