package kr.or.nextit.bookmarket.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(MemberRequest member);
    void updatePassword(MemberRequest member);
}
