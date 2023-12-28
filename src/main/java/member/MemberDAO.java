package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.tomcat.dbcp.dbcp2.Jdbc41Bridge;

import common.JDBCUtil;
import member.MemberDTO;

public class MemberDAO {

		// SQL 쿼리를 접근하는 객체 선언
	
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;	
		
		// sql 쿼리를 적용하는 상수 선언
		
		private final String USER_LOGIN = "select * from member where id = ? and password = ? " ;
		
		// 메소드
		
		
		public MemberDTO login (MemberDTO dto) {
			
			System.out.println("login메소드 호출");
			
			MemberDTO mem = null; 
			
			try {
				
				conn = JDBCUtil.getConnection();
				pstmt = conn.prepareStatement(USER_LOGIN);
				
				// ? 변수의 값 할당
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPassword());
				
				rs = pstmt.executeQuery() ;
				
				
				while (rs.next())  {
					
					// 리턴으로 돌려줄 dto 선언
					
					 mem = new  MemberDTO () ;
					
					mem.setId(rs.getString("ID"));
					mem.setPassword(rs.getNString("PASSWORD"));
					mem.setPhone(rs.getNString("PHONE"));
					mem.setEmail(rs.getNString("EMAIL"));
					mem.setRegdate(rs.getDate("REGDATE"));
					mem.setAddr(rs.getString("ADDR"));
					mem.setRole(rs.getString("ROLE"));
					
					System.out.println("인증성공 : 해당 ID와 PASSWORD가 정확히 일치합니다.");
				}
				
				
				
				
			} catch ( Exception e) {
			
			System.out.println("인증시 문제 발생");	
			e.printStackTrace();
				
			} finally {
				
			JDBCUtil.close(rs, pstmt, conn);
			
			}
			
			
			
			return mem;
			
		}
	
	
	
	
}
