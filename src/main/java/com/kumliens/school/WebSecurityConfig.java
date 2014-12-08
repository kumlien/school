package com.kumliens.school;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kumliens.school.user.DBUserDetailsService;

/**
 * Contains the security configuration for the web resources.
 * We use our own {@link UserDetailsService} (the {@link DBUserDetailsService})
 * We use the {@link BCryptPasswordEncoder} from spring. 
 * Override {@link #configure(AuthenticationManagerBuilder)} and tell the 
 * security framework to use those two.
 * 
 * @author svante
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public abstract class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired PasswordEncoder passwordEncoder;
	
	@Autowired UserCache userCache;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		return bCryptPasswordEncoder;
	}
	
	@Bean
	public UserCache userCache() throws Exception {
		Cache cache = new ConcurrentMapCache("UserDetailsCache");
		return new SpringCacheBasedUserCache(cache);
	}
	
	

	/*
	 * Holy crap... must be possible to solve this smarter. 
	 * set the userDetailsService (ok)
	 * set the password encoder we use (ok)
	 * set user cache implementation and tell the providerManager to not erase credentials after authentication (looks like crap)
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		 DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsService> daoAuthConfig = auth.userDetailsService(userDetailsService);
		 daoAuthConfig.passwordEncoder(passwordEncoder);
		 
		 auth.objectPostProcessor(new ObjectPostProcessor<Object>() {
			@Override
			public <O> O postProcess(O object) {
				if(object instanceof ProviderManager) {
					((ProviderManager)object).setEraseCredentialsAfterAuthentication(false);
					List<AuthenticationProvider> daoProviders = ((ProviderManager)object).getProviders().stream().filter(provider -> {
						return provider.getClass().isAssignableFrom(DaoAuthenticationProvider.class);
					}).collect(Collectors.toList());
					if(!daoProviders.isEmpty()) {
						((AbstractUserDetailsAuthenticationProvider)daoProviders.get(0)).setUserCache(userCache);
					}
				} else {
					throw new RuntimeException("WTF, we want to be able to post process the ProviderManager!");
				}
				return object;
			}
		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated();
		http.httpBasic();
		http.csrf().disable();
	}
}
