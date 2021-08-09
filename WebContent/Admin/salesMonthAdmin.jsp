<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>월별 매출</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(function() {
		//자바 배열을 자바스크립트 배열로 바꿔줌.
		var labels = new Array();
		<c:forEach var="month" items="${month}">
			labels.push(${month});
		</c:forEach>
		
		var data = new Array();
		<c:forEach var="total" items="${total}">
			data.push(${total});
		</c:forEach>
		
		//년도 설정 버튼
	    //다음년도
	      $("#plus").click(function(){
	         var year = $("#year").val();
	         var thisDate = new Date();
	         var thisYear = thisDate.getFullYear();
	         if(year < thisYear){ //현재년도 이후로는 못감
		         year++;
		         location.href = "./NikeactionController?command=monthSales&year="+year;
	         }
	      })
	      
	    //이전년도
	      $("#minus").click(function(){
	         var year = $("#year").val();         
	         year--;
	         location.href = "./NikeactionController?command=monthSales&year="+year;
	     })
		
		//차트 생성
		// 우선 컨텍스트를 가져옵니다. 
		var ctx = document.getElementById("myChart").getContext('2d');
		/*
		- Chart를 생성하면서, 
		- ctx를 첫번째 argument로 넘겨주고, 
		- 두번째 argument로 그림을 그릴때 필요한 요소들을 모두 넘겨줍니다.
		- 코드 출처 : https://frhyme.github.io/javascript/chart_js_basic_and_canvas/
		*/
		
		var myChart = new Chart(ctx, {
		    type: 'bar',
		    data: {
		        labels,
		        datasets: [{
		            label: '# of Sales',
		            data,
		            backgroundColor: [
		                'rgba(255, 99, 132, 0.2)',
		                'rgba(54, 162, 235, 0.2)',
		                'rgba(255, 206, 86, 0.2)',
		                'rgba(75, 192, 192, 0.2)',
		                'rgba(153, 102, 255, 0.2)',
		                'rgba(255, 159, 64, 0.2)',
		                'rgba(255, 99, 132, 0.2)',
		                'rgba(54, 162, 235, 0.2)',
		                'rgba(255, 206, 86, 0.2)',
		                'rgba(75, 192, 192, 0.2)',
		                'rgba(153, 102, 255, 0.2)',
		                'rgba(255, 159, 64, 0.2)'
		            ],
		            borderColor: [
		                'rgba(255,99,132,1)',
		                'rgba(54, 162, 235, 1)',
		                'rgba(255, 206, 86, 1)',
		                'rgba(75, 192, 192, 1)',
		                'rgba(153, 102, 255, 1)',
		                'rgba(255, 159, 64, 1)',
		                'rgba(255,99,132,1)',
		                'rgba(54, 162, 235, 1)',
		                'rgba(255, 206, 86, 1)',
		                'rgba(75, 192, 192, 1)',
		                'rgba(153, 102, 255, 1)',
		                'rgba(255, 159, 64, 1)'
		            ],
		            borderWidth: 1
		        }]
		    },
		    options: {
		        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		    }
		});
	});
</script>
</head>
<body>
	<br />
	<input type="hidden" name="year" id="year"
		value="<%=request.getAttribute("year")%>" />
	<h2 style="text-align: center;">
		<input type="button" value="&lt;" id="minus" class="btn btn-link"
			style="font-size: 30px;" />
		<%=request.getAttribute("year")%>년 나이키 매출 현황 <input type="button"
			value="&gt;" id="plus" class="btn btn-link" style="font-size: 30px;" />
	</h2>
	<br />
	<div style="width: 70%; margin: 0 auto;">
		<canvas id="myChart"></canvas>
	</div>
	<%!//현재 월 2자리로 맞추는 함수
	public String getTwoMonth(int month) {
		if (month > 0 && month < 10) {
			return "0" + String.valueOf(month);
		} else {
			return String.valueOf(month);
		}
	}%>
	<%
		//현재 월, 일을 구하는 부분
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		Calendar cal = Calendar.getInstance();

		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1;
		String twoMonth = getTwoMonth(month);

		//현재 월의 1일부터 시작
		String start = year + "-" + twoMonth + "-" + "01";
		//현재 월, 일로 끝남
		String end = format.format(now);

		String salesAction = "./NikeactionController?command=periodSales&start=" + start + "&end=" + end;
	%>
	<input type="button" value="상세 조회" class="btn btn-primary"
		onclick="window.open('<%=salesAction%>', '_blank')"
		style="float: right; margin-right: 10%;" />
</body>
</html>