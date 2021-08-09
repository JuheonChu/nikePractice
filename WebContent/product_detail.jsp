<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="./includeFile.jsp"%>
<link rel="stylesheet" type="text/css" href="./css/detail.css" />
<title>상품 상세 보기</title>
<script>
	$(function() {
		//구매하기 버튼 이벤트
		$("#purchase").click(function() {
							var quantity = $("#quantity").val();
							var size = $("#size").val();
							var price =  $("#product-price").val();
							var pnum = $("#pnum").val();
						//	var total = price * size;
							var img = $("#img").val();
							var name = $("#name").val();
							
							 var warning = confirm('구매하시겠습니까?');
					         if(warning){
					     ////////위의 var들 값 찍히는거 크롬 콘솔에서 확인함////////////////
									location.href = "ProductController?command=purchase&quantity="
										+quantity + "&size=" + size+ "&price="+price + 
										"&pnum="+pnum + "&img="+img + "&name="+name;
						     }else{

							 }
							
							
						});
	

		//장바구니 버튼 이벤트
	      $("#bucket").click(function() {
	         var quantity = $("#quantity").val();
	         var size = $("#size").val();
	         var name=$("#name").val();
	         var pnum = $("#pnum").val();
	         
	         var warning = confirm('장바구니에 담으시겠습니까?');
	         if(warning){
	        	 location.href = "ProductController?command=bucketAdd&quantity=" + quantity + "&size=" + size+"&name="+name+"&pnum="+pnum;
			 }else{

			  }
	      	 
	      });

		//수량 증가
		$("#plus").click(function() {
			var quantity = $("#quantity").val();
			if (quantity < 10) {
				quantity++;
				$("#quantity").val(quantity);
			}

			//10개까지 구매 가능하다고 띄움
			if (quantity == 10) {
				$("#quantity-inform").text("10개까지 구매 가능합니다");
			}

		})
		//수량 감소
		$("#minus").click(function() {
			var quantity = $("#quantity").val();
			if (quantity > 1) { //1이상일 때만 감소 가능
				quantity--;
				$("#quantity").val(quantity);
			}
			//10개까지 구매 가능한거 지움
			$("#quantity-inform").text("");
		})

		//사이즈 설정
		var type = $("#type").val();
		var gender = $("#gender").val();
		var sizeArr;

		   if(type == "clothes" || type == "kid-clothes"){
	            sizeArr = new Array("S", "M", "L", "XL");
	         }else if(type == "shoes"){
	            if(gender == "men"){
	              sizeArr = new Array("250", "260", "270", "280", "290");
	            }else if(gender == "women"){  
	               sizeArr = new Array("220", "230", "240", "250", "260");
	            }else if(gender == "all"){
	              sizeArr = new Array("230", "240", "250", "260", "270", "280", "290");
	            }
	         }else if(type == "kid-shoes"){
	            sizeArr = new Array("150", "160", "170", "180", "190", "200");
	         }

		for (var i = 0; i < sizeArr.length; i++) {
			var option = $("<option>" + sizeArr[i] + "</option>");
			$('#size').append(option);
		}

		//천단위 구분 콤마
		var num = $("#product-price").val();
		
		function comma(inputNum) {
			num = inputNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			$(".price").html(num + "원");
		}
		comma(num);

		//상품 삭제
		$("#delBtn").click(function (){
			var pnum = $("#pnum").val(); //pnum을 가져옴
			var warning = confirm('상품을 삭제하시겠습니까?');
			
			if(warning){
				//yes
				location.href = 'ProductController?command=delete&pnum='+pnum+ "&gender="+gender+"&type="+type;
			}else{
				//no
			}
		});

	});
</script>
<style>
#quantity-inform {
	font-size: 15px;
	color: red;
	font-weight: bold;
	padding-top: 5px;
	border-top: 1px solid red;
}
</style>
</head>
<body>
	<div class="header">
		<%@ include file="./header.jsp"%>
	</div>
	<div class="content">
	
		<ul>
			<li style="display: inline-block;">
				<div class="image-section">
					<img src="${productdetail.getImg()}" />
				</div>
				<div class="detail-section">
					<%
						//등급이 관리자이면 수정하기 버튼이 보인다.
					//System.out.print((String) session.getAttribute("loginTrue"));
					if ("true".equals((String) session.getAttribute("loginTrue"))) {
						if ("admin".equals(session.getAttribute("grade"))) {
					%>
					<input type="button" value="수정하기" class="btn btn-warning"
						onclick="location.href='ProductController?command=modify&pnum=${productdetail.getPnum()}'" /><br />
					<input type="button" value="상품삭제" class="btn btn-danger" id="delBtn"/>
					<br />
					<br />
					<%
						}
					}
					%>
					<!-- 사이즈 설정을 위해 type과 gender를 hidden으로 가져온다. -->
					<input type="hidden" name="name" id="name" value="${productdetail.getName()}" /> 
					<input type="hidden" name="img" id="img" value="${productdetail.getImg()}" /> 
					<input type="hidden" name="type" id="type" value="${productdetail.getType()}" /> 
					<input type="hidden" name="gender" id="gender" value="${productdetail.getGender()}" />
					<input type="hidden" name="price" id="product-price" value="${productdetail.getPrice()}" /> 
					<input type="hidden" name="pnum" id="pnum" value="${productdetail.getPnum()}" /> 
					
					<span class="explain">${productdetail.getExplain()}</span>
					<span id = "price" class="price">${productdetail.getPrice() }원</span><br /> 
					<span class="detail-name">${productdetail.getName() }</span> <br /> <br />
					<br /> <br /> 
					
					<%
               			String type = (String)request.getAttribute("type");
               			if(!"other".equals(type)){
              		 %>
	               		<!-- 사이즈 선택 옵션 리스트 -->
               			<label for="size"><b>사이즈</b></label>&nbsp;&nbsp;
	              		<select name="size" id="size">
	               		</select>                             
              		 <%			
               			}else{
               		%>
							<input type="hidden" name="size" id="size" value="none" />
               		<%			
               			}
               		%>      
               <br />
					
					
					
					
					
					
					
					
				<!-- 	<label for="size"><b>사이즈</b></label>&nbsp;&nbsp; -->
					<!-- 사이즈 선택 옵션 리스트 -->
				<!--	<select name="size" id="size"> -->
				<!--		<option value="230">230</option>-->
				<!--		<option value="240">240</option>-->
					<!--	<option value="250">250</option>-->
					<!--	<option value="260">260</option>-->
					<!--	<option value="270">270</option>-->
					<!--</select> <br /> -->-->S
					
					<label for="quantity"><b>수량</b> &nbsp;&nbsp;</label>
					<input type="text" id="quantity" name="quantity" value="1" readonly="readonly" /><input type="button" value="-" id="minus" />
					<input type="button" value="+" id="plus" /><br /> 
					<span id="quantity-inform"></span> 
					<input type="button" value="바로구매" id="purchase" class="btn btn-dark"/><br /> 
					<input type="submit" value="장바구니" id="bucket" class="btn btn-secondary" /> 
						
					<span class="detail-explain"> ${productdetail.getDetailexplain()}</span>
				</div>
	</div>
	</li>
	</ul>
	<div class="footer">
		<%@ include file="./footer.jsp"%>
	</div>
</body>
</html>