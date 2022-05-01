<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CashbookListByMonth</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<%
	/*
		cashbook의 jsp파일들 모두 실행해도 404오류와 함께 실행되는 웹페이지창에 WEB_INF~주소로만 실행됨(WEB-INF파일은 외부에서 접근불가이므로.. controller호출해야함.)
		그래서 톰캣을 삭제하고 다시 깔아도 오류는 그대로(삭제하고 재설정시 포트번호 바꿔야함)
		실행되게 된 이유 : Server에서 web.xml에서 중간에 web-app앞에 해당 오류메세지(There is '1' error in 'javaee_7.xsd'.)와 함께 오류발생해서, 뒤에 xsi?에 있는 http주소들 사이를 세미콜론으로 구분했더니
		해결완료 !!!!!
	*/
		//값받아옴...........
		List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
		int y = (Integer)request.getAttribute("y");
		int m = (Integer)request.getAttribute("m");
		
		int startBlank = (Integer)request.getAttribute("startBlank");
		int endDay = (Integer)request.getAttribute("endDay");
		int endBlank = (Integer)request.getAttribute("endBlank");
		int totalTd = (Integer)request.getAttribute("totalTd");
		//디버깅
		System.out.println(list.size() +" <- list.size() CashbookListByMonth.jsp");
		System.out.println(y +" <- y CashbookListByMonth.jsp");
		System.out.println(m +" <- m CashbookListByMonth.jsp");
		
		System.out.println(startBlank +" <- startBlank CashbookListByMonth.jsp");
		System.out.println(endDay +" <- endDay CashbookListByMonth.jsp");
		System.out.println(endBlank +" <- endBlank CashbookListByMonth.jsp");
		System.out.println(totalTd +" <- totalTd CashbookListByMonth.jsp");
	%>
	<h3><%=y%>년 <%=m%>월</h3>
	<!-- 로그인+로그아웃 -->
	<div>
		<%=session.getAttribute("sessionMemberId")%>님 반갑습니다.
		<a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a>
	</div>
	<div>
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController?y=<%=y%>&m=<%=m-1%>">이전달</a>
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController?y=<%=y%>&m=<%=m+1%>">다음달</a>
	</div>
	<!-- 
		1) 이번날 1일의 요일 firstDayYoil -> startBlank -> 일 0, 월 1, 화 2, ... 토 6
		2) 이번달 마지막날짜 endDay
		3) endBlank -> totalBlank
		4) td의갯수 1 ~ totalBlank
				+		
		5) 가계부 list
		6) 오늘 날짜
	-->
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
					for(int i=1; i<=totalTd; i+=1) {
						if((i-startBlank) > 0 && (i-startBlank) <= endDay) {
							String c = "";
							if(i%7==0) {
								c = "text-primary";
							} else if(i%7==1) {
								c = "text-danger";
							}
				%>
				<!-- 날짜출력 -->
							<td class="<%=c%>">
								<%=i-startBlank%>
								<a href="<%=request.getContextPath()%>/InsertCashbookController?y=<%=y%>&m=<%=m%>&d=<%=i-startBlank%>" class="btn btn-light">입력</a>
								<div>
									<%
										// 해당 날짜의 cashbook 목록 출력
										for(Map map : list) {
											if((Integer)map.get("day") == (i-startBlank)) {
									%>
												<div>
													<a href="<%=request.getContextPath()%>/CashbookOneController?cashbookNo=<%=map.get("cashbookNo")%>"></a>
													[<%=map.get("kind")%>] 
													<%=map.get("cash")%>원
													<%=map.get("memo")%>...
												</div>
									<%			
											}
										}
									%>
								</div>
							</td>
				<%
						} else {
				%>
							<td>&nbsp;</td>
				<%			
						}
						if(i<totalTd && i%7==0) {
				%>
							</tr><tr><!-- 새로운 행을 추가시키기 위해 -->
				<%			
						}
					}
				%>
			</tr>
		</tbody>
	</table>
</body>
</html>
