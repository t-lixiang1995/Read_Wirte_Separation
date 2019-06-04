package com.pcitc.example.mapper;

import java.util.List;

import com.pcitc.example.entity.Member;

public interface MemberMapper {

	int insert(Member member);
	
	List<Member> selectByExample();
}
