<%--
  Created by IntelliJ IDEA.
  User: taeyeong
  Date: 24. 12. 7.
  Time: 오후 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="missionJava.Distance"%>
<%@ page import="missionJava.WifiService" %>

<html>
  <head>
    <title>zerobase_Mission1</title>
    <style>
      h1 {
        margin-top: 20px;
        margin-bottom: 20px;
      }
      th {
        background-color: #04AA6D;
        color: white;
        height: 50px;
        text-align: center;
      }
    </style>
    <script>
      function getMyLocation() {

        function success(pos) { // 위치 정보를 가져오는데 성공했을 때 호출되는 콜백 함수 (pos : 위치 정보 객체)
          const lat = document.getElementById('myLat');
          lat.value = pos.coords.latitude;
          const lnt = document.getElementById('myLnt');
          lnt.value = pos.coords.longitude;
        }
        function fail(err) { // 위치 정보를 가져오는데 실패했을 때 호출되는 콜백 함수
          alert('현위치를 찾을 수 없습니다.');
        }

        navigator.geolocation.getCurrentPosition(success, fail);
      }

      function setLocation(myLat, myLnt) {
        <%
            WifiService wifiService = new WifiService();
        %>
      }


    </script>
  </head>
  <body>
    <h1>와이파이 정보 구하기</h1>
    <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <table>
      <tr>
        <td>LAT:
          <input id = 'myLat' value="0.0">
        </td>
        <td>
          , LNT:
          <input id = 'myLnt' value="0.0">
        </td>
        <td><button onclick="getMyLocation()">내 위치 가져오기</button></td>
        <td><button>근처 와이파이 정보 보기</button></td>
      </tr>
    </table>
    <table class = "info">
      <tr>
        <th>거리(km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
      </tr>
      <tr>
        <td>

        </td>
      </tr>

    </table>

  </body>
</html>
