package login;

public interface LoginMapper {
	MemberVO findByEmailAndPassword(LoginVO login);
}
