package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.UserModel;
import apap.tugasakhir.sibusiness.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDB userDb;


    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user. setPassword(pass);
        System.out.println(user.getRole());
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<UserModel> getListUser() {
        return userDb.findAll();
    }
}
