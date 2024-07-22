package kr.or.nextit.bookmarket.member;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
class AuthVO implements GrantedAuthority {
	private String memberEmail;
	private String auth;

	@Override
	public String getAuthority() {
		return auth;
	}
}
