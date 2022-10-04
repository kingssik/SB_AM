package com.khs.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khs.exam.demo.service.ArticleService;
import com.khs.exam.demo.util.Ut;
import com.khs.exam.demo.vo.Article;
import com.khs.exam.demo.vo.ResultData;

@Controller
public class UsrArticleController {

	// 인스턴스 변수
	@Autowired
	private ArticleService articleService;

	// 액션메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {
		ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body);
		
		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticle(id);

		return ResultData.newData(writeArticleRd, article);
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-1", "Article List", articles);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(int id) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1",Ut.f("%d번 게시물은 존재하지 않습니다.", id), id);
		}

		articleService.deleteArticle(id);

		return ResultData.from("S-1",Ut.f("%d번 게시물이 삭제되었습니다.", id), id);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Integer> doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1",Ut.f("%d번 게시물은 존재하지 않습니다.", id), id);
		}

		articleService.modifyArticle(id, title, body);

		return ResultData.from("S-1",Ut.f("%d번 게시물이 수정되었습니다.", id), id);
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), article);
	}

}