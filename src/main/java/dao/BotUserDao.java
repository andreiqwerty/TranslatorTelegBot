package dao;

import domain.model.BotUser;

import java.util.List;

public interface BotUserDao {

    BotUser findById(int id);

    void save(BotUser user);

    void update(BotUser user);

    void delete(BotUser user);

    List<BotUser> findAll();
}