package com.example.demo.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Component
public class UserDaoService {
    private static List<User> users= new ArrayList<>();
    private static int idCount = 3;

    static {
        users.add(new User(1, "John Doe", new Date()));
        users.add(new User(2, "Alex Smith", new Date()));
        users.add(new User(3, "John Seed", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
//        if (user.getId() == null) {
//            this.idCount++;
//            user.setId(idCount);
//        }
        this.users.add(user);
        return user;
    }

    public User findOne(int id) {
        ListIterator<User> iterate = users.listIterator();
        while(iterate.hasNext()) {
            User user = iterate.next();
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
