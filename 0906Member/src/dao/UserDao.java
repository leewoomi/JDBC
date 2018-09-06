package dao;

import vo.Member;

//데이터베이스 작업을 위한 메소드를 선언할 인터페이스 
public interface UserDao {
	
	// 로그인 처리를 위한 메소드
	// 매개변수는 아이디와 비밀번호 -> 하나로 묶어서 받음.
	// 처리결과는 아이디와 기타 필요한 정보 -> 하나로 묶어서 리턴
	public Member login(Member member);

}
