package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> getListUser();
}
