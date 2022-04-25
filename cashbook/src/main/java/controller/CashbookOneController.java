package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;

@WebServlet("/CashbookOneController")
public class CashbookOneController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));//요청값받아옴
		System.out.println(cashbookNo+ "<-cashbookNo");//디버깅

		CashbookDao cashbookDao = new CashbookDao();//호출
		Cashbook cashbook = new Cashbook();//호출
		cashbook  = cashbookDao.selectCashbookOne(cashbookNo);
		
		request.setAttribute("cashbook", cashbook);

		//뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/cashbookOne.jsp").forward(request, response);
		}
}
