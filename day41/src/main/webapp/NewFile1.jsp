<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.MemberVO" %>
<jsp:useBean id="mDAO" class="dao.MemberDAO" />
<jsp:useBean id="mVO" class="vo.MemberVO" />
<jsp:setProperty property="*" name="mVO" />
<%
	MemberVO data=mDAO.selectOne(mVO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="NewFile2.jsp" method="post">
번호: <input type="number" name="mpk" value="<%=data.getMpk()%>" required readonly> <br>
이름: <input type="text" name="name" value="<%=data.getName()%>" required> <br>
성적: <input type="number" name="score" min="0" max="100" value="<%=data.getScore()%>" required> <br><br>
<input type="submit" value="데이터 변경하기">
</form>

</body>
</html>