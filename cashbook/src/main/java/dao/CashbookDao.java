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
	//갱신
	public void updateCashbook(Cashbook cashbook, List<String>hashtag) {
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//드라이버로딩
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			//text 쿼리문 전송
			String sql = "update cashbook set cash_date=?, kind=?, cash=?, memo=?, update_date=new() where cashbook_no=?";
			stmt = conn.prepareStatement(sql);//쿼리저장
			stmt.setString(1, cashbook.getCashDate());//순번에 따라 해당 값을 String값으로 지정
			stmt.setString(2, cashbook.getKind());
			stmt.setInt(3, cashbook.getCash());
			stmt.setString(4, cashbook.getMemo());
			stmt.setInt(5, cashbook.getCashbookNo());
			stmt.executeUpdate();//insert, update와 함께,결과값리턴함
			//해시태그부분.....
			
			
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				conn.close();
				rs.close();
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//상세보기
	public Cashbook selectCashbookOne(int cashbookNo) {
		//vo값 불러오기, 객체생성
		Cashbook cashbook = new Cashbook();
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//드라이버로딩
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			//쿼리문 전송
			String sql = "select cashbook_no cashbookNo, cash_date cashDate, kind, cash, memo, update_date updateDate, create_date createDate from cashbook where cashbook_no=?";
			//쿼리문 저장
			stmt = conn.prepareStatement(sql);
			//매개변수 어떤값인지....
			stmt.setInt(1, cashbookNo);
			//리턴값반환
			rs = stmt.executeQuery();
			//조건문, 커러가 위치하는 행 지정, 아래에 데이터가 있으면 값을가져옴 
			if(rs.next()) {
				//순번에 따라 지정된 데이터타입 값으로 지정함... 
				cashbook.setCashbookNo(rs.getInt("cashbookNo"));
				cashbook.setCashDate(rs.getString("cashDate"));
				cashbook.setKind(rs.getString("kind"));
				cashbook.setCash(rs.getInt("cash"));
				cashbook.setMemo(rs.getString("memo"));
				cashbook.setUpdateDate(rs.getString("updateDate"));
				cashbook.setCreateDate(rs.getString("createDate"));
			}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					//반납
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		//반환
		return cashbook;
	}
	//삭제
	public void deleteCashbook(int cashbookNo) {
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		//텍스트쿼리문 전송(해당 테이블에서 삭제할것)
		String sql1 = "delete from hashtag where cashbook_no=?";
		String sql2 = "delete from cashbook where cashbook_no=?";
		try {
			//드라이버로딩
			Class.forName("org.mariadb.jdbc.Driver");
			//객체생성
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); // 자동커밋해제
			//객체생성, 쿼리문저장
			stmt = conn.prepareStatement(sql1);
			//물음표와 값, 어떤 데이터타입인지..매개변수에 값을 지정함
			stmt.setInt(1, cashbookNo);
			//실행 후 결과리턴
			stmt.executeUpdate();//delete insert와 함께
			stmt.close(); //반환			
			//두번째 쿼리문 저장
			stmt = conn.prepareStatement(sql2);
			//물음표와 값.. 어떤 데이터타입인지.. 매개변수에 값을 지정함
			stmt.setInt(1, cashbookNo);
			//실행 후 결과 리턴
			stmt.executeUpdate();
			conn.commit(); //최종 커밋
		} catch (Exception e) {
			try {
				conn.rollback();//트랜잭션처리
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				//반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	//삽입
	public void insertCashbook(Cashbook cashbook, List<String> hashtag) {
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//드라이버로딩
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false);//자동커밋방지
			//쿼리문전송
			String sql = "insert into cashbook(cash_date, kind, cash, memo, update_date, create_date"
						+ " values(?,?,?,?,now(),now())";
			//쿼리문저장, insert 문 실행하고 자동생성된 기본키값을 검색...?
					stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
					//물음표와 값, 어떤 데이터타입인지..매개변수에 값을 지정함
					stmt.setString(1, cashbook.getCashDate());
					stmt.setString(2, cashbook.getMemo());
					stmt.setInt(3, cashbook.getCash());
					stmt.setString(4, cashbook.getMemo());
					//결과값리턴
					stmt.executeUpdate();
					rs = stmt.getGeneratedKeys();
					int cashbookNo = 0;
					if(rs.next()) {
						cashbookNo = rs.getInt(1);
					}
					//hashtag저장
					for(String h : hashtag) {
						PreparedStatement stmt2 = null;
						//쿼리문실행
						String sql2 = "insert into hashtag(cashbook_no, tag, create_date) values(?,?,new())";
						//쿼리문저장
						stmt2 = conn.prepareStatement(sql2);
						//매개변수값이 무엇인지
						stmt2.setInt(1, cashbookNo);
						stmt2.setString(2, h);
						//결과값 리턴
						stmt2.executeUpdate();			
					}	
			conn.commit();//최종커밋
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
	//달력레이아웃.
	public List<Map<String, Object>> selectCashbookListByMonth(int y, int m) {
		//데이터베이스저장준비
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
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//쿼리문실행
		String sql = "SELECT"
				+ "		 	cashbook_no cashbookNo"
				+ "		 	,DAY(cash_date) day"
				+ "		 	,kind"
				+ "		 	,cash"
				+ "			,LEFT(memo,5) memo"
				+ "		 FROM cashbook"
				+ "		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?"
				+ "		 ORDER BY DAY(cash_date) ASC, kind ASC";
		try {
			//드라이버로딩
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			//쿼리문저장
			stmt = conn.prepareStatement(sql);
			//물음표와 값, 어떤 데이터타입인지..매개변수에 값을 지정함
			stmt.setInt(1, y);
			stmt.setInt(2, m);
			//결과값리턴
			rs = stmt.executeQuery();
			while(rs.next()) {
				//map에 다음의 값들을 저장
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
				//반환
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} //반납
		return list;
	}
}
