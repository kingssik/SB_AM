package com.khs.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khs.exam.demo.repository.ReactionPointRepository;

@Service
public class ReactionPointService {

	@Autowired
	private ReactionPointRepository reactionPointRepository;

	public boolean actorCanMakeReaction(int actorId, String relTypeCode, int relId) {
		if (actorId == 0) {
			return false;
		}
		return reactionPointRepository.getSumReactionPointByMemberId(actorId, relTypeCode, relId) == 0;
	}

}
