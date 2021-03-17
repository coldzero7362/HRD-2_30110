<%@page import="book.memberVO"%>
<%@page import="book.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
	<%	
		int custno = Integer.parseInt(request.getParameter("custno"));
		
		String good =(String) request.getAttribute("good");
		String bad =(String) request.getAttribute("bad");
		
		if(good!=null){
			%>
				<script>
					alert('회원정보 수정 성공!');
					location.href="index.jsp";
				</script>
				<% }%>
				<%if(bad != null){%>
					<script>
						alert('회원정보 수정에 실패했습니다.');
						history.go(-1);
					</script>
				<%}%>
			<%
		
		memberDAO instance = memberDAO.getInstance();
		memberVO vo = instance.getAline(custno);
	%>
	<section id="index">
		<form action="/MemberUpdate.do" name="insertForm">
		<table border="1">
			<tr>
				<td>회원번호</td>
				<td><input type="text" value="<%=custno%>" name="custno" readonly="readonly"></td>
			<tr>
			<tr>
				<td>회원이름</td>
				<td><input type="text" value="<%=vo.getCustname()%>" name="custName" required="required"></td>
			<tr>
			<tr>
				<td>가입일자</td>
				<td><input type="text" name="joinDate" value="<%=vo.getJoindate() %>" required="required" onkeyup="inputdayNumber(this)"></td>
			<tr>
			<tr>
				<td>고객등급</td>
				<td>
					<select name="grade">
						<option value="A">VIP</option>
						<option value="B">일반</option>
						<option value="C">직원</option>
					</select>
				</td>
			<tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address" value="<%=vo.getAddress() %>" required="required"></td>
			<tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="등록">
				</td>
			<tr>
		</table>
	</form>
	</section>
<jsp:include page="footer.jsp"/>