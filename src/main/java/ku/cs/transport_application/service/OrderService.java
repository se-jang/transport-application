package ku.cs.transport_application.service;

import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.entity.Order;
import ku.cs.transport_application.entity.TransportationWorker;
import ku.cs.transport_application.entity.User;
import ku.cs.transport_application.repository.OrderRepository;
import ku.cs.transport_application.repository.TransportationWorkerRepository;
import ku.cs.transport_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static ku.cs.transport_application.common.OrderStatus.*;
import static ku.cs.transport_application.common.UserRole.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransportationWorkerRepository twRepository;

    public List<Order> getOrdersByUser(UUID id) {
        Optional<User> recordOptional = userRepository.findById(id);
        if (recordOptional.isPresent()) {
            User record = recordOptional.get();

            if (record.getRole() == USER) {
                return orderRepository.findByUserId(record.getId());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<Order> getOrdersByWorker(UUID id) {
        Optional<TransportationWorker> recordOptional = twRepository.findById(id);
        if (recordOptional.isPresent()) {
            TransportationWorker record = recordOptional.get();

            return orderRepository.findByWorkerId(record.getId());

        } else {
            return null;
        }
    }

    public List<Order> getUncheckOrder() {
        return orderRepository.findByStatus(UNCHECK);
    }

    public List<Order> getCheckedOrder() {
        return orderRepository.findByStatus(CHECKED);
    }

    public List<Order> getOnGoing() {
        return orderRepository.findByStatus(ONGOING);
    }

    public List<Order> getDelivered() {
        return orderRepository.findByStatus(DELIVERED);
    }

    public List<Order> getNotUncheckOrder() {
        List<Order> notUncheckOrders = orderRepository.findAll();
        return notUncheckOrders.stream()
                .filter(order -> !(order.getStatus() == UNCHECK))
                .collect(Collectors.toList());
    }

    public void upDateOrderStatus(UUID id, OrderStatus status) {
        Order record = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        record.setStatus(status);
        orderRepository.save(record);
    }

    public void upDateOrderToWorker(UUID workerId, UUID orderId) {
        Optional<TransportationWorker> recordWorkerOptional = twRepository.findById(workerId);
        Optional<Order> recordOrderOptional = orderRepository.findById(orderId);

        if (recordWorkerOptional.isPresent() && recordOrderOptional.isPresent()) {
            TransportationWorker recordWorker = recordWorkerOptional.get();
            Order recordOrder = recordOrderOptional.get();

            recordOrder.setWorker(recordWorker);
            orderRepository.save(recordOrder);
        }
    }

    public void uploadFile(UUID orderID, String fileName, String uploadDir) {
        String path = uploadDir + fileName;
        File uploadedFile = new File(path);

        if (!uploadedFile.exists()) {
            throw new IllegalArgumentException("File does not exist: " + path);
        }

        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setShipmentDocDir(path);
        orderRepository.save(order);
    }
}