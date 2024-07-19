package kr.or.nextit.bookmarket.member;

import lombok.Data;

@Data
public class MemberRequest {
    private String email;
    private String password;
    private String name;
}
