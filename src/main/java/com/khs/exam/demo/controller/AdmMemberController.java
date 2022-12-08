package com.khs.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khs.exam.demo.service.MemberService;
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
		int withrawMembersCount = memberService.getMembersCountByStatus(authLevel, status, searchKeywordTypeCode,
				searchKeyword);
		int brokenMembersCount = memberService.getMembersCountByStatus(authLevel, status, searchKeywordTypeCode,
				searchKeyword);

		int itemsInAPage = 10;

		int pagesCount = (int) Math.ceil((double) membersCount / itemsInAPage);

		if (status.equals("탈퇴")) {
			pagesCount = (int) Math.ceil((double) withrawMembersCount / itemsInAPage);

		} else if (status.equals("활동정지")) {
			pagesCount = (int) Math.ceil((double) brokenMembersCount / itemsInAPage);

		}

		List<Member> members = memberService.getForPrintMembers(authLevel, itemsInAPage, page, searchKeywordTypeCode,
				searchKeyword);

// 탈퇴 멤버만 보기
		List<Member> withrawMembers = memberService.getWithrawMemberByStatus(authLevel, itemsInAPage, page,
				searchKeywordTypeCode, searchKeyword);
// 활동정지 멤버만 보기
		List<Member> brokenMembers = memberService.getBrokenMemberByStatus(authLevel, itemsInAPage, page,
				searchKeywordTypeCode, searchKeyword);

		model.addAttribute("authLevel", authLevel);
		model.addAttribute("searchKeywordTypeCode", searchKeywordTypeCode);
		model.addAttribute("searchKeyword", searchKeyword);

		model.addAttribute("membersCount", membersCount);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("members", members);
		model.addAttribute("withrawMembers", withrawMembers);
		model.addAttribute("brokenMembers", brokenMembers);
		model.addAttribute("status", status);

		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("page", page);

		return "adm/member/list";

	}

	@RequestMapping("adm/member/getAwayMember")
	@ResponseBody
	public String getAwayMember(@RequestParam(defaultValue = "") String ids,
			@RequestParam(defaultValue = "/adm/member/list") String replaceUri) {

		List<Integer> memberIds = new ArrayList<>();

		for (String idStr : ids.split(",")) {
			memberIds.add(Integer.parseInt(idStr));
		}

		memberService.deleteMember(memberIds);

		return rq.jsReplace("회원이 추방되었습니다", replaceUri);

	}

	@RequestMapping("adm/member/recoverMember")
	@ResponseBody
	public String recoverMember(@RequestParam(defaultValue = "") String ids,
			@RequestParam(defaultValue = "/adm/member/list") String replaceUri) {

		List<Integer> memberIds = new ArrayList<>();

		for (String idStr : ids.split(",")) {
			memberIds.add(Integer.parseInt(idStr));
		}

		memberService.recoverMember(memberIds);

		return rq.jsReplace("회원이 재가입(복구)되었습니다", replaceUri);

	}

	@RequestMapping("adm/member/breakMember")
	@ResponseBody
	public String breakMember(@RequestParam(defaultValue = "") String ids,
			@RequestParam(defaultValue = "/adm/member/list") String replaceUri) {

		List<Integer> memberIds = new ArrayList<>();

		for (String idStr : ids.split(",")) {
			memberIds.add(Integer.parseInt(idStr));
		}

		memberService.breakMember(memberIds);

		return rq.jsReplace("회원의 활동을 정지시켰습니다", replaceUri);

	}

	@RequestMapping("adm/member/breakCancelMember")
	@ResponseBody
	public String breakCancelMember(@RequestParam(defaultValue = "") String ids,
			@RequestParam(defaultValue = "/adm/member/list") String replaceUri) {

		List<Integer> memberIds = new ArrayList<>();

		for (String idStr : ids.split(",")) {
			memberIds.add(Integer.parseInt(idStr));
		}

		memberService.breakCancelMember(memberIds);

		return rq.jsReplace("회원의 활동정지를 취소했습니다", replaceUri);

	}

}
