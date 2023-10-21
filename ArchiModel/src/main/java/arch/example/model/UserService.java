package arch.example.model;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends DAO implements UserDetailsService, CustomPersonRepository {
	private final DAO dataAccessObject = new DAO();
    private PasswordEncoder bCryptPasswordEncoder;
        
	public void save(Person user) {
    	user.setId(System.currentTimeMillis());
        String login = user.getLogin();
        switch (login) {
        case "admin" :  user.setRole("ADMIN"); break;
        case "moderator" :  user.setRole("MODERATOR"); break;
        case "user" :  user.setRole("USER");
        default : user.setRole("USER");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPosition(Integer.toString((int) Math.random()));
        save(user);
        System.out.println("User saved: " + user.toString());
    }
    public Person findByUsername(String username) {
        return dataAccessObject.findByUsername(username);
    }

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		        Person myUser = findByUsername(userName);
		        if (myUser == null) {
		            throw new UsernameNotFoundException("Unknown user: "+userName);
		        }
		        UserDetails user = User.builder()
		                .username(myUser.getLogin())
		                .password(myUser.getPassword())
		                .roles(myUser.getRole())
		                .build();
		        return user;
		    }
}