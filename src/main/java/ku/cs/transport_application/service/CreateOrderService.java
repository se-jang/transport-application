package ku.cs.transport_application.service;

import ku.cs.transport_application.entity.Order;
import ku.cs.transport_application.entity.OrderLine;
import ku.cs.transport_application.entity.Product;
import ku.cs.transport_application.entity.User;
import ku.cs.transport_application.repository.OrderLineRepository;
import ku.cs.transport_application.repository.OrderRepository;
import ku.cs.transport_application.repository.UserRepository;
import ku.cs.transport_application.request.OrderRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CreateOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void createOrder(OrderRequest request){
        Order record = modelMapper.map(request, Order.class);
        User customer = userRepository.findByUsername(request.getCustomerUsername());
        record.setCustomer(customer);
        orderRepository.save(record);

        OrderLine orderLine = new OrderLine();
        orderLine.setOrder(record);

        for (Map.Entry<Product, Integer> entry : request.getProductQuantities().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            orderLine.setOrder(record);
            orderLine.setProduct(product);
            orderLine.setQuantity(quantity);

            orderLineRepository.save(orderLine);
        }

        orderLineRepository.save(orderLine);
    }
}
