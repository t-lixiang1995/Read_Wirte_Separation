package com.pcitc.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.example.annotation.Master;
import com.pcitc.example.entity.Member;
import com.pcitc.example.mapper.MemberMapper;
import com.pcitc.example.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Transactional
	@Override
	public int insert(Member member){
		return memberMapper.insert(member);
	}
	
	@Master
	@Override
	public int save(Member member){
		return memberMapper.insert(member);
	}
	
	@Override
	public List<Member> selectAll(){
		return memberMapper.selectByExample();
	}
	
	@Master
	@Override
	public String getToken(String appId){
		//有些读操作必须读主数据库
		//比如，获取微信access_token，因为高峰时期主从同步可能延迟
		//这种情况下必须强制从主数据读
		return null;
	}
}
