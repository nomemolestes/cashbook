package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;

@WebServlet("/UpdateCashbookController")
public class UpdateCashbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//유효성검사
		if(request.getParameter("cashbookNo") == null) {
			response.sendRedirect(request.getContextPath()+"CashbookListByMonthController?msg=null");
			return;
		}
		
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println(cashbookNo + "<-cashbookNo");//디버깅
		//dao 호출
		CashbookDao cashbookDao = new CashbookDao();
		Cashbook cashbook = new Cashbook();
		cashbook = cashbookDao.selectCashbookOne(cashbookNo);
		//request:요청시마다 만들어지는 객체, 서블릿간 공유.../ 이전에 다른 jsp또는 servlet페이지에 설정된 매개변수를 가져오는데 사용(대신 이전에 setAttribute로 설정했어야함)/ view로 전달할떄 사용
		request.setAttribute("cashbookNo", cashbookNo);
		request.setAttribute("cashbook", cashbook);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("utf-8");
		Cashbook cashbook = new Cashbook();
		cashbook.setKind(request.getParameter("kind"));
		cashbook.setCash(Integer.parseInt(request.getParameter("cash")));
		cashbook.setMemo(request.getParameter("memo"));
		cashbook.setCashbookNo(Integer.parseInt(request.getParameter("cashbookNo")));
		//dao호출
		CashbookDao cashbookDao = new CashbookDao();
		cashbookDao.updateCashbook(cashbook);
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}

}
