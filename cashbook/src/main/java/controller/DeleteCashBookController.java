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
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println(cashbookNo + "<- cashbookNo");
		
		CashbookDao cashbookDao = new CashbookDao();
		cashbookDao.deleteCashbook(cashbookNo);
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}

}
