package com.fdxsoft.lucas.controllers;

import com.fdxsoft.lucas.models.UserModel;
import com.fdxsoft.lucas.services.UserService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    //A partir de aqui, empiezan los metodos que retornan datos fijos
    @GetMapping("/test-text")
    public String test(){
        return "TEST";
    }

    @GetMapping("fruit-list")
    public List<String> myFruitList(){
        List<String> fruits = new ArrayList<>();

        fruits = List.of("Naranja","Piña", "Durazno","Uva", "Mandarina", "Fresa",
                        "Toronja", "Ponche", "Raiz", "Manzana", "Limon", "Lina-Limon");
        return fruits;
    }

    @RequestMapping(value = "get-user")
    public UserModel getUser(){
        UserModel userModel = new UserModel();
        userModel.setName("Federico");
        userModel.setLastname("Martinez");
        userModel.setEmail("fedemtzesc@hotmail.com");
        userModel.setPhone("+528110099414");
        return userModel;
    }

    @RequestMapping(value = "get-user-by-id/{id}")
    public UserModel getUserByFixedId(@PathVariable Long id){
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setName("Alicia");
        userModel.setLastname("Martinez");
        userModel.setEmail("pdjalicia@gmail.com");
        userModel.setPhone("+528110099666");
        return userModel;
    }

    @RequestMapping(value = "user-list")
    public List<UserModel> getUserList(){
        List<UserModel> users = new ArrayList<>();
        for(int i=1;i<=10;i++){
            UserModel userModel = new UserModel();
            userModel.setId(Long.parseLong(String.valueOf(i)));
            userModel.setName("Name" + i);
            userModel.setLastname("LastName" + i);
            userModel.setEmail("user"+i+"@hotmail.com");
            userModel.setPhone("+5281100994("+i+")");
            users.add(userModel);
        }
        return users;
    }

    // A partir de Aqui empiezan los metodos que accesan a la BD
    @RequestMapping(value = "users")
    public List<UserModel> getUserLList(){
        return userService.getUsers();
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
    public boolean deleteUserByID(@PathVariable Long id){
        return userService.deleteUserByID(id);
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public UserModel addNewUser(@RequestBody UserModel newUser){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, newUser.getPassword());
        newUser.setPassword(hash);
        return userService.addNewUser(newUser);
    }


}
