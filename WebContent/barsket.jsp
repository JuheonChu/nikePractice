<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.nike.dao.*"%>
<%@ page import="com.nike.dto.CartDTO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" type="text/css" href="./css/barsket.css" />
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha256-4+XzXVhsDmqanXGHaHvgh1gMQKX40OUvDEBTu8JcmNs="
	crossorigin="anonymous"></script>
<script>
	$(function(){//수량 증가
				$(".plus").click(
						function() {

							var quantity = $(this).parent().find(
									"input#quantity").val(); // $("#quantity").val();

							var price = $(this).parent().find("input#gagyuk")
									.val(); //상품가격(alert)

							//var pay = 
							//alert(pay);

							if (quantity < 10) {
								quantity++;

								$(this).parent().find("input#quantity").val(
										quantity);

								var newqty = $(this).parent().find(
										"input#quantity").val();

								$(this).parent().parent().parent().find(
										"td span").text(newqty * price + "원");
								//alert(newqty*price);// 이 가격으로 최종가격 설정해야함.

							}

							//10개까지 구매 가능하다고 띄움
							if (quantity == 10) {
								$(this).parent().parent().find("div span")
										.text("10개까지 구매 가능합니다");

							}

						});

				

				//수량 감소
				$(".minus").click(
								function() {
									var quantity = $(this).parent().find(
											"input#quantity").val(); // $("#quantity").val();
									var price = $(this).parent().find(
											"input#gagyuk").val(); //상품가격(alert)

									if (quantity > 1) { //1이상일 때만 감소 가능
										quantity--;
										$(this).parent().find("input#quantity").val(quantity);

										var newqty = $(this).parent().find(	"input#quantity").val();

										$(this).parent().parent().parent().find("td span").text(newqty * price + "원");
									}
									//10개까지 구매 가능한거 지움
									$(this).parent().parent().find("div span")
											.text("");
								});


				$(".buy").click(
						function() {
							var quantity = $(this).parent().parent().find("input#quantity").val();
							var pnum = $(this).parent().find("input#pnum").val();
							var size = $(this).parent().find("input#size").val();
							var mid= $(this).parent().find("input#mid").val();
							var cost = $(this).parent().find("input#cost").val();
							var imgsrc = $(this).parent().find("input#imgsrc").val();
							var name = $(this).parent().find("input#nameofP").val();
							var cart_id=$(this).parent().find("input#cid").val();

							alert("ProductController?command=purchaseCart&pnum="+pnum +"&quantity="+quantity+"&size="+size+ "&mid=" +mid +"&price=" +cost + "&img="+imgsrc+"&name="+name+"&cartid="+cart_id);

							location.href="ProductController?command=purchaseCart&pnum="+pnum +"&quantity="+quantity+"&size="+size+ "&mid=" +mid +"&price=" +cost+ "&img="+imgsrc+"&name="+name+"&cartid="+cart_id;

							
						});
			})
		
			
</script>

<%
	ArrayList<CartDTO> boardList = (ArrayList<CartDTO>) (request.getAttribute("list"));
	int totalRowCount = ((Integer) request.getAttribute("totalRowCount")).intValue();
	int currentPage = ((Integer) request.getAttribute("page")).intValue();
	int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
	int startPage = ((Integer) request.getAttribute("startPage")).intValue();
	int endPage = ((Integer) request.getAttribute("endPage")).intValue();
%>
<style>
.quantity-manipulation {
	display: inline-flex;
}

