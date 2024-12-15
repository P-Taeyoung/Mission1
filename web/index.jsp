<%--
  Created by IntelliJ IDEA.
  User: taeyeong
  Date: 24. 12. 7.
  Time: 오후 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ page import="missionJava.WifiService" %>
<%@ page import="missionJava.WifiNearMe" %>
<%@ page import="java.util.List" %>

<html>
  <head>
    <title>mission1</title>
    <style>
      h1 {
        margin-top: 20px;
        margin-bottom: 20px;
      }
      .info {
        width: 100%;
        border: gray 1px solid ;
        border-collapse: collapse;
      }
      th {
        background-color: #04AA6D;
        border: #dddddd 2px solid ;
        color: white;
        height: 50px;
      }
      td {
        height: 50px;
        border: #dddddd 2px solid ;
        margin-left: auto;
        margin-right: auto;
      }
      tr:nth-child(2n + 1){background-color: lightgray}

    </style>
    <script>
      function setMyLocation() {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            document.getElementById('userLat').value = position.coords.latitude;
            document.getElementById('userLnt').value = position.coords.longitude;
          }, function() {
            alert("위치 정보를 가져오는 데 실패했습니다.");
          });
        } else {
          alert("이 브라우저는 위치 정보를 지원하지 않습니다.");
        }
      }

      function submitForm() {
        setMyLocation(); // 위치 가져오기
        // 잠시 대기 후 폼 제출
        setTimeout(function() {
          document.forms[0].submit(); // 첫 번째 폼 제출
        }, 100); // 100ms 대기
      }
    </script>
  </head>

  <body>
    <h1>와이파이 정보 구하기</h1>
    <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <p></p>
    <form action="" method="get">
      <label for="userLat">LAT: </label>
      <input type="text" id='userLat' name="myLat" value="<%= request.getParameter("myLat") != null ? request.getParameter("myLat") : "0.0" %>">
      <label for="userLnt">, LNT: </label>
      <input type="text" id='userLnt' name="myLnt" value="<%= request.getParameter("myLnt") != null ? request.getParameter("myLnt") : "0.0" %>">
      <button type="button" onclick="setMyLocation()">내 위치 가져오기</button>
      <button type="button" onclick="submitForm()"> 근처 WIFI 정보보기</button>
    </form>
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
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
      </tr>
      <%
        if ("GET".equalsIgnoreCase(request.getMethod())) {
          String myLat = request.getParameter("myLat");
          String myLnt = request.getParameter("myLnt");

          if (myLat != null && myLnt != null) {
            WifiService wifiService = new WifiService();
            wifiService.setMyLocation(Double.parseDouble(myLat), Double.parseDouble(myLnt));
            List<WifiNearMe> wifiNearMeList = wifiService.nearWifiInfo();
            for (WifiNearMe wifiNearMe : wifiNearMeList) { %>
      <tr>
        <td><%= wifiNearMe.getKm() %></td>
        <td><%= wifiNearMe.getMgrNo() %></td>
        <td><%= wifiNearMe.getWrdofc() %></td>
        <td><%= wifiNearMe.getMainNm() %></td>
        <td><%= wifiNearMe.getAdres1() %></td>
        <td><%= wifiNearMe.getAdres2() %></td>
        <td><%= wifiNearMe.getInstlFloor() %></td>
        <td><%= wifiNearMe.getInstlTy() %></td>
        <td><%= wifiNearMe.getInstlMby() %></td>
        <td><%= wifiNearMe.getSvcSe() %></td>
        <td><%= wifiNearMe.getCmcwr() %></td>
        <td><%= wifiNearMe.getCnstcYear() %></td>
        <td><%= wifiNearMe.getInoutDoor() %></td>
        <td><%= wifiNearMe.getRemars3() %></td>
        <td><%= wifiNearMe.getLat() %></td>
        <td><%= wifiNearMe.getLnt() %></td>
        <td><%= wifiNearMe.getDttm() %></td>
      </tr>
      <% }
      } else { %>
      <tr>
        <td colspan="17" style="text-align: center">
          위치정보를 입력한 후에 조회해 주세요.
        </td>
      </tr>
      <% }
      } %>


    </table>

  </body>
</html>
