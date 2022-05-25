package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;
import vo.Cashbook;

@WebServlet("/CashbookOneController")
public class CashbookOneController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 로그인에 따라서 컨트롤러 접근 허가 또는 불허 !!! 추가
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) {
			//로그인 안했다면 !!!
			response.sendRedirect(request.getContextPath()+"LoginController");//LoginController로 돌아감
		}
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));//요청값받아옴
		System.out.println(cashbookNo+ "<-cashbookNo");//디버깅

		CashbookDao cashbookDao = new CashbookDao();//호출
		Cashbook cashbook = new Cashbook();//호출
		cashbook  = cashbookDao.selectCashbookOne(cashbookNo);
		
		Calendar now = Calendar.getInstance();
		int y = now.get(Calendar.YEAR);
		int m = now.get(Calendar.MONTH)+1;

		request.setAttribute("cashbook", cashbook);
		request.setAttribute("y", y);
		request.setAttribute("m", m);

		//뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/cashbookOne.jsp").forward(request, response);
		}
}
