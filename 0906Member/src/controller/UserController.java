package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import service.UserService;
import service.UserServiceImpl;
import vo.Member;

// /user 가 포함되어 있는 URL을 처리할 수 있는 Controller를 생성 
@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;

	public UserController() {
		super();
		userService = UserServiceImpl.sharedInstance();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 요청 경로에서 프로젝트 경로를 제거
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);

		// 세션을 사용하는 경우가 많으므로 여기서 세션 생성
		HttpSession session = request.getSession();
		// command가 콘솔에 출력이 안되면
		// url 제대로 처리하지 못하는 것입니다.
		// form에서 전송할 때 사용할 URL과 Sevlet이 처리하는 URL이 같은지 확인
		System.out.println(command);
		
		RequestDispatcher dispatcher =null;
		switch (command) {
		case "user/login":
			// 서비스 메소드 호출
			Member user = userService.login(request);

			// 로그인 실패한 경우
			if (user == null) {
				session.setAttribute("user", user);
				session.setAttribute("msg", "이메일 혹은 비밀번호가 틀렸습니다.");
			}
			// 로그인 성공
			else {
				session.setAttribute("user", user);
			}

			// 로그인 성공여부에 관계없이 메인 페이지로 리다이렉트
			// 현재 요청이 user/login 이므로 현재위치는 user
			// 메인으로 가려면 user의 상위로 이동해야 합니다.
			response.sendRedirect("../");
			break;
		case "user/logout":

			// 세션을 초기화
			session.invalidate();
			// 시작 페이지로 리다이렉트
			response.sendRedirect("../");
			break;

		case "user/register":
			// 단순 페이지 이동
			 dispatcher = request.getRequestDispatcher("../member/register.jsp");
			dispatcher.forward(request, response);
			break;
			
	
		case "user/insert" :
			//이 문장이 안나오면 링크 확인 
			System.out.println("회원가입 처리");
			boolean r =userService.insertMember(request);
			
			if(r == true) {
				//회원가입에 성공했을 때 처리 
				session.setAttribute("msg", "회원가입에 성공하셨습니다.");
			response.sendRedirect("../");
			}else {
				//회원가입에 실패했을 때 처리 
				session.setAttribute("registermsg","회원가입에 실패하셨습니다. 다시 시도해 주세요.");
				response.sendRedirect("register");
			}
			break;
			
		case "user/emailcheck" : 
			boolean result = userService.emailCheck(request);
			
			//json으로 출력할 때는 리턴받은 데이터를 바로 저장하지 말고 
			//JSON 객체로 변환해서 저장 
			
			JSONObject obj = new JSONObject();
			obj.put("result", result);
			
			//데이터를 저장
			
			request.setAttribute("result", obj);
			
			//결과 페이지로 포워딩 - 리다이렉트로 가능  
			
			dispatcher = request.getRequestDispatcher("/member/emailcheck.jsp"); 
			dispatcher.forward(request, response);
			
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
