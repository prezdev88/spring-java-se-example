package cl.prezdev.service;

import org.springframework.stereotype.Service;

import cl.prezdev.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(int id) {
        return new User(id, "User" + id);
    }
}
