<%@page import="book.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
	<% 
		memberDAO instance = memberDAO.getInstance();
		int maxNo = instance.getMax();
	%>
	<script type="text/javascript">
	function inputdayNumber(obj) {
	    var number = obj.value.replace(/[^0-9]/g, "");
	    var day = "";
	       
	    if(number.length < 4) {
	        return number;
	    } else if(number.length < 6) {
	       day += number.substr(0, 4);
	       day += "-";
	       day += number.substr(4);
	    }else{
	       day += number.substr(0, 4);
	       day += "-";
	       day += number.substr(4, 2);
	      day += "-";
	      day += number.substr(6);
	    }
	    
	    obj.value = day;
	}
	</script>
	<section id="index">
	<form action="/MemberInsert.do" name="insertForm">
		<table border="1">
			<tr>
				<td>회원번호</td>
				<td><input type="text" value="<%=maxNo%>" name="custno" readonly="readonly"></td>
			<tr>
			<tr>
				<td>회원이름</td>
				<td><input type="text" name="custName" required="required"></td>
			<tr>
			<tr>
				<td>가입일자</td>
				<td><input type="text" name="joinDate" required="required" onkeyup="inputdayNumber(this)"></td>
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
				<td><input type="text" name="address" required="required"></td>
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