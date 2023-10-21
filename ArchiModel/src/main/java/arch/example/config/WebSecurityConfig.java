package arch.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import arch.example.model.UserService;

@Configuration
@EnableWebSecurity // (debug = true)
public class WebSecurityConfig implements WebMvcConfigurer {
	@Autowired
	private CustomAuthencationProvider authencationProvider;
	@Autowired
	private UserService userDetailsService;
	public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authencationProvider)
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/").setCachePeriod(0);
	}
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/admin", "/admin/**")
		.hasRole("ADMIN")
		.antMatchers("/anonymous*")
		.anonymous()
		.antMatchers("/login*", "/index*", "/translate.html")
		.permitAll()
		.and()
		
	    .headers()
	    .frameOptions().disable() // iframe options
	    .and()

		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/perform_login")
		// .defaultSuccessUrl("/login", false)
		.failureUrl("/login.html?error=true")
		.and()
		.logout()
		.logoutUrl("/perform_logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/"); // index.html
		return http.build();
	}
}
