<%@page import="mul.cam.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	MemberDto login = (MemberDto)session.getAttribute("login");
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div align="center">

<form action="pdsupload.do" method="post" enctype="multipart/form-data"> <!--form으로 데이터를 전송하는데 폼필드에 있는거만 전송하지 않고 다전송한다 -->

	<table border="1">
	
		<tr>
			<th>아이디</th>
			<td>
				<%=login.getId()%>
				<input type="hidden" name="id" value="<%=login.getId()%>">
				
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>
				<input type="text" name="title" size="50">
			</td>
		</tr>
		<tr>
			<th>파일업로드</th>
			<td>
				<input type="file" name="fileload">
			</td>
		</tr>
		<tr>
		<th>내용</th>
		<td>
			<textarea rows="10" cols="50" name="content"></textarea>
		</td>
		</tr>
		<tr>
		<td colspan="2">
			<input type="submit" value="자료올리기">
		
		</td>
		
		</tr>
	
	</table>


</form>

</div>
</body>
</html>