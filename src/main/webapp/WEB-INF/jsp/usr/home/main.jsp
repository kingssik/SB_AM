<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN" />
<%@ include file="../common/head.jspf"%>

<style>
.searchboxa {
	/*일단 보류*/
	border: 2px solid #fcd11e;
	border-radius: 24px;
	padding: 0 45px;
	height: 50px;
	line-height: 50px;
	font-size: 16px;
	letter-spacing: 0;
	text-align: left;
	max-width: 580px;
	width: 100%;
	position: relative;
}

.fa-solid fa-magnifying-glass {
	position: absolute;
	top: 39%;
	width: 24px;
	height: 24px;
	left: 15px;
	cursor: pointer;
}

.searchbox {
	max-width: 580px;
	width: 100%;
	position: relative;
}

.searchbox .input {
	background-color: rgba(29, 192, 120, .12);
	border: 1px solid rgba(29, 192, 120, .24);
	box-shadow: 0 2px 4px 0 rgb(0 0 0/ 10%);
	padding: 14px 20px;
	border-radius: 28px;
	height: auto;
	transition: all .2s ease;
}

.searchbox .button {
	position: absolute;
	right: 20px;
	top: 50%;
	transform: translateY(-50%);
	font-size: 16px;
	background-color: inherit;
	border: unset;
	outline: none;
}

.searchbox .button .icon {
	font-size: 20px;
}

.content {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.title.is-4 {
	font-size: 1.5rem;
}

.Popup {
	color: pink;
}
</style>

<section class="mt-8">
	<div class="container mx-auto">
		<div class="content">
			<h1 class="title is-4 text-center">동물학대 멈춰</h1>
			<div class="flex">
				<img class="my-2" src="https://blog.kakaocdn.net/dn/bfb1h1/btqYxoJH5bL/VRPQ5W0Lx1MKNCglQiv4rK/img.png" width="300"
					height="600"
				/>
				<img class="my-2" src="https://www.mohenesh.com/wp-content/uploads/2021/11/Doge-meme-2.webp" width="300"
					height="600"
				/>
				<img class="my-2" src="https://image.kmib.co.kr/online_image/2021/0414/611211110015738637_1.jpg" width="300"
					height="600"
				/>
				<img class="my-2"
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSh9PT0rZgUAy59rEqsHeSkkUdZbAF4CRFMew&usqp=CAU"
					width="300" height="600"
				/>
				<img class="my-2" src="https://pbs.twimg.com/media/EqZwLI4UYAE9m2u.jpg" width="300" height="600" />
			</div>

			<div class="searchbox">
				<input class="input" maxlength="130" type="text" placeholder="찾는 질문 또는 궁금한 정보 입력" />
				<button class="button">
					<span class="icon">
						<i class="fa-solid fa-magnifying-glass"></i>
					</span>
				</button>

				<!-- 모양 참고용 -->
				<!-- <div class="search_wrapper"> -->
				<!-- <input class="input e_search_input" type="text" placeholder="배우고 싶은 지식을 입력해보세요."> -->
				<!-- <button class="button e_search_submit"> -->
				<!-- <span class="icon"> -->
				<!-- <i class="far fa-search"></i> -->
				<!-- </span> -->
				<!-- </button> -->
				<div class="search_view_cover e_search_view_cover"></div>
			</div>
		</div>
		<br />

		<span class="Popup">동물보호법</span>
	</div>
</section>
<div class="layer-bg">
	<div class="layer">
		<h2>제8조(동물학대 등의 금지)</h2>
		① 누구든지 동물에 대하여 다음 각 호의 행위를 하여서는 아니 된다.
		<br />
		② 누구든지 동물에 대하여 다음 각 호의 학대행위를 하여서는 아니 된다.
		<br />
		③ 누구든지 다음 각 호에 해당하는 동물에 대하여 포획하여 판매하거나 죽이는 행위, 판매하거나 죽일 목적으로 포획하는 행위 또는 다음 각 호에 해당하는 동물임을 알면서도 알선ㆍ구매하는 행위를 하여서는 아니 된다.
		<br />
		④ 소유자등은 동물을 유기(遺棄)하여서는 아니 된다.
		<br />
		⑤ 누구든지
		<a href="" class="hover:text-blue-400 underline">다음</a>
		각 호의 행위를 하여서는 아니 된다.
		<br />
		<br />
		출처 :
		<a class="underline" href="https://www.law.go.kr/%EB%B2%95%EB%A0%B9/%EB%8F%99%EB%AC%BC%EB%B3%B4%ED%98%B8%EB%B2%95">law.go.kr/법령/동물보호법</a>
		<button class="close-btn">CLOSE</button>
	</div>
</div>
<%@ include file="../common/foot.jspf"%>
