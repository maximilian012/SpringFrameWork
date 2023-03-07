
<%@page import="mul.cam.a.dto.PdsDto"%>
<%@page import="mul.cam.a.dto.MemberDto"%>
<%@page import="mul.cam.a.dto.BbsDto"%>
<%@page import="mul.cam.a.dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

PdsDto pds = (PdsDto)request.getAttribute("pds");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  
<style type="text/css">

th {
	background-color: #007bff;
	color:white;
}
pre{
	white-space: pre-wrap;
	word-break:break-all;
	overflow:auto;
}

</style>

</head>

<%
	MemberDto login = (MemberDto)session.getAttribute("login");

%>
		
<body>
	<span></span>
	<div id="app" class="container">
	
<form action="pdsupdateAf.do" method="post" enctype="multipart/form-data"> <!-- 수정할 파일이 없어도 해야함 -->
	<input type="hidden" name="seq" value="<%=pds.getSeq()%>">
		<table class="table table-striped" >
		<colgroup>
		<col style="width: 150px"><col>
		<col style="width: 500px"><col>
		
		</colgroup>
		
			<tr>
	<th>아이디</th>
	<td><%=pds.getId() %></td>
</tr>
<tr>
	<th>제목</th>
	<td><input type="text" value='<%=pds.getTitle()%>' name="title"></td>
</tr>
<tr>
			<th>파일업로드</th>
			<td>
				<input type="file" name="fileload">
			</td>
		</tr>
<tr>
	<th>조회수</th>
	<td><%=pds.getReadcount() %></td>
</tr>
<tr>
	<th>다운로드수</th>
	<td><%=pds.getDowncount() %></td>
</tr>
<tr>
	<th>파일명</th>
	<td><%=pds.getFilename() %>
	<!-- 파일이 변경되지 않았을 경우를 위해 -->
		<input type="hidden" name="filename" value="<%=pds.getFilename() %>">
		<input type="hidden" name="newFilename" value="<%=pds.getNewFilename() %>">
	</td>
		
</tr>

<tr>
	<th>등록일</th>
	<td><%=pds.getRegdate() %></td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="10" cols="50" name="content"><%=pds.getContent() %></textarea>
	</td>
</tr>


		</table>
	
	<br>
	<br>
	
	
	
		<input type="submit" class="btn btn-primary" value="수정하기">

	</form>	
	
	<button type="button" onclick="history.back()" class="btn btn-primary">목록보기</button>
<br><br>



</div>



</body>
</html>