package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import util.JDBCUtil;
import vo.MemberVO;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	final String sql_insert = "INSERT INTO MEMBER VALUES((SELECT NVL(MAX(MPK),0)+1 FROM MEMBER),?,?)"; // 크롤링하여 받아온 데이터																					// 모두저장
	
	final String sql_delete = "DELETE FROM MEMBER WHERE MPK=?"; // 사용 x
	final String sql_update = "UPDATE MEMBER SET SCORE=? WHERE MPK=?"; // 예매횟수 증가 sql
	final String sql_selectOne = "SELECT * FROM MEMBER WHERE MPK=?"; 
	
	final String sql_selectAll = "SELECT * FROM MEMBER ORDER BY MPK ASC";// 영화제목 영화장르 검색 or 전체목록 출력
	
	public boolean insert(MemberVO vo) {// 받아온 객체를
		conn = JDBCUtil.connect(); // 드라이버에 연결해주고
		try {
			pstmt = conn.prepareStatement(sql_insert);// sql insert 쿼리문에
			
			pstmt.setString(1, vo.getName()); // 영화포스터
			pstmt.setInt(2, vo.getScore()); // 장르
			

			pstmt.executeUpdate();// 넣어준다/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn); // 드라이버 연결 해제 해주고
		}
		return true;

	}

	public boolean update(MemberVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_update); // sql update에
			pstmt.setInt(1, vo.getScore());
			pstmt.setInt(2, vo.getMpk());
			int res = pstmt.executeUpdate();
			if (res == 0) {
				System.out.println("로그 : update 실패");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean delete(MemberVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setInt(1, vo.getMpk());
			int res = pstmt.executeUpdate();
			if (res == 0) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public MemberVO selectOne(MemberVO vo) {
		conn = JDBCUtil.connect();
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql_selectOne);
			pstmt.setInt(1, vo.getMpk());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				MemberVO data = new MemberVO();
				data.setMpk(rs.getInt("MPK"));
				data.setName(rs.getString("NAME"));
				data.setScore(rs.getInt("SCORE"));
				
				return data;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JDBCUtil.disconnect(pstmt, conn);
		}
	}

	public ArrayList<MemberVO> selectAll(MemberVO vo) {
		ArrayList<MemberVO> datas = new ArrayList<MemberVO>();
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_selectAll);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO data = new MemberVO();
				data.setMpk(rs.getInt("MPK"));
				data.setName(rs.getString("NAME"));
				data.setScore(rs.getInt("SCORE"));
				
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datas;
	}

	
}
