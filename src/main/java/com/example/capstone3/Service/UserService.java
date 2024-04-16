package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer userId , User user){
        User u = userRepository.findUserByUserId(userId);
        if(u==null){
            throw new ApiException("User not found");
        }
        u.setAge(user.getAge());
        u.setEmail(user.getEmail());
        u.setRating(user.getRating());
        u.setComment(user.getComment());
        u.setPassword(user.getPassword());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setRide(user.getRide());
        userRepository.save(u);
    }

    public void deleteUser(Integer userId){
        User u = userRepository.findUserByUserId(userId);
        if(u==null){
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }
}
