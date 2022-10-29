package com.khs.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khs.exam.demo.service.ReactionPointService;
import com.khs.exam.demo.vo.ResultData;
import com.khs.exam.demo.vo.Rq;

@Controller
public class UsrReactionPointContoller {

	@Autowired
	private ReactionPointService reactionPointService;
	@Autowired
	private Rq rq;

	// 좋아요
	@RequestMapping("/usr/reactionPoint/doGoodReaction")
	@ResponseBody
	public String doGoodReaction(String relTypeCode, int relId, String replaceUri) {
		ResultData actorCanMakeReactionRd = reactionPointService
				.actorCanMakeReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		if (actorCanMakeReactionRd.isFail()) {
			return rq.jsHistoryBackOnView(actorCanMakeReactionRd.getMsg());
		}

		reactionPointService.addGoodReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

		return rq.jsReplace("좋아!", replaceUri);
	}

	// 싫어요
	@RequestMapping("/usr/reactionPoint/doBadReaction")
	@ResponseBody
	public String doBadReaction(String relTypeCode, int relId, String replaceUri) {
		ResultData actorCanMakeReactionRd = reactionPointService
				.actorCanMakeReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		if (actorCanMakeReactionRd.isFail()) {
			return rq.jsHistoryBackOnView(actorCanMakeReactionRd.getMsg());
		}

		reactionPointService.addBadReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

		return rq.jsReplace("싫어!", replaceUri);
	}

	// 좋아요 취소
	@RequestMapping("/usr/reactionPoint/doCancelGoodReaction")
	@ResponseBody
	public String doCancelGoodReaction(String relTypeCode, int relId, String replaceUri) {

		ResultData actorCanMakeReactionRd = reactionPointService.actorCanMakeReaction(rq.getLoginedMemberId(),
				relTypeCode, relId);
		if (actorCanMakeReactionRd.isSuccess()) {
			return rq.jsHistoryBackOnView(actorCanMakeReactionRd.getMsg());
		}

		reactionPointService.deleteGoodReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

		return rq.jsReplace("좋다고 한거 취소!", replaceUri);
	}

	// 싫어요 취소
	@RequestMapping("/usr/reactionPoint/doCancelBadReaction")
	@ResponseBody
	public String doCancelBadReaction(String relTypeCode, int relId, String replaceUri) {

		ResultData actorCanMakeReactionRd = reactionPointService.actorCanMakeReaction(rq.getLoginedMemberId(),
				relTypeCode, relId);

		if (actorCanMakeReactionRd.isSuccess()) {
			return rq.jsHistoryBackOnView(actorCanMakeReactionRd.getMsg());
		}

		reactionPointService.deleteBadReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

		return rq.jsReplace("싫다고 한거 취소!", replaceUri);
	}

}