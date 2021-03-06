package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;
import vo.Cashbook;


@WebServlet("/InsertCashbookController")
public class InsertCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 로그인에 따라서 컨트롤러 접근 허가 또는 불허 !!! 추가
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) {
			//로그인 안했다면 !!!
			response.sendRedirect(request.getContextPath()+"LoginController");//LoginController로 돌아감
		}
		String y = request.getParameter("y");
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		String cashDate = y+"-"+m+"-"+d;
		request.setAttribute("cashDate", cashDate);
		request.getRequestDispatcher("/WEB-INF/view/insertCashBookForm.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		request.setCharacterEncoding("utf-8");//인코딩
		String cashDate = request.getParameter("cashDate");
		String kind = request.getParameter("kind");
		int cash = Integer.parseInt(request.getParameter("cash"));
		String memo = request.getParameter("memo");
		
		System.out.println(cashDate + " <--cashDate InsertCashBookController.doPost()");
		System.out.println(kind + " <--kind InsertCashBookController.doPost()");
		System.out.println(cash + " <--cash InsertCashBookController.doPost()");
		System.out.println(memo + " <--memo InsertCashBookController.doPost()");
		//호출
		Cashbook cashbook = new Cashbook();
		cashbook.setCashDate(cashDate);
		cashbook.setKind(kind);
		cashbook.setCash(cash);
		cashbook.setMemo(memo);

		//해시태그 !!
		//해시태그가 저장될 목록생성
		List<String> hashtag = new ArrayList<>();
		String memo2 = memo.replace("#", " #");
		String[] arr = memo2.split(" ");
		for(String s : arr) {
			if(s.startsWith("#")) {
				String temp = s.replace("#", "");
				if(temp.length()>0) {
					hashtag.add(temp);
				}
			}
		}
		  System.out.println(hashtag.size() + " <--hashtag.size InsertCashBookController.doPost()");
			CashbookDao cashbookDao = new CashbookDao();
			cashbookDao.insertCashbook(cashbook, hashtag);
	      for(String h : hashtag) {
	         System.out.println(h + " <-- hashtag InsertCashBookController.doPost()");
	         
	      }
			response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");

	   }
	}