table {
	width: 1000px;
}

 tr>{
	border:1px solid black;
}
</style>
</head>
<body>
	<%
		MemberDAO member = MemberDAO.getInstance(); //NullPointerException
		CartDAO cart = CartDAO.getInstance();
		ArrayList<CartDTO> pdtArr = cart.getcartListFromMemberId(member.getMemberID((String) request.getSession().getAttribute("userid")));
		//String renewedQty ="<script>document.writeln(qty)</script>";
		
		
		
	%>
	<div class="header">
		<%@ include file="./header.jsp"%>

	</div>

	<div style = "margin-top:50px; text-align:center;">
	<h2><%=(String)request.getSession().getAttribute("userid") %>님의 장바구니</h2>
	</div>


	<div class="content" style= "margin: 0 auto; margin-top:20px;">


		<table class="barsket_list" id="mytable" style = "margin:0 auto;">
			<tr>
				<th class="td_h"></th>
				<th class="td_h">사진</th>
				<th class="td_h">상품 이름</th>
				<th class="td_h">상품 가격</th>
				<th class="td_h">상품 갯수</th>
				<th class="td_h">결제금액</th>
				<th class="td_h">구매</th>
				<th class="td_h">삭제</th>
			</tr>
			<%
				for (int i = 0; i < pdtArr.size(); i++) {
				CartDTO pdt = pdtArr.get(i);
			%>
			<tbody>
				<tr>
					<td class="td_b_l"><input type="checkbox" name="check"
						value="<%=i%>" /></td>
					<td class="td_b"><img id="img" src="<%=pdt.getImg()%>" /></td>
					<td class="td_b"><%=pdt.getName()%></td>
					<td id="price" class="td_b"><%=pdt.getPrice()%>원</td>
					<td class="td_b_r">
						<div class="quantity-manipulation">
							<input type="button" value="-" class="p_down minus" /> 
							<input type="text" id="quantity" class="qtyqty" name="quantity" class="p" value="<%=pdt.getQty()%>" style="text-align: center; width: 105px;" /> 
							<input type="button" value="+" class="p_up plus" /><br /> 
							<input type="hidden" name="size" value="<%=pdt.getQty()%>" /> 
							<input type="hidden" id="gagyuk" class="gagyuk" value="<%=pdt.getPrice()%>" /> 
							<input type="hidden" id="sum" class="sum" value="<%=pdt.getPrice() * pdt.getQty()%>" />
							
						</div>
						
						<div>
							<span id="quantity-inform"></span>
						</div>
					</td>
					
					<td><span id="payment" class="payment"><%=pdt.getQty() * pdt.getPrice()%></span></td>
					<td>
							<input type="button" class ="buy" name="purchase" value="구매"/>
							<input type="hidden" id="pnum" value="<%=pdt.getPnum()%>"/>
							<input type="hidden" id="size" value="<%=pdt.getSize()%>"/>
							<input type="hidden" id="mid" value="<%=pdt.getMember_id()%>"/>
							<input type="hidden" id="cost" value="<%=pdt.getPrice()%>"/>
							<input type="hidden" id="imgsrc" value="<%=pdt.getImg()%>"/>
							<input type="hidden" id="nameofP" value="<%=pdt.getName()%>"/>
							<input type="hidden" id="cid" value="<%=pdt.getCart_id()%>"/>
					</td>
					<td><input type="submit" name="eliminate" value="삭제" onclick="location.href='ProductController?command=deleteCart&cart_id=<%=pdt.getCart_id()%>' " />
					
				</tr>
			</tbody>
			<%
				}
			%>


			<tr align="center">
				<td colspan='8'>
					<%
						if (currentPage >= 10) {
					%> <a
					href="ProductController?command=mybucketlist&currentPage=<%=currentPage - 10%>">[이전페이지]</a>
					<%
						}
					//endPage
					for (int a = startPage; a <= endPage; a++) {
					if (a == currentPage) {
					%> [<%=a%>] <%
						} else {
					%> <a
					href="ProductController?command=mybucketlist&currentPage=<%=a%>">[<%=a%>]
				</a> <%
 	}
 }
 if (currentPage != maxPage) {
 %> <a
					href="ProductController?command=mybucketlist&currentPage=<%=currentPage + 10%>">[다음페이지]</a>
					<%
						}
					%>
				</td>
			</tr>
		</table>

		<div class="price" style ="margin-bottom:40px;">

			<%
				if (pdtArr.size()==0) {
			%>
			<div style="position: relative;">
				<h1>담긴 상품이 없습니다.</h1>
			</div>
			<%
				}
			%>
		</div>
		
<div class="footer">
	
	<%@ include file ="./footer.jsp" %>
</div>
</body>
</html>