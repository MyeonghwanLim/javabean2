<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.MemberVO, java.util.ArrayList" %>
<jsp:useBean id="mDAO" class="dao.MemberDAO" />
<jsp:useBean id="mVO" class="vo.MemberVO" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="controller.jsp" method="post">
	 이름: <input type="text" name="name"><br>	
	 점수: <input type="number" min="0" max="100" name="score" required><br><br>
	<input type="submit" value="데이터 등록하기">	
</form>
<hr>
<h1>데이터 목록</h1>
<hr>
<%
	ArrayList<MemberVO> datas=mDAO.selectAll(mVO);
	if(datas.size()==0){
		out.println("<h3>출력할 데이터가 없습니다...<h3>");
	}
	else{
%>
<table border="2">
	<tr>
		<th>번 호</th><th>이 름</th>
	</tr>
	<%
		for(MemberVO v:mDAO.selectAll(mVO)){
	%>
	<tr>
		<th><%=v.getMpk()%></th>
		<td><%=v.getName()%></td>
	</tr>
	<%
		}
	%>
</table>
<%
	}
%>
</body>
</html>