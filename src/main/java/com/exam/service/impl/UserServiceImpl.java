package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //creating user
    @SuppressWarnings("null")
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

       // throw new UnsupportedOperationException("Unimplemented method 'createUser'");

      User local = this.userRepository.findByUsername(user.getUsername());  // findByUserName() function of this class 
      //Checking if user of the same name already exist or not
      if(local!=null){
        System.out.println("User is already there");
        throw new Exception("User already present!");

      } else{
        //create user
        for(UserRole ur:userRoles){
            roleRepository.save(ur.getRole());
        }
        user.getUserRoles().addAll(userRoles);
        local = this.userRepository.save(user);
        
      }

       return null;
    }

    //getting user by username
    @Override
    public User getUser(String username) {
      // TODO Auto-generated method stub
      //throw new UnsupportedOperationException("Unimplemented method 'getUser'");
      return this.userRepository.findByUsername(username);
    }

    @SuppressWarnings("null")
    @Override
    public void deleteUser(Long userId) {
      // TODO Auto-generated method stub
      
     this.userRepository.deleteById(userId);
    }
 
}
