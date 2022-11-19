<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN" />
<%@ include file="../common/head.jspf"%>

<style>
.searchbox {
	width: 90%;
	border: 2px solid #fcd11e;
	border-radius: 24px;
	padding: 0 45px;
	height: 50px;
	line-height: 50px;
	font-size: 16px;
	letter-spacing: 0;
	text-align: left;
}

.fa-solid fa-magnifying-glass {
	position: absolute;
	top: 39%;
	width: 24px;
	height: 24px;
	left: 15px;
	cursor: pointer;
}
</style>

<section class="mt-8">
	<div class="container mx-auto">
		<div>
			<span style="font-size: 25px;">
				<a href="">
					<i class="fa-solid fa-magnifying-glass"></i>
				</a>
			</span>
			<input class="searchbox" maxlength="130" type="text" placeholder="찾는 질문 또는 궁금한 정보 입력" />

			<br />
			<span class="Popup">팝업 예시</span>
		</div>
	</div>
</section>
<div class="layer-bg">
	<div class="layer">
		<h2>POPUP</h2>
		Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iste id odio eius nobis possimus cumque fuga voluptate
		ratione voluptatem eligendi eos repudiandae quaerat sed sequi sunt distinctio nesciunt quidem labore.
		<!-- 		<div class="toggle-btn"> -->
		<!-- 			<div></div> -->
		<!-- 			<div></div> -->
		<!-- 		</div> -->
		<button class="close-btn">CLOSE</button>
	</div>
</div>
<%@ include file="../common/foot.jspf"%>