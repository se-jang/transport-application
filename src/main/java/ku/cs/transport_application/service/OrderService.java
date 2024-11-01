package ku.cs.transport_application.service;

import ku.cs.transport_application.DTO.OrderDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    public List<OrderDTO> getOrdersByUser(UUID id) {
        Optional<User> recordOptional = userRepository.findById(id);
        if (recordOptional.isPresent()) {
            User record = recordOptional.get();
            if (record.getRole() == USER) {
                return orderRepository.findByUserId(record.getId()).stream()
                        .map(order -> new OrderDTO(
                                order.getId(),
                                order.getStatus(),
                                order.getCustomerName(),
                                order.getDate(),
                                order.getDeliveredDate(),
                                order.getUser().getName()
                        ))
                        .collect(Collectors.toList());
            }
        }
        return null;
    }

    public List<OrderDTO> getOrdersByWorker(UUID id) {
        Optional<TransportationWorker> recordOptional = twRepository.findById(id);
        if (recordOptional.isPresent()) {
            TransportationWorker record = recordOptional.get();
            return orderRepository.findByWorkerId(record.getId()).stream()
                    .map(order -> new OrderDTO(
                            order.getId(),
                            order.getStatus(),
                            order.getCustomerName(),
                            order.getDate(),
                            order.getDeliveredDate(),
                            order.getUser().getName()
                    ))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<OrderDTO> getUncheckOrder() {
        return orderRepository.findByStatus(UNCHECK).stream()
                .map(order -> new OrderDTO(
                        order.getId(),
                        order.getStatus(),
                        order.getCustomerName(),
                        order.getDate(),
                        order.getDeliveredDate(),
                        order.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getCheckedOrder() {
        return orderRepository.findByStatus(CHECKED).stream()
                .map(order -> new OrderDTO(
                        order.getId(),
                        order.getStatus(),
                        order.getCustomerName(),
                        order.getDate(),
                        order.getDeliveredDate(),
                        order.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOnGoing() {
        return orderRepository.findByStatus(ONGOING).stream()
                .map(order -> new OrderDTO(
                        order.getId(),
                        order.getStatus(),
                        order.getCustomerName(),
                        order.getDate(),
                        order.getDeliveredDate(),
                        order.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getDelivered() {
        return orderRepository.findByStatus(DELIVERED).stream()
                .map(order -> new OrderDTO(
                        order.getId(),
                        order.getStatus(),
                        order.getCustomerName(),
                        order.getDate(),
                        order.getDeliveredDate(),
                        order.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getUploaded() {
        return orderRepository.findByStatus(UPLOADED).stream()
                .map(order -> new OrderDTO(
                        order.getId(),
                        order.getStatus(),
                        order.getCustomerName(),
                        order.getDate(),
                        order.getDeliveredDate(),
                        order.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getComplete() {
        return orderRepository.findByStatus(COMPLETED).stream()
                .map(order -> new OrderDTO(
                        order.getId(),
                        order.getStatus(),
                        order.getCustomerName(),
                        order.getDate(),
                        order.getDeliveredDate(),
                        order.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public List<Order> getNotCompleteOrder() {
        List<Order> notUncheckOrders = orderRepository.findAll();
        return notUncheckOrders.stream()
                .filter(order -> !(order.getStatus() == COMPLETED))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrder() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderDTO(
                        order.getId(),
                        order.getStatus(),
                        order.getCustomerName(),
                        order.getDate(),
                        order.getDeliveredDate(),
                        order.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public OrderRequest getOrderDetail(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        List<OrderLine> orderLines = orderLineRepository.findByOrderId(orderId);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setDate(order.getDate());
        orderRequest.setCustomerName(order.getCustomerName());
        orderRequest.setCustomerAddress(order.getCustomerAddress());
        if (order.getWorker() != null) {
            orderRequest.setWorkerUsername(order.getWorker().getUsername());
        } else {
            orderRequest.setWorkerUsername("N/A");
        }
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

    public void uploadFile(UUID orderID, MultipartFile file) throws IOException {
        String uploadDir = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator + "uploads";
        String fileName = file.getOriginalFilename();
        assert fileName != null;

        Files.createDirectories(Paths.get(uploadDir));

        fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        File uploadedFile = new File(uploadDir + fileName);
        Path path = Paths.get(uploadDir, fileName);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setShipmentDocDir(String.valueOf(path));
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