package kr.or.nextit.bookmarket.common;

import kr.or.nextit.bookmarket.member.MemberMapper;
import kr.or.nextit.bookmarket.member.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO member = mapper.selectMemberByEmail(username);
        // UserDetails는 User 클래스로부터 만든다.
        return User.withUsername(username).password(member.getPassword()).authorities(member.getAuthList()).build();
    }
}
