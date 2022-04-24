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
		System.out.println(cashbookNo+ "<-cashbookNo");//디버깅

		CashbookDao cashbookDao = new CashbookDao();
		Cashbook cashbook = new Cashbook();
		cashbook = cashbookDao.selectCashbookOne(cashbookNo);
		
		request.setAttribute("cashbookNo", cashbook.getCashbookNo());//선택한 요소의 속성이름, 속성값
		request.setAttribute("cashDate", cashbook.getCashDate());
		request.setAttribute("kind", cashbook.getKind());
		request.setAttribute("cash", cashbook.getCash());
		request.setAttribute("memo", cashbook.getMemo());
		request.setAttribute("updateDate", cashbook.getUpdateDate());
		request.setAttribute("createDate", cashbook.getCreateDate());
		
		//뷰 포워딩
		request.getRequestDispatcher("/webapp/view/cashbookOne.jsp").forward(request, response);
		}
}
