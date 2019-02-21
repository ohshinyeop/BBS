package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	

	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "dhdh959595@@";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			System.out.println(conn);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			System.out.println("pstmt : "+ pstmt);
			//////
			rs = pstmt.executeQuery();
			System.out.println("rs : "+ rs);
			//////
			
			if(rs.next()) {
				
				System.out.println(rs.getString(1));
				System.out.println(userPassword);
				
				if(rs.getString(1).equals(userPassword)) {
					return 1;
				}
				else {
					System.out.println("비밀번호 불일치");
					return 0; //비밀번호 불일치
				}
			}
			return -1; //아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;  //데이터베이스 오류
	}
}
