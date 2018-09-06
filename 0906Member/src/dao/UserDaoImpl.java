package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

//데이터베이스 처리를 위한 메소드를 선언할 인터페이스를 구현한 클래스를 생성
public class UserDaoImpl implements UserDao {

	// 데이터베이스 연결을 위한 변수
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 외부에서 인스턴스 생성을 못하도록 생성자를 private으로 설정
	private UserDaoImpl() {

		try {
			// MySQL인 경우 여기를 다르게 작성해야 함.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	// 인스턴스 1개의 주소를 저장할 수 있는 변수를 생성
	private static UserDao userDao;

	// 외부에서 인스턴스를 사용할 수 있도록 public으로
	// 인스턴스의 주소를 리턴해주는 메소드
	public static UserDao sharedInstance() {
		// 처음 한 번만 인스턴스를 생성하도록 해주는 코드
		if (userDao == null) {
			userDao = new UserDaoImpl();
		}
		return userDao;

	}

	@Override
	public Member login(Member member) {
		// 넘어온 파라미터 출력
		// email과 pw가 제대로 저장되었는지 확인
		System.out.println("넘어온 데이터 : " + member);
		Member user = null;
		try {
			try {
				// 데이터베이스 접속
				con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.101:1521:xe", "user08", "user08");
				// Statement 인스턴스를 생성하고 SQL 실행
				pstmt = con.prepareStatement("select email, name from member " + "where trim(email)=? and trim(pw)=?");
				
				// ?에 값 채우기
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPw());

				// SQL 실행
				rs = pstmt.executeQuery();

				// 결과 읽기
				// 읽은 데이터가 있으면 user에 인스턴스를 생성해서 대입
				// 읽은 데이터가 없으며 user는 null
				if (rs.next()) {
					// 인스턴스 생성하고 결과를 저장
					user = new Member();
					user.setEmail(rs.getString("email"));
					user.setName(rs.getString("name"));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {

			}
		}

		// email과 pw가 일치하는 데이터가 없으면 null이 리턴되고
		// 일치하는 데이터가 있으면 인스턴스를 생성해서 리턴
		System.out.println("리턴하는 데이터 :" + user);
		return user;

	}
}
