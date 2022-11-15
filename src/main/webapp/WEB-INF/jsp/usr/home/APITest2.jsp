<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="API Test2" />
<%@ include file="../common/head.jspf"%>

<div id="map" style="width: 1100px; height: 600px;"></div>
<div class="msg-1"></div>
<div class="msg-2"></div>
<div style="background-color: black; width: 100px; height: 100px" onclick="removeText();"></div>
<p>
	<button onclick="setCenter()">지도 중심좌표 이동시키기</button>
	<button onclick="panTo()">지도 중심좌표 부드럽게 이동시키기</button>
</p>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=62a277ca5cdc4c086160482dc327ab3a"></script>
<script>
	const API_KEY = 'ucqK5%2BA3tKt%2B1AW5VVKW7%2F6yKOZgOjWBBtYWaf1IfN%2BcjRHqnmf5qhvH%2BwwiMZ%2FzSHYlzzrc0MLs0KIMcg3%2BmA%3D%3D';
	var Lalocation;
	var Lolocation;

	async function getData() {
		const url = 'http://apis.data.go.kr/1180000/DaejeonNationalCemetery/Burialinfo042?name=홍길동&pageNo=1&numOfRows=50&serviceKey='
				+ API_KEY;
		const response = await fetch(url);
		const data = await response.json();
		console.log("data", data);
		
		Lalocation = data.body[0].latitude;
		Lolocation = data.body[0].longitude;
		console.log(Lalocation);
		console.log(Lolocation);
		$('.msg-1').html('<div class="mt-2"> API 위도' + data.body[0].latitude + '</div>');
		
	}
	
	getData();

	var container = document.getElementById('map');
	var options = {
		center : new kakao.maps.LatLng(33.450701, 126.570667),
		level : 3
	};

	var map = new kakao.maps.Map(container, options);

	function setCenter() {            
	    // 이동할 위도 경도 위치를 생성합니다 
	    var moveLatLon = new kakao.maps.LatLng(33.452613, 126.570888);
	    
	    // 지도 중심을 이동 시킵니다
	    map.setCenter(moveLatLon);
	}
	
	const lat = 36.3701311788239;
	const lot = 127.298272866466;
	function panTo() {
		// 이동할 위도 경도 위치를 생성합니다 
		console.log("위도" + Lalocation);
		console.log("경도" + Lolocation);
	    var moveLatLon = new kakao.maps.LatLng(lat, lot);
	    $('.msg-2').html('<div class="mt-2"> TEST 위도' + lat + '</div>');
	    
	    // 지도 중심을 부드럽게 이동시킵니다
	    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
	    map.panTo(moveLatLon);            
	}
	
	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(lat, lot); 
	
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
	
	function removeText() {
		$('.msg-2').html('<div class="mt-2"> TEST 위도 : </div>');
	}
</script>



<%@ include file="../common/foot.jspf"%>