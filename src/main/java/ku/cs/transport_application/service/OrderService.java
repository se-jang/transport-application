package ku.cs.transport_application.service;

import ku.cs.transport_application.entity.Order;
import ku.cs.transport_application.entity.User;
import ku.cs.transport_application.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static ku.cs.transport_application.common.UserRole.COMPANY;
import static ku.cs.transport_application.common.UserRole.CUSTOMER;

public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByUser(User user) {
        if (user.getUserRole()== COMPANY) {
            return orderRepository.findByCompanyId(user.getId());
        } else if (user.getUserRole() == CUSTOMER) {
            return orderRepository.findByCustomerId(user.getId());
        } else {
            return null;
        }
    }
}