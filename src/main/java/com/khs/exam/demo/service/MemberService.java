package com.khs.exam.demo.service;

import org.springframework.stereotype.Service;

import com.khs.exam.demo.repository.MemberRepository;
import com.khs.exam.demo.util.Ut;
import com.khs.exam.demo.vo.Member;
import com.khs.exam.demo.vo.ResultData;

@Service
public class MemberService {
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public String join(String loginId, String loginPw, String loginPwConfirm, String name, String nickname,
			String cellphoneNum, String email) {
		// 로그인아이디 중복체크
		Member existsMember = getMemberByLoginId(loginId);

		if (existsMember != null) {
			return Ut.jsHistoryBack(Ut.f("이미 사용중인 아이디(%s)입니다", loginId));
		}

		// 이름 + 이메일 중복체크
		existsMember = getMemberByNameAndEmail(name, email);

		if (existsMember != null) {
			return Ut.jsHistoryBack(Ut.f("이미 사용중인 이름(%s)과 이메일(%s)입니다", name, email));
		}

		// 비밀번호 재확인
		boolean isSamePw = passwordCheck(loginPw, loginPwConfirm);

		if (isSamePw == false) {
			return Ut.jsHistoryBack("비밀번호를 다시 입력하세요");
		}

		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		int id = memberRepository.getLastInsertId();

		return Ut.jsReplace("회원가입이 완료되었습니다", "../member/login");
	}

	private boolean passwordCheck(String loginPw, String loginPwConfirm) {
		if (loginPw.equals(loginPwConfirm) == false) {
			return false;
		}
		return true;
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);

	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);

	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

	public ResultData modify(int id, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		memberRepository.modify(id, loginPw, name, nickname, cellphoneNum, email);
		return ResultData.from("S-1", "회원정보가 수정되었습니다");
	}
}