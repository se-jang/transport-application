package ku.cs.transport_application.controller;

import ku.cs.transport_application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/uncheck-order")
    public ResponseEntity<?> getUncheckOrder() {
        return ResponseEntity.ok(orderService.getUncheckOrder());
    }
}
