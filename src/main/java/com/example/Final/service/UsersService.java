package com.example.Final.service;


import com.example.Final.model.UserModel;
import com.example.Final.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {



    private UsersRepository usersRepository;
    public UsersService(UsersRepository usersRepository)
    {
        this.usersRepository=usersRepository;
    }

    public UserModel registerUser(String login ,String password,String email){
        if (login == null || password == null) {
            return null;
        } else {
            UserModel userModel = new UserModel();
            userModel.setEmail(email);
            userModel.setLogin(login);
            userModel.setPassword(password);
            return usersRepository.save(userModel);

        }
    }

    public UserModel authenticate(String login,String password)
    {
        return  usersRepository.findByLoginAndPassword(login,password).orElse(null);
    }
}
