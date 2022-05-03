package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;

@WebServlet("/tahKindSearchController")
public class TagKindSearchController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//인코딩
		String kind = request.getParameter("kind");//값을 받아옴.... 종류
		//dao호출
		HashtagDao hashtagDao = new HashtagDao();
		List <Map<String, Object>>list = hashtagDao.selectTagRankList();
		
		request.setAttribute("list", list);//jsp페이지로 값 넘겨줌
		request.setAttribute("kind", kind);
		request.getRequestDispatcher("/WEB-INF/view/tagKindSearch.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
