package org.softuin.mobilele.service.impl;

import org.softuin.mobilele.model.entity.UserEntity;
import org.softuin.mobilele.model.entity.UserRoleEntity;
import org.softuin.mobilele.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

//this is not annotated with service since we have @bean UserDetailsService in the security
public class MobileleUserDetailService implements UserDetailsService {


    private final UserRepository userRepository;

    public MobileleUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //TODO
        return userRepository.findByEmail(email)
                .map(MobileleUserDetailService::map)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + email + " not found"));
    }

    private static UserDetails map(UserEntity userEntity){

        return User.withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(userEntity.getUserRoles().stream().map(MobileleUserDetailService::map).toList())
                .build();
    }

    private static GrantedAuthority map(UserRoleEntity userRoleEntity){

        return  new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());


    }
}
