package ku.cs.transport_application.service;

import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.entity.Order;
import ku.cs.transport_application.entity.TransportationWorker;
import ku.cs.transport_application.entity.User;
import ku.cs.transport_application.repository.OrderRepository;
import ku.cs.transport_application.repository.TransportationWorkerRepository;
import ku.cs.transport_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static ku.cs.transport_application.common.OrderStatus.CHECKED;
import static ku.cs.transport_application.common.OrderStatus.UNCHECK;
import static ku.cs.transport_application.common.UserRole.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransportationWorkerRepository twRepository;

    @Autowired
    private JavaMailSender mailSender;

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

    public void sendEmail(UUID orderId) {
        SimpleMailMessage message = new SimpleMailMessage();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        User user = userRepository.findByName(order.getCustomerName());
        String email = user.getEmail();
        String subject = String.format("Order Update: Your Order %s is now %s", orderId, order.getStatus());
        String body = String.format("Dear %s,\n\nWe would like to inform you that your order is now %s. " +
                "If you have any questions or require further assistance, please do not hesitate to contact us.\n\n" +
                "Thank you for choosing our service!\n\nBest regards,\nTransportation Application", user.getName(),order.getStatus());
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("pariyanuch.m@ku.th");

        mailSender.send(message);
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