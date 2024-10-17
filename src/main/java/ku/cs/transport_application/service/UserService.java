package ku.cs.transport_application.service;

import ku.cs.transport_application.common.UserRole;
import ku.cs.transport_application.entity.User;
import ku.cs.transport_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findByUserRoleNot(UserRole.ADMIN);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
