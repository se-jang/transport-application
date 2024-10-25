package ku.cs.transport_application.service;

import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.common.ProductType;
import ku.cs.transport_application.entity.*;
import ku.cs.transport_application.repository.OrderLineRepository;
import ku.cs.transport_application.repository.OrderRepository;
import ku.cs.transport_application.repository.ProductRepository;
import ku.cs.transport_application.repository.UserRepository;
import ku.cs.transport_application.request.OrderRequest;
import ku.cs.transport_application.request.ProductDetailRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void createOrder(OrderRequest request){
        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setCustomerAddress(request.getCustomerAddress());
        order.setStatus(OrderStatus.UNCHECK);
        order.setDate(LocalDateTime.now());

        User user = userRepository.findByUsername(request.getUsername());
        order.setUser(user);

        orderRepository.save(order);

        for ( ProductDetailRequest productDetail: request.getProductDetails()) {
            Product product = new Product();
            String productName = productDetail.getProductName();
            ProductType productType = productDetail.getProductType();
            Integer quantity = productDetail.getQuantity();

            product.setName(productName);
            product.setType(productType);

            OrderLine orderLine = new OrderLine();
            orderLine.setOrder(order);
            orderLine.setProduct(product);
            orderLine.setQuantity(quantity);

            OrderLineKey orderLineKey = new OrderLineKey();
            orderLineKey.setOrderId(order.getId());
            orderLineKey.setProductId(product.getId());

            orderLine.setId(orderLineKey);

            orderLineRepository.save(orderLine);
            productRepository.save(product);
        }
    }
}
