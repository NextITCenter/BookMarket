package kr.or.nextit.bookmarket.member;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MemberVO {
	private String id;
	private String email;
	private String password;
	private String name;
	private LocalDate registerDate;
	private LocalDate modifiedDate;
	private List<AuthVO> authList;
}
