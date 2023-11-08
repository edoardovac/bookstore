package com.assignment.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assignment.bookstore.BookstoreApplication;
import com.assignment.bookstore.domain.AppUser;
import com.assignment.bookstore.domain.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final AppUserRepository repository;
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	@Autowired
	public UserDetailServiceImpl(AppUserRepository userRepository) {
		this.repository = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	AppUser curruser = repository.findByUsername(username);
    	//log.info("username is :" + curruser.getUsername());
    	//log.info("pwHash is :" + curruser.getPasswordHash());
    	//curruser.setPasswordHash("$2a$12$N/yX/WDXAj28j.6SOgnmmefKckgQ1E2Ot92VVpXaJPnvhzmvmNe7O");
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    } 

}
