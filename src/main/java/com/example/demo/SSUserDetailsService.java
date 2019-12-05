package com.example.demo;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public SSUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try {
            User user = userRepository.findByUsername(username);
            if (user==null){
                return null;
            }
        }return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(user)
        );

    }

}
