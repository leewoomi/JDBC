package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Buy;

/**
 * Servlet implementation class DBController
 */
@WebServlet("*.db")
public class DBController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DBController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청 주소에서 공통된 부분을 제외한 부분을 추출
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);

		switch (command) {
		case "mysql.db":
			// 드라이버 클래스 로드
			try {
				// Class.forName("com.mysql.jdbc.Driver");
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 데이터 베이스 연결
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/woomi?characterEncoding=UTF-8&serverTimezone=UTC", "root",
						"woomi1114");
				System.out.println(conn);
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "oracle.db":
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");

				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.101:1521:xe", "user08",
						"user08");
				System.out.println(conn);
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "insert.db":

			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				// Class.forName("com.mysql.jdbc.Driver");
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 데이터 베이스 연결
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/woomi?characterEncoding=UTF-8&serverTimezone=UTC", "root",
						"woomi1114");
				System.out.println(con);
				
				pstmt = con.prepareStatement("insert into usertbl(userid, name, birthyear, addr, mobile, mdate) values(?,?,?,?,?,?)");
				
				pstmt.setString(1, "lwm");
				pstmt.setString(2,"이우미");
				pstmt.setInt(3, 1995);
				pstmt.setString(4, "서울");
				pstmt.setString(5, "01012345678");
				pstmt.setDate(6, new Date(95,10,13));
				
				//sql을 실행하고 결과 저장하기
				int result = pstmt.executeUpdate();
				if(result > 0 ) {
					System.out.println("삽입성공");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
				try {
					if (con != null) {
						con.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}

				} catch (Exception e) {

				}
			}
			break;

		case "delete.db" :

			Connection con1 = null;
			PreparedStatement pstmt1 = null;
			try {
				// Class.forName("com.mysql.jdbc.Driver");
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 데이터 베이스 연결
				con1 = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/woomi?characterEncoding=UTF-8&serverTimezone=UTC", "root",
						"woomi1114");
				System.out.println(con1);
				
				pstmt1 = con1.prepareStatement("delete from usertbl where userid=?");
				
				pstmt1.setString(1, "lwm");
			
				
				//sql을 실행하고 결과 저장하기
				int result1 = pstmt1.executeUpdate();
				if(result1 > 0 ) {
					System.out.println("삭제성공");
				}else {
					System.out.println("삭제실패");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
				try {
					if (con1 != null) {
						con1.close();
					}
					if (pstmt1 != null) {
						pstmt1.close();
					}

				} catch (Exception e) {

				}
			}
			break;
		case "selectlist.db" :

			Connection con2 = null;
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			//select 구문은 결과를 저장할 변수가 필요 
			List<Buy> list =new ArrayList<Buy>();
			try {
				// Class.forName("com.mysql.jdbc.Driver");
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 데이터 베이스 연결
				con2 = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/woomi?characterEncoding=UTF-8&serverTimezone=UTC", "root",
						"woomi1114");
				//System.out.println(con2);
				
				pstmt2 = con2.prepareStatement("select * from buytbl");
				
			
			
				
				//sql을 실행하고 결과 저장하기
				rs2 = pstmt2.executeQuery();
				
				//리턴된 데이터를 읽어서 list에 저장 
				while(rs2.next()) {
					Buy buy = new Buy();
					buy.setNum(rs2.getInt("num"));
					buy.setUserid(rs2.getString("userid"));
					buy.setProductname(rs2.getString("productname"));
					buy.setPrice(rs2.getInt("price"));
					buy.setAmount(rs2.getInt("amount"));
					
					list.add(buy);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
				try {
					if(rs2 !=null) {rs2.close();}
					if (con2 != null) {
						con2.close();
					}
					if (pstmt2 != null) {
						pstmt2.close();
					}

				} catch (Exception e) {

				}
			}
			//System.out.println(list);
		for(Buy buy : list) {
			System.out.println(buy);
		}
			break;
			
		case "selectone.db" :

			Connection con3 = null;
			PreparedStatement pstmt3 = null;
			ResultSet rs3 = null;
			//데이터 1개를 리턴하는 경우는 변수만 생성 
			Buy buy = null;
			try {
				// Class.forName("com.mysql.jdbc.Driver");
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 데이터 베이스 연결
				con3 = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/woomi?characterEncoding=UTF-8&serverTimezone=UTC", "root",
						"woomi1114");
				//System.out.println(con2);
				
				pstmt3 = con3.prepareStatement("select * from buytbl where num=?");
				
				String num = request.getParameter("num");
				//문자열을 정수로 변환해서  ?에 바인딩
				pstmt3.setInt(1, Integer.parseInt(num));
				
			
				
				//sql을 실행하고 결과 저장하기
				rs3 = pstmt3.executeQuery();
				
				//리턴된 데이터를 읽어서 list에 저장 
				if(rs3.next()) {
					buy = new Buy();
					buy.setNum(rs3.getInt("num"));
					buy.setUserid(rs3.getString("userid"));
					buy.setProductname(rs3.getString("productname"));
					buy.setPrice(rs3.getInt("price"));
					buy.setAmount(rs3.getInt("amount"));
					
				
				}
			
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
				try {
					if(rs3 !=null) {rs3.close();}
					if (con3 != null) {
						con3.close();
					}
					if (pstmt3 != null) {
						pstmt3.close();
					}

				} catch (Exception e) {

				}
			}
	System.out.println(buy);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
