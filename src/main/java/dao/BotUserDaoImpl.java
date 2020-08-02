package dao;

import domain.model.BotUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotUserDaoImpl implements BotUserDao {
    private static final Map<Integer, BotUser> users = new HashMap<>();

    @Override
    public BotUser findById(int id) {
        return users.get(id);
    }

    @Override
    public void save(BotUser user) {
        users.put(user.getId(), user);
    }

    @Override
    public void update(BotUser user) {
        users.put(user.getId(), user);
    }

    @Override
    public void delete(BotUser user) {
        users.remove(user.getId());
    }

    @Override
    public List<BotUser> findAll() {
        return new ArrayList<>(users.values());
    }
}