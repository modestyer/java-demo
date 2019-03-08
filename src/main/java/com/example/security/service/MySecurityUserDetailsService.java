package com.example.security.service;

import com.example.security.dao.UserDao;
import com.example.security.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/7
 **/
@Service
public class MySecurityUserDetailsService implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = userDao.selectOneUser(userName);
        if(users==null){
            throw new UsernameNotFoundException("user not found");
        }
        return null;
//        return new User(users.getUsername(),users.getPassword(),);
    }
}
