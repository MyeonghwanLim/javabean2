<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="mDAO" class="dao.MemberDAO" />
<jsp:useBean id="mVO" class="vo.MemberVO" />
<jsp:setProperty property="*" name="mVO" />
<%
	if(mDAO.update(mVO)){
	%>
	<script type="text/javascript">
		alert('<%=mVO.getMpk()%>번 데이터 변경완료!');
		location.href='NewFile.jsp';
	</script>
	<%
	}
	else{
	%>
	<script type="text/javascript">
		alert('<%=mVO.getMpk()%>번 데이터 변경실패...');
		location.href='NewFile.jsp';
	</script>
	<%	
	}
%>