package com.khs.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import com.khs.exam.demo.vo.Rq;

@Configuration
public class NeedLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

		Rq rq = (Rq) req.getAttribute("rq");
		if (!rq.isLogined()) {
//			resp.getWriter().append("<script>")...
			
			rq.printHistoryBack("로그인 후 이용하세요");
			return false;
		}

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}

}
