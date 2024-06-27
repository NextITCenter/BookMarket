package login;

import common.MyBatisSession;

public class LoginService {
	private final LoginMapper mapper;
	
	private static LoginService instance = new LoginService();
	private LoginService () {
		mapper = MyBatisSession.session.getMapper(LoginMapper.class);
	}
	public static LoginService getInstance() {
		return instance;
	}
	
	public MemberVO findByEmailAndPassword(LoginVO login) {
		return mapper.findByEmailAndPassword(login);
	}
}
