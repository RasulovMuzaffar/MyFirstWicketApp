package test.wicket.model.dao;

import test.wicket.model.pojo.UserModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserModelDaoImpl implements UserModelDao, Serializable {

    static List<UserModel> list = new ArrayList<>();

    public void initializeList() {
        list.add(new UserModel(1, "ivan", "ivanov", 20));
        list.add(new UserModel(2, "petr", "petrov", 25));
        list.add(new UserModel(3, "ivan", "petrov", 23));
    }

    @Override
    public List<UserModel> listUsers() {
        return list;
    }

    @Override
    public UserModel userById(int id) {
        for (UserModel um : list) {
            if (um.getId() == id) {
                return um;
            }
        }
        return null;
    }

    @Override
    public boolean addUser(UserModel userModel) {
        list.add(userModel);
        return true;
    }

    @Override
    public boolean updateUser(UserModel userModel) {
        System.out.println(userModel.toString());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == userModel.getId()) {
                list.set(i, userModel);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(UserModel userModel) {
        if (list.contains(userModel)) {
            list.remove(userModel);
            return true;
        } else {
            return false;
        }
    }
}
