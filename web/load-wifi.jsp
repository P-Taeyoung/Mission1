<%--
  Created by IntelliJ IDEA.
  User: taeyeong
  Date: 24. 12. 9.
  Time: 오후 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="missionJava.WifiService"%>
<%@ page import="missionJava.JsonFileToDatabase"%>

<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
      h1 {
        text-align: center;
      }
      p {
          text-align: center;
      }
    </style>
    <%
        WifiService wifiService = new WifiService();
        if (wifiService.countData() == 0) {
            JsonFileToDatabase jsonFileToDatabase = new JsonFileToDatabase();
            jsonFileToDatabase.toDatabase();
        }
    %>
</head>
<body>
    <h1><%=wifiService.countData()%>개의 WIFI정보를 정상적으로 저장하였습니다.</h1>
    <p><a href="index.jsp">홈으로 가기</a></p>
</body>
</html>
