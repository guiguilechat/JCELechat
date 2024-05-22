package fr.guiguilechat.jcelechat.programs.spring.mevetic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiConnectionInterceptor;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
		        .requestMatchers("/", "/index", "/webjars/**").permitAll()
						.requestMatchers("/h2-console/**").permitAll()
		        .anyRequest().hasAuthority(EsiConnectionInterceptor.LECHAT_AUTHORITIES))
				.oauth2Login(
						Customizer.withDefaults())
		;
		return http.build();
	}

}
