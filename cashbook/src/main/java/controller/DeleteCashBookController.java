package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;

@WebServlet("/DeleteCashbookController")
public class DeleteCashBookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
