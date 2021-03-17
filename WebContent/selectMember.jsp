<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="book.memberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
	<%
		String good =(String) request.getAttribute("good");
		String bad =(String) request.getAttribute("bad");
	
		if(good!=null){
	%>
		<script>
			alert('회원 등록 성공!');
			location.href="index.jsp";
		</script>
		<% }%>
		<%if(bad != null){%>
			<script>
				alert('회원 등록에 실패했습니다.');
				history.go(-1);
			</script>
		<%}%>
	<%
		ArrayList<memberVO> list =(ArrayList) request.getAttribute("list");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	%>
	<section id="index">
		<table border="1">
			<tr align="center">
				<th>회원번호</th>
				<th>회원이름</th>
				<th>가입일자</th>
				<th>고객등급</th>
				<th>주소</th>
				<th>기능</th>
			</tr>
			<%
				for(memberVO vo : list){
					Date day = vo.getJoindate();
			%>
				<tr align="center">
					<td><%=vo.getCustno() %></td>
					<td><%=vo.getCustname() %></td>
					<td><%=vo.getJoindate() %></td>
					<td><%=vo.getGrade() %></td>
					<td><%=vo.getAddress() %></td>
					<td><a href="updateMember.jsp?custno=<%=vo.getCustno()%>">수정</a></td>
				</tr>
			
			<%
				}
			%>
		</table>
	</section>
</div>
<jsp:include page="footer.jsp"></jsp:include>