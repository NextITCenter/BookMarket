package kr.or.nextit.bookmarket.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberVO selectMemberByEmail(String email);
    void insertMember(MemberRequest member);
    void updatePassword(MemberRequest member);
}
