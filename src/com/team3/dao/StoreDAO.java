package com.team3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.StoreVO;


public class StoreDAO {

		// 데이터 베이스에 접근하여 데이터를 획득하거나 설정
		// CRUD를 메서드로 구현한 클래스
		
		
		
		// Create (insert) - 회원 가입
		public int insertStoreInfo(StoreVO sVo) {
			
			//String sql_insert_pstmt = "insert into store_info values(?, ?, ?, ?, ?, ?, ?, ?, 'm', ?, ?)";
			//String sql_insert_pstmt = "insert into store_info values('btestid01', 'c:\temp\test.jpg', '섬머셋호텔', '숙박업', '평일 : 오전00:00 - 오후00:00 마감', '주말 : 오전00:00 - 오후00:00 마감', '서울특별시 종로구 통의동 000-00 ', '머셋호텔', 'm', '가능', '비고없음')";
			String sql_insert_pstmt = "insert into store_info values(?, ?, ?, ?, ?, ?, ?, ?, 'm', ?, ?)";
		
			int result = -1;
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			
			try {
				// 1. jdbc 드라이버 로드 : forName(className)
				try {
					System.out.println("point01");
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				// 2. 디비 접속을 위한 연결 객체 생성 : getConnection(url, user, password)
				System.out.println("point02");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ora_user", "1234");
		
				// 3. 쿼리문을 실행하기 위한 객체 생성
				pstmt = conn.prepareStatement(sql_insert_pstmt);
	
				pstmt.setString(1, sVo.getUserid());
				pstmt.setString(2, sVo.getStore_photo());
				pstmt.setString(3, sVo.getStore_name());
				pstmt.setString(4, sVo.getStore_type());
				pstmt.setString(5, sVo.getStore_time());
				pstmt.setString(6, sVo.getStore_time_w());
				pstmt.setString(7, sVo.getStore_addr());
				pstmt.setString(8, sVo.getStore_addr_detail());
				
				//pstmt.setString(9, sVo.getMemu_info());
				pstmt.setString(9, sVo.getParking());				
				pstmt.setString(10, sVo.getParking_detail());
			
				System.out.println("sVo.getUserid() =>"+ sVo.getUserid());
				
				// 4. 쿼리 실행 및 결과 처리
				// executeQuery(sql)	- select
				// executeUpdate(sql)	- insert update delete	
				result = pstmt.executeUpdate();
				
				System.out.println(result);
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null)		rs.close();
					if(pstmt != null)	pstmt.close();
					if (conn != null)	conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}			
			}
			
			return result;	
		}	
		
		
/*		
		// Read   (select) - 사용자 인증, 데이터 활용
		public int checkUser(String userid, String pwd){
			String sql_insert_pstmt = "select pwd, name from member where userid=?";
			
			int result = -1;
			String name;
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			
			try {
				// 1. jdbc 드라이버 로드 : forName(className)
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				// 2. 디비 접속을 위한 연결 객체 생성 : getConnection(url, user, password)
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:orcl",
						"ora_user", 
						"1234");
		
				// 3. 쿼리문을 실행하기 위한 객체 생성
				pstmt = conn.prepareStatement(sql_insert_pstmt);
				pstmt.setString(1, userid);
				
				// 4. 쿼리 실행 및 결과 처리
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
						//name = rs.getString("name");
						result = 1; 
					}
				}else {
					//
					result = 0;
					
					result = -1;
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
				if(rs != null)		rs.close();
				if(pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}			
			}
			return result; 	
			
		}
		
		public StoreVO getMember(String userid) {
			String sql_select_info = "select * from member where userid=?";
			StoreVO sVo = null;
		
			sVo = new StoreVO(); 
			
			int result = -1;
			String name;
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			
			try {
				// 1. jdbc 드라이버 로드 : forName(className)
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				// 2. 디비 접속을 위한 연결 객체 생성 : getConnection(url, user, password)
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:orcl",
						"ora_user", 
						"1234");
		
				// 3. 쿼리문을 실행하기 위한 객체 생성
				pstmt = conn.prepareStatement(sql_select_info);
				pstmt.setString(1, userid);
				
				// 4. 쿼리 실행 및 결과 처리
				rs = pstmt.executeQuery();
			
				//
				if(rs.next()) {
					sVo.setName(rs.getString("name"));
					sVo.setUserid(rs.getString("userId"));
					SVo.setPwd(rs.getString("pwd"));
					SVo.setEmail(rs.getString("email"));
					SVo.setPhone(rs.getString("phone"));
					SVo.setAdmin(rs.getInt("admin"));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
				if(rs != null)		rs.close();
				if(pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}			
			}
			return sVo; 	
			
		}
		
		// Update (update) - 회원 정보 수정
		public int updateStoreInfo(StoreVO sVo) {
			String sql_update = "update store_info set pwd=?, email=?, phone=?, admin=? where userid=?";
		
			int result = -1;
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			
			try {
				// 1. jdbc 드라이버 로드 : forName(className)
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				// 2. 디비 접속을 위한 연결 객체 생성 : getConnection(url, user, password)
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:orcl",
						"ora_user", 
						"1234");
		
				// 3. 쿼리문을 실행하기 위한 객체 생성
				pstmt = conn.prepareStatement(sql_update);
				pstmt.setString(1, sVo.getPwd());
				pstmt.setString(2, sVo.getEmail());
				pstmt.setString(3, sVo.getPhone());
				pstmt.setInt(4, sVo.getAdmin());
				pstmt.setString(5, SVo.getUserid());
				
				// 4. 쿼리 실행 및 결과 처리
				// executeQuery(sql)	- select
				// executeUpdate(sql)	- insert update delete	
				result = pstmt.executeUpdate();
				
				System.out.println(result);
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null)		rs.close();
					if(pstmt != null)	pstmt.close();
					if (conn != null)	conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}			
			}
			
			return result;	
			
			
		}
		
		// Delete (delete) - 회원 삭제
		public int deleteStore(StoreVO sVo) {
			String sql_delete = "delete from store_info where userid=?";
			
			int result = -1;
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			
			try {
				// 1. jdbc 드라이버 로드 : forName(className)
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				// 2. 디비 접속을 위한 연결 객체 생성 : getConnection(url, user, password)
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:orcl",
						"ora_user", 
						"1234");
		
				// 3. 쿼리문을 실행하기 위한 객체 생성
				pstmt = conn.prepareStatement(sql_delete);
				pstmt.setString(1, sVo.getUserid());
				
				// 4. 쿼리 실행 및 결과 처리
				// executeQuery(sql)	- select
				// executeUpdate(sql)	- insert update delete	
				result = pstmt.executeUpdate();
				
				System.out.println(result);
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null)		rs.close();
					if(pstmt != null)	pstmt.close();
					if (conn != null)	conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}			
			}
			
			return result;
		}
*/		
}

