<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="product.ProductDTO" %>    


<%

		// session 변수에 담긴 값을 불러옴 : 서버의 RAM 
		
		ProductDTO board = new ProductDTO () ;

		//session변수의 값을 가져올 때는 다운캐스팅 필요
		board = ( ProductDTO ) session.getAttribute("board");    // getAttribute : 변수의 값을 가지고 옴)  

	


%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 글 상세 페이지</title>
</head>
<body>
<center>

	<h1> 글 상세 페이지 ( 수정이 가능하도록 )</h1>
	<hr> 
	<br> <br>
	
	<form method = "post" action = "updateProduct.pp">
	
		<!--  글 수정시 조건을 처리할 컬럼  -->
		<input type = "hidden" name = "id" value = "<%= board.getId()  %>" >
		
		<table border = "1" width = "700px" cellpedding = "5px">
			<tr> <td bgcolor = "pink" align ="center" > 상품명 </td>
				 <td> <input type ="text" name = "name" value="<%= board.getName() %>" > </td>
			</tr>
			
			<tr> <td bgcolor = "pink" align ="center"> 가격 </td>
				 <td> <input type ="text" name = "price" value="<%= board.getPrice() %>" > </td>
			</tr>
			
			<tr> <td bgcolor = "pink" align ="center"> 제품설명 </td>
				 <td> <textarea name ="content"  rows = "10" cols ="70" > <%= board.getContent() %>" > </textarea> </td>
			</tr>
			
			<tr> <td bgcolor = "pink" align ="center"> 제품등록일 </td>
				 <td> <%= board.getRegdate() %> </td>
			</tr>
			

			
			<tr> <td colspan = "2" align = "center"> <input type = "submit" value = "수정"> </td>
			</tr>
	
	
	
		</table>
	
	
	</form>
	
	<br> <br>
	 <a href = "deleteProduct.pp?id=<%= board.getId() %>" > 
	  글삭제
	 </a>
	
	<p /> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>
		
		<p /> <a href = "getProductList.do"> 제품목록 </a>
		<p /> <a href = "insertPRodect.jsp"> 제품등록 </a>
	
	

</center>
</body>
</html>