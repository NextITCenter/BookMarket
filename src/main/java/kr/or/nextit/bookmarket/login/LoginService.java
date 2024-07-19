package kr.or.nextit.bookmarket.login;

import kr.or.nextit.bookmarket.member.MemberVO;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	private final LoginMapper mapper;
	public LoginService(LoginMapper mapper) {
		this.mapper = mapper;
	}

	public MemberVO findByEmailAndPassword(LoginVO login) {
		return mapper.findByEmailAndPassword(login);
	}
}
