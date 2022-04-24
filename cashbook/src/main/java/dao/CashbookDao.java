package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Cashbook;

public class CashbookDao {
	public void insertCashbook(Cashbook cashbook, List<String> hashtag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;//insert된 키값을 받아야하므로
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false);//자동커밋방지..
			
			String sql = "insert into cashbook(cash_date, kind, cash, memo, update_date, create_date"
						+ " values(?,?,?,?,new(),now())";
					//방금 입력된 insert 테이블에 키값을 select	ex)select 방금 입력한 cashbook_no from cashbook
						stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
						stmt.setString(1, cashbook.getCashDate());
						stmt.setString(2, cashbook.getMemo());
						stmt.setInt(3, cashbook.getCash());
						stmt.setString(4, cashbook.getMemo());
						stmt.executeUpdate();
						rs = stmt.getGeneratedKeys();
						int cashbookNo = 0;
						if(rs.next()) {
							cashbookNo = rs.getInt(1);
						}
						
						//hashtag저장코드
						for(String h : hashtag) {
							PreparedStatement stmt2 = null;
							String sql2 = "insert into hashtag(cashbook_no, tag, create_date) values(?,?,new())";
							stmt2 = conn.prepareStatement(sql2);
							stmt2.setInt(1, cashbookNo);
							stmt2.setString(2, h);
							stmt2.executeUpdate();//실행
							
						}
						
						
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}	
	public List<Map<String, Object>> selectCashbookListByMonth(int y, int m) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		/*
		 SELECT 
		 	cashbook_no cashbookNo
		 	,DAY(cash_date) day
		 	,kind
		 	,cash
		 	,LEFT(memo, 5) memo
		 FROM cashbook
		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?
		 ORDER BY DAY(cash_date) ASC, kind ASC
		 */
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT"
				+ "		 	cashbook_no cashbookNo"
				+ "		 	,DAY(cash_date) day"
				+ "		 	,kind"
				+ "		 	,cash"
				+ "			,LEFT(memo, 5) memo"
				+ "		 FROM cashbook"
				+ "		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?"
				+ "		 ORDER BY DAY(cash_date) ASC, kind ASC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, y);
			stmt.setInt(2, m);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("day", rs.getInt("day"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getInt("cash"));
				map.put("memo", rs.getString("memo"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
