package kr.or.nextit.bookmarket.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public void insertMember(MemberRequest member) {
        String password = member.getPassword();
        String encodePassword = encoder.encode(password);
        member.setPassword(encodePassword);
        mapper.insertMember(member);
    }

    public void updatePassword(MemberRequest member) {
        String password = member.getPassword();
        String encodePassword = encoder.encode(password);
        member.setPassword(encodePassword);
        mapper.updatePassword(member);
    }

}
