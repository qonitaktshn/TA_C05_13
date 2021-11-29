package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    UserModel updateUser(UserModel user);
    String encrypt(String password);
    List<UserModel> getListUser();
    UserModel getUserByUsername(String username);
}
