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

import vo.Hashtag;

public class HashtagDao {
	//태그 많은 순... 순위매기기
	public List<Map<String, Object>> selectTagRankList() {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;//insert된 키값을 받아야하므로
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");			
			String sql = "SELECT kind, t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) ranking"
					+" FROM"
					+" (SELECT tag, COUNT(*) cnt"
					+" FROM hashtag"
					+" GROUP BY t.tag) t";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("tag", rs.getString("tag"));
				map.put("cnt", rs.getInt("t.cnt"));
				map.put("rank", rs.getInt("rank"));
				list.add(map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//반환
		return list;
	}
	//해시태그 목록
	public List<Map<String, Object>> selectCashbookListByTag(Hashtag hashtag) {
		//저장할목록설정
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//드라이버로딩
			Class.forName("org.mariadb.jdbc.Driver"); // 드라이브 로딩
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook", "root", "java1234");
			//텍스트쿼리전송
			String sql = "select h.tag, c.kind kind, c.cash cash, c.memo memo, c.cash_date cashDate from cashbook c left join hastag h in c.cashbook_no = h.cashbook_no where h.tag=?";
			//쿼리문저장
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, hashtag.getTag());
			//결과값 반환
			rs = stmt.executeQuery();
			while(rs.next()) {
				//결과값을 맵에 저장
				Map<String, Object> map = new HashMap<String, Object>();
				//컬럼값에 따라 데이터타입을 지정해서 저장
				map.put("tag", rs.getString("tag"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getInt("cash"));
				map.put("memo", rs.getString("memo"));
				map.put("cashDate", rs.getString("cashDate"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	//해시태그 많은순..

}
