package ku.cs.transport_application.service;

import ku.cs.transport_application.entity.Order;
import ku.cs.transport_application.entity.User;
import ku.cs.transport_application.repository.OrderRepository;
import ku.cs.transport_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ku.cs.transport_application.common.UserRole.COMPANY;
import static ku.cs.transport_application.common.UserRole.CUSTOMER;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> getOrdersByUser(UUID id) {
        Optional<User> recordOptional = userRepository.findById(id);
        if (recordOptional.isPresent()) {
            User record = recordOptional.get();

            if (record.getUserRole()== COMPANY) {
                return orderRepository.findByCompanyId(record.getId());
            } else if (record.getUserRole() == CUSTOMER) {
                return orderRepository.findByCustomerId(record.getId());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}