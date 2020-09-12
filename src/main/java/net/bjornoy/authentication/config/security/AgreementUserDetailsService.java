package net.bjornoy.authentication.config.security;

import net.bjornoy.authentication.access.AccessService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class AgreementUserDetailsService implements UserDetailsService {

    private final AccessService accessService;

    public AgreementUserDetailsService(AccessService accessService) {
        this.accessService = accessService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccessService.Person person = accessService.getPerson(username);
        return new User(username, "N/A", List.of(new SimpleGrantedAuthority("ROLE_" + person.getProductCode().name())));
    }
}
