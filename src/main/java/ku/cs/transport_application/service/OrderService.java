package ku.cs.transport_application.service;

import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.entity.Order;
import ku.cs.transport_application.entity.OrderLine;
import ku.cs.transport_application.entity.TransportationWorker;
import ku.cs.transport_application.entity.User;
import ku.cs.transport_application.repository.OrderLineRepository;
import ku.cs.transport_application.repository.OrderRepository;
import ku.cs.transport_application.repository.TransportationWorkerRepository;
import ku.cs.transport_application.repository.UserRepository;
import ku.cs.transport_application.request.OrderRequest;
import ku.cs.transport_application.request.ProductDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private OrderLineRepository orderLineRepository;

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

    public List<Order> getUploaded() {
        return orderRepository.findByStatus(UPLOADED);
    }

    public List<Order> getComplete() {
        return orderRepository.findByStatus(COMPLETED);
    }

    public List<Order> getNotUncheckOrder() {
        List<Order> notUncheckOrders = orderRepository.findAll();
        return notUncheckOrders.stream()
                .filter(order -> !(order.getStatus() == UNCHECK))
                .collect(Collectors.toList());
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public OrderRequest getOrderDetail(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        List<OrderLine> orderLines = orderLineRepository.findByOrderId(orderId);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setDate(order.getDate());
        orderRequest.setCustomerName(order.getCustomerName());
        orderRequest.setCustomerAddress(order.getCustomerAddress());
        orderRequest.setWorkerUsername(order.getWorker().getUsername());
        orderRequest.setStatus(order.getStatus());

        orderRequest.setDeliveredDate(order.getDeliveredDate());

        List<ProductDetailRequest> productDetails = orderLines.stream()
                .map(orderLine -> {
                    ProductDetailRequest productDetailRequest = new ProductDetailRequest();
                    productDetailRequest.setId(orderLine.getProduct().getId());
                    productDetailRequest.setProductName(orderLine.getProduct().getName());
                    productDetailRequest.setProductType(orderLine.getProduct().getType());
                    productDetailRequest.setQuantity(orderLine.getQuantity());
                    return productDetailRequest;
                })
                .collect(Collectors.toList());

        orderRequest.setProductDetails(productDetails);

        return orderRequest;
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

    public Resource getShipmentDoc(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        String dir = order.getShipmentDocDir();
        if (dir == null || dir.isEmpty()) {
            throw new IllegalArgumentException("Shipment document not found for this order");
        }

        try {
            Path filePath = Paths.get(dir).toAbsolutePath().normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new IllegalArgumentException("File not found at path: " + dir);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Error retrieving file: " + dir, ex);
        }
    }

}