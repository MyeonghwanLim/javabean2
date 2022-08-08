<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.MemberVO,dao.MemberDAO, java.util.ArrayList" %>
<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="mVO" class="vo.MemberVO" />
<jsp:useBean id="mDAO" class="dao.MemberDAO" />
<jsp:setProperty property="*" name="mVO"/>
<%
	if(mDAO.insert(mVO)){
		%>
	<script type="text/javascript">
	alert('<%=request.getParameter("name")%> 데이터 등록 성공!');
	location.href="NewFile.jsp"
	</script>
		<%
	}
	else{
		%>
		<script type="text/javascript">
		alert('데이터 등록 실패..');
		location.href="NewFile.jsp"
		</script>
		<%
	}
%>