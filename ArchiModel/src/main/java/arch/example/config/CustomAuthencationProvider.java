package arch.example.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import arch.example.model.Person;
import arch.example.model.UserService;

@Component
public class CustomAuthencationProvider implements AuthenticationProvider {
	private UserService dao = new UserService();
    @Override
    public Authentication authenticate(Authentication authentication) 
                                          throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person myUser = dao.findByUsername(userName);
        if (myUser == null) {
            throw new BadCredentialsException("Unknown user "+userName);
        }
        if (!password.equals(myUser.getPassword())) {
            throw new BadCredentialsException("Bad password");
        }
        UserDetails principal = User.builder()
                .username(myUser.getLogin())
                .password(myUser.getPassword())
                .roles(myUser.getRole())
                .build();
        return new UsernamePasswordAuthenticationToken(
                principal, password, principal.getAuthorities());
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}