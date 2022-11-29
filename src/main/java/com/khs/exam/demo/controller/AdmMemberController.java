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
			@RequestParam(defaultValue = "loginId,name,nickname") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "1") int page) {

		int membersCount = memberService.getMembersCount(authLevel, searchKeywordTypeCode, searchKeyword);

		int itemsInAPage = 10;
		int pagesCount = (int) Math.ceil((double) membersCount / itemsInAPage);

		List<Member> members = memberService.getForPrintMembers(authLevel, itemsInAPage, page, searchKeywordTypeCode,
				searchKeyword);

		model.addAttribute("authLevel", authLevel);
		model.addAttribute("searchKeywordTypeCode", searchKeywordTypeCode);
		model.addAttribute("searchKeyword", searchKeyword);

		model.addAttribute("membersCount", membersCount);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("members", members);

		return "adm/member/list";

	}

	@RequestMapping("adm/member/delete")
	@ResponseBody
	public String doDelete(int id) {

		Member member = memberService.getMemberById(id);

		if (member == null) {

			return rq.jsHistoryBack("회원이 존재하지 않습니다");
		}

		memberService.deleteMember(member.getId());

		return rq.jsReplace(Ut.f("%d번 회원이 추방되었습니다", member.getId()), "adm/member/list");

	}

	@RequestMapping("adm/member/recover")
	@ResponseBody
	public String doRecover(Model model) {

		Member withdrawMember = memberService.getMemberByDelstatus();

		if (withdrawMember == null) {

			return rq.jsHistoryBack("회원이 존재하지 않습니다");
		}

		model.addAttribute("withdrawMember", withdrawMember);

		memberService.recoverMember(withdrawMember.getId());

		return rq.jsReplace(Ut.f("%d번 회원이 복구되었습니다", withdrawMember.getId()), "adm/member/list");

	}
}