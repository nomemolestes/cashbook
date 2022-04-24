package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;

/**
 * Servlet implementation class CashbookOneController
 */
@WebServlet("/CashbookOneController")
public class CashbookOneController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		CashbookDao cashbookDao = new CashbookDao();
		Cashbook cashbook = new Cashbook();
		cashbook = cashbookDao.selectCashbookOne(cashbookNo);
		request.setAttribute("cashbook", cashbook);
		request.setAttribute("cashbookNo", cashbookNo);
		//뷰 포워딩
		request.getRequestDispatcher("/webapp/view/cashbookOne.jsp").forward(request, response);
		}
}
