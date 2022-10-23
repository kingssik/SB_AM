<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MEMBER JOIN" />
<%@ include file="../common/head.jspf"%>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form class="table-box-type-1" method="POST" action="../member/doJoin">
			<table class="table table-zebra w-full">
				<colgroup>
					<col width="200" />
				</colgroup>

				<tbody>
					<tr>
						<th>아이디</th>
						<td>
							<input autocomplete="off" required="required" class="w-full input input-bordered  max-w-xs" type="text" 
								placeholder="아이디를 입력하세요" />
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input autocomplete="off" required="required" class="w-full input input-bordered  max-w-xs" type="text"
								placeholder="비밀번호를 입력하세요" />
						</td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td>
							<input autocomplete="off" required="required" class="w-full input input-bordered  max-w-xs" type="text" name=""
								placeholder="비밀번호 확인을 입력하세요" />
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input autocomplete="off" required="required" class="w-full input input-bordered  max-w-xs" type="text" name="name"
								placeholder="이름을 입력하세요" />
						</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td>
							<input autocomplete="off" required="required" class="w-full input input-bordered  max-w-xs" type="text" name="nickname"
								placeholder="닉네임을 입력하세요" />
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>
							<input autocomplete="off" required="required" class="w-full input input-bordered  max-w-xs" type="text" name="cellphoneNum"
								placeholder="전화번호를 입력하세요"	/>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input autocomplete="off" required="required" class="w-full input input-bordered  max-w-xs" type="text" name="email"
								placeholder="이메일을 입력하세요"	/>
						</td>
					</tr>
					<tr>
						<th></th>
						<td>
							<button class="btn btn-active btn-ghost" type="submit" value="가입">가입</button>
						</td>
					</tr>
				</tbody>

			</table>
		</form>

		<div class="btns">
			<button class="btn-text-link btn btn-active btn-ghost" type="button" onclick="history.back();">뒤로가기</button>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>