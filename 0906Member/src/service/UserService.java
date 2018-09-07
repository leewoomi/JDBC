package service;

import javax.servlet.http.HttpServletRequest;

import vo.Member;

//비즈니스 로직을 처리할 메소드를 선언할 인터페이스
//여기의 메소드는 클라이언트의 요청 당 1개로 매핑이 되어야 합니다.
public interface UserService {

	// 로그인을 처리하는 메소드
	public Member login(HttpServletRequest request);

	// 회원가입을 처리하는 메소드
	public boolean insertMember(HttpServletRequest request);
	
	//이메일 중복 체크를 위한 메소드 
	public boolean emailCheck(HttpServletRequest request);
}
