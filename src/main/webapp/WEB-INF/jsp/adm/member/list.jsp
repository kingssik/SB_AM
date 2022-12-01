<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="관리자 페이지 - 회원 리스트" />
<%@ include file="../common/head.jspf"%>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="flex">
			<div>
				회원 수 :
				<span class="badge">${membersCount }명</span>
			</div>
			<div class="flex-grow"></div>
			<form class="flex">

				<select data-value="${authLevel }" name="authLevel" class="select select-bordered">
					<option disabled="disabled">회원 타입</option>
					<option value="3">일반</option>
					<option value="7">관리자</option>
					<option value="0">전체</option>

				</select>

				<select data-value="${status }" name="status" class="mx-2 select select-bordered">
					<option disabled="disabled">회원상태</option>
					<option value="">전체</option>
					<option value="탈퇴">탈퇴</option>
					<option value="활동정지">활동정지</option>
				</select>

				<select data-value="${searchKeywordTypeCode }" name="searchKeywordTypeCode" class="select select-bordered">
					<option disabled="disabled">검색 타입</option>
					<option value="loginId">아이디</option>
					<option value="name">이름</option>
					<option value="nickname">닉네임</option>
					<option value="loginId,name,nickname">전체</option>
				</select>


				<input name="searchKeyword" type="text" class="ml-2 w-96 input input-borderd" placeholder="검색어 입력" maxlength="20"
					value="${param.searchKeyword }"
				/>
				<button type="submit" class="ml-2 btn btn-ghost">검색</button>
			</form>
		</div>
		<div class="table-box-type-1 mt-3">
			<table class="table table-fixed w-full">
				<colgroup>
					<col width="100" />
					<col />
					<col />
					<col />
					<col />
					<col />
				</colgroup>
				<thead>
					<tr>
						<th>
							<input type="checkbox" class="checkbox-all-member-id" />
						</th>
						<th>번호</th>
						<th>가입날짜</th>
						<th>수정날짜</th>
						<th>아이디</th>
						<th>이름</th>
						<th>닉네임</th>
						<th>상태</th>
					</tr>
				</thead>

				<c:if test="${status == '' }">
					<tbody>
						<c:forEach var="member" items="${members }">
							<tr class="hover">
								<th>
									<input type="checkbox" class="checkbox-member-id" value="${member.id }" />
								</th>
								<td>${member.id}</td>
								<td>${member.forPrintType1RegDate}</td>
								<td>${member.forPrintType1UpdateDate}</td>
								<td>${member.loginId}</td>
								<td>${member.name}</td>
								<td>${member.nickname}</td>
								<td>${member.status}</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>

				<!-- 탈퇴 멤버만 보기 -->
				<c:if test="${status == '탈퇴' }">
					<tbody>
						<c:forEach var="member" items="${withrawMembers }">
							<tr class="hover">
								<th>
									<input type="checkbox" class="checkbox-member-id" value="${member.id }" />
								</th>
								<td>${member.id}</td>
								<td>${member.forPrintType1RegDate}</td>
								<td>${member.forPrintType1UpdateDate}</td>
								<td>${member.loginId}</td>
								<td>${member.name}</td>
								<td>${member.nickname}</td>
								<td>${member.status}</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
		</div>
		<div class="page-menu mt-3 flex justify-center">
			<div class="btn-group">

				<c:set var="pageMenuLen" value="6" />
				<c:set var="startPage" value="${page - pageMenuLen >= 1 ? page- pageMenuLen : 1}" />
				<c:set var="endPage" value="${page + pageMenuLen <= pagesCount ? page + pageMenuLen : pagesCount}" />

				<c:set var="pageBaseUri" value="?boardId=${boardId }" />
				<c:set var="pageBaseUri" value="${pageBaseUri }&searchKeywordTypeCode=${param.searchKeywordTypeCode}" />
				<c:set var="pageBaseUri" value="${pageBaseUri }&searchKeyword=${param.searchKeyword}" />

				<c:if test="${startPage > 1}">
					<a class="btn btn-sm" href="${pageBaseUri }&page=1">1</a>
					<c:if test="${startPage > 2}">
						<a class="btn btn-sm btn-disabled">...</a>
					</c:if>
				</c:if>
				<c:forEach begin="${startPage }" end="${endPage }" var="i">
					<a class="btn btn-sm ${page == i ? 'btn-active' : '' }" href="${pageBaseUri }&page=${i }">${i }</a>
				</c:forEach>
				<c:if test="${endPage < pagesCount}">
					<c:if test="${endPage < pagesCount - 1}">
						<a class="btn btn-sm btn-disabled">...</a>
					</c:if>
					<a class="btn btn-sm" href="${pageBaseUri }&page=${pagesCount }">${pagesCount }</a>
				</c:if>
			</div>
		</div>
		<a href="adm/member/delete" type="button" class="btn btn-active btn-ghost">회원추방</a>
		<a href="adm/member/recover" type="button" class="btn btn-active btn-ghost">회원복구</a>
		<a href="" type="button" class="btn btn-active btn-ghost">활동정지</a>
		<select class="select select-bordered" name="">
			<option disabled="disabled">기간선택</option>
			<option value="1">3일</option>
			<option value="2">7일</option>
			<option value="3">14일</option>
		</select>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>