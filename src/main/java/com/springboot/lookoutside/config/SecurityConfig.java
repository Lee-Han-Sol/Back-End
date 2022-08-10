package com.springboot.lookoutside.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.springboot.lookoutside.config.auth.PrincipalDetailService;

@Configuration // �� ��� (Ioc)
@EnableWebSecurity // ���� 
@EnableGlobalMethodSecurity(prePostEnabled = true) // Ư�� �ּҷ� ���� �� ���� �� ������ �̸� üũ
public class SecurityConfig {

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // Ioc
	public BCryptPasswordEncoder endodePw() {
		return new BCryptPasswordEncoder();
	}
	
	//��ť��Ƽ�� ��� �α������ִµ� pw�� �ؽ��Ǿ� �ִ� ���·� ��
	
	AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		return auth.userDetailsService(principalDetailService).passwordEncoder(endodePw()).and().build(); // �ؽ��� ��й�ȣ ��
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
	        .httpBasic().disable()
	        .cors().configurationSource(corsConfigurationSource())
	        .and()
        	.csrf().disable() // csrf ��ū ��Ȱ��ȭ (�׽�Ʈ�� ��Ȱ��ȭ)
	        .authorizeRequests()
				.antMatchers("/","/user/**","/manager/**").permitAll()// user�� ������ ��� ��� ���
				//.antMatchers("/manager/**").hasRole("ADMIN") // Admin�� ����
				.anyRequest().authenticated() // �ٸ� ��û�� ������ �Ǿ���Ѵ�.
        	.and()
        		.formLogin()
        		.successHandler(new CustomAuthenticationSuccessHandler())
        		 // .loginPage("/login") //�α��������� �ּ�
        		.loginProcessingUrl("/user/sign-in"); // ������ ��ť��Ƽ�� �ش� �ּҷ� ��û���� �α����� ����ä�� ��� �α��� ���ش�.
//        		.defaultSuccessUrl("/"); //������ ������ �ּ�
        		
        
        		
        return http.build();
    }
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
