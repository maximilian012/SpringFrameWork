
<%@page import="mul.cam.a.dto.PdsDto"%>
<%@page import="mul.cam.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

PdsDto pds = (PdsDto)request.getAttribute("dto");
/* BbsDto count = (BbsDto)request.getAttribute("count"); */
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
	<td><%=pds.getTitle() %></td>
</tr>
<tr>
	<th>다운로드</th>
	<td>
		<input type="button" value="다운로드" onclick="filedown(<%=pds.getSeq() %>, '<%=pds.getNewFilename() %>', '<%=pds.getFilename() %>')">
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
	<td><%=pds.getFilename() %></td>
</tr>
<tr>
	<th>등록일</th>
	<td><%=pds.getRegdate() %></td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<pre rows="10" cols="50"><%=pds.getContent() %></pre>
	</td>
</tr>


		</table>
	
	<br>
	<br>
			<%
		if (pds.getId().equals(login.getId())) {
		%>
		
		<button type="button" onclick="location.href='pdsupdate.do?seq=<%=pds.getSeq()%>'"
			class="btn btn-primary">수정하기</button>
		<button type="button" onclick="location.href='pdsdelete.do?seq=<%=pds.getSeq()%>'"
			class="btn btn-primary">삭제하기</button>
		
		
		<%
		}
		%>
		
		
	
	<button type="button" onclick="history.back()" class="btn btn-primary">목록보기</button>
<br><br>

</div>


<form name="file_down" action="filedownLoad.do" method="post">
 <input type="hidden" name="newfilename">
 <input type="hidden" name="filename">
 <input type="hidden" name="seq">

</form>


<script type="text/javascript">

function filedown(seq, newfilename, filename){
	
	document.file_down.newfilename.value = newfilename;
	document.file_down.filename.value = filename;
	document.file_down.seq.value = seq;
	document.file_down.submit();
	
	
}



</script>


</body>
</html>