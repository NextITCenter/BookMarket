package kr.or.nextit.bookmarket.login;

import kr.or.nextit.bookmarket.member.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	MemberVO findByEmailAndPassword(LoginVO login);
}
