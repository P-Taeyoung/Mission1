<%--
  Created by IntelliJ IDEA.
  User: taeyeong
  Date: 24. 12. 9.
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="missionJava.Distance"%>
<%@ page import="missionJava.MyLocation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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


    </script>
</head>
    <%
        WifiService wifiService = new WifiService();
        List<MyLocation> myLocations = wifiService.historyMyLocation();
    %>
<body>
<h1>와이파이 정보 구하기</h1>
<a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<table class = "info">
    <thead>
        <tr>
            <th>ID</th>
            <th>X-좌표</th>
            <th>Y-좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <%
            for (MyLocation myLocation : myLocations) {
            %>
            <td>
                <%=myLocation.getId()%>
            </td>
            <td>
                <%=myLocation.getMyLat()%>
            </td>
            <td>
                <%=myLocation.getMyLnt()%>
            </td>
            <td>
                <%=myLocation.getDate()%>
            </td>
            <td>
                <button>비고</button>
            </td>
            <%
                }
            %>
        </tr>
    </tbody>


</table>

</body>
</html>
