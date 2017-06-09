package id.co.antin.security;

import id.co.antin.persistence.domain.UserSecurity;
import id.co.antin.persistence.repository.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antin on 4/14/17.
 */
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    UserSecurityRepository userSecurityRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurity user = userSecurityRepository.findByUsername(username);

        if (null == user) throw new UsernameNotFoundException("Bad Credential");

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new User(
                user.getUsername(),
                user.getPassword(),
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.TRUE,
                simpleGrantedAuthorities
        );
    }
}
