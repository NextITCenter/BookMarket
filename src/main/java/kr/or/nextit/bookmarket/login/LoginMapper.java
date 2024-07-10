package kr.or.nextit.bookmarket.login;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	MemberVO findByEmailAndPassword(LoginVO login);
}
