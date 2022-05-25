package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;

@WebServlet("/DeleteCashbookController")
public class DeleteCashBookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 로그인에 따라서 컨트롤러 접근 허가 또는 불허 !!! 추가
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) {
			//로그인 안했다면 !!!
			response.sendRedirect(request.getContextPath()+"LoginController");//LoginController로 돌아감
		}
		//유효성검사
		if(request.getParameter("cashbookNo")==null) {
			response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController?msg=null");
			return;
		}
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));//요청값 받아옴
		System.out.println(cashbookNo + "<- cashbookNo");//디버깅
		//dao 호출
		CashbookDao cashbookDao = new CashbookDao();
		cashbookDao.deleteCashbook(cashbookNo);
		//완료 후 요청?
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}

}
