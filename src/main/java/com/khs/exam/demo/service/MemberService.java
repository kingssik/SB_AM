package com.khs.exam.demo.service;

import org.springframework.stereotype.Service;

import com.khs.exam.demo.repository.MemberRepository;
import com.khs.exam.demo.vo.Member;

@Service
public class MemberService {
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		Member existsMember = memberRepository.getMemberByLoginId(loginId);

		if (existsMember != null) {
			return -1;
		}

		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		return memberRepository.getLastInsertId();

	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}