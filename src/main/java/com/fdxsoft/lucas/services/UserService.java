package com.fdxsoft.lucas.services;

import com.fdxsoft.lucas.models.UserModel;
import com.fdxsoft.lucas.repositories.IUserRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    IUserRepository userRepository;

    public List<UserModel> getUsers() {
        return (List<UserModel>) userRepository.findAll();
    }

    public boolean deleteUserByID(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UserModel addNewUser(UserModel newUser) {
        return userRepository.save(newUser);
    }

    public UserModel getLogin(UserModel userModel){
        UserModel userFind = userRepository.findByEmail(userModel.getEmail());
        if(userFind==null){
            return null;
        }else{
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hashedPwd = userFind.getPassword();

            if(argon2.verify(hashedPwd, userModel.getPassword())){
                return userFind;
            }else{
                return null;
            }
        }
    }
}
