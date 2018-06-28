package test.wicket.model.dao;

import test.wicket.model.pojo.UserModel;

import java.util.List;

public interface UserModelDao {
    List<UserModel> listUsers();

    UserModel userById(int id);

    boolean addUser(UserModel userModel);

    boolean updateUser(UserModel userModel);

    boolean deleteUser(UserModel userModel);
}
