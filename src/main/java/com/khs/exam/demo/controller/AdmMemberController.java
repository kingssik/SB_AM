package com.khs.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khs.exam.demo.service.MemberService;
import com.khs.exam.demo.util.Ut;
import com.khs.exam.demo.vo.Member;
import com.khs.exam.demo.vo.Rq;

@Controller
public class AdmMemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private Rq rq;

	@RequestMapping("adm/member/list")
	public String showList(Model model, @RequestParam(defaultValue = "0") String authLevel,
			@RequestParam(defaultValue = "") String status,
			@RequestParam(defaultValue = "loginId,name,nickname") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "1") int page) {

		int membersCount = memberService.getMembersCount(authLevel, searchKeywordTypeCode, searchKeyword);

		int itemsInAPage = 10;
		int pagesCount = (int) Math.ceil((double) membersCount / itemsInAPage);

		List<Member> members = memberService.getForPrintMembers(authLevel, itemsInAPage, page, searchKeywordTypeCode,
				searchKeyword);

// 탈퇴 멤버만 보기
		List<Member> withrawMembers = memberService.getWithrawMemberByStatus(authLevel, itemsInAPage, page,
				searchKeywordTypeCode, searchKeyword);
		System.out.println(withrawMembers);

		model.addAttribute("authLevel", authLevel);
		model.addAttribute("searchKeywordTypeCode", searchKeywordTypeCode);
		model.addAttribute("searchKeyword", searchKeyword);

		model.addAttribute("membersCount", membersCount);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("members", members);
		model.addAttribute("withrawMembers", withrawMembers);
		model.addAttribute("status", status);

		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("page", page);

		return "adm/member/list";

	}

	@RequestMapping("adm/member/getAwayMember")
	@ResponseBody
	public String getAwayMember(int ids) {

		Member member = memberService.getMemberById(ids);

		if (member == null) {

			return rq.jsHistoryBack("회원이 존재하지 않습니다");
		}

		if (member.getStatus().contains("탈퇴")) {

			return rq.jsHistoryBack("이미 탈퇴한 회원입니다");
		}

		memberService.deleteMember(member.getId());

		return rq.jsReplace(Ut.f("%d번 회원이 추방되었습니다", member.getId()), "adm/member/list");

	}

	@RequestMapping("adm/member/recoverMember")
	@ResponseBody
	public String recoverMember(int ids) {

		Member withdrawMember = memberService.getMemberByDelstatus(ids);

		if (withdrawMember == null) {

			return rq.jsHistoryBack("가입한 회원입니다");
		}

		memberService.recoverMember(withdrawMember.getId());

		return rq.jsReplace(Ut.f("%d번 회원이 재가입(복구)되었습니다", withdrawMember.getId()), "adm/member/list");

	}

}