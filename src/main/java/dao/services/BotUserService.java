package dao.services;

import dao.BotUserDao;
import dao.BotUserDaoImpl;
import domain.model.BotUser;

import java.util.List;

public class BotUserService {
    private BotUserDao userDao = new BotUserDaoImpl();

    public BotUser findUser(int id){
        return userDao.findById(id);
    }

    public void saveUser(BotUser user) {
        userDao.save(user);
    }

    public void updateUser(BotUser user) {
        userDao.update(user);
    }

    public void deleteUser(BotUser user) {
        userDao.delete(user);
    }

    public List<BotUser> findAllUsers() {
        return userDao.findAll();
    }
}
