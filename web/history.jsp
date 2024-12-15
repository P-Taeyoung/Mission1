<%--
  Created by IntelliJ IDEA.
  User: taeyeong
  Date: 24. 12. 9.
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            text-align: center;
        }
        td{
            height: 50px;
            border: #dddddd 2px solid ;

        }
        tr:nth-child(2n + 1){background-color: lightgray}
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
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            String id = request.getParameter("ID");
            if (id != null) {
                wifiService.removeHistory(Integer.parseInt(id));
            }
        }
    %>
<body>
<h1>와이파이 정보 구하기</h1>
<a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<table class = "info">
        <tr>
            <th>ID</th>
            <th>X-좌표</th>
            <th>Y-좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>

            <%
            for (MyLocation myLocation : myLocations) {
            %>
    <tr>
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
        <form action="" method="get">
            <input type="hidden" name="ID" value="<%=myLocation.getId()%>">
            <td style="text-align: center">
                <button type="submit">삭제</button>
            </td>
        </form>

    </tr>
            <%
                }
            %>

</table>

</body>
</html>
