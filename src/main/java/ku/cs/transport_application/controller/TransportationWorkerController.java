package ku.cs.transport_application.controller;


import ku.cs.transport_application.service.OrderService;
import ku.cs.transport_application.service.TransportationWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transportation-workers")
public class TransportationWorkerController {

    @Autowired
    private TransportationWorkerService transportationWorkerService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getTransportationWorkers() {
        return ResponseEntity.ok(transportationWorkerService.getAvailableWorker());
    }

    @GetMapping("/{workerId}")
    public ResponseEntity<?> getTransportationWorkerDetails(@PathVariable UUID workerId) {
        return ResponseEntity.ok(orderService.getOrdersByWorker(workerId));
    }

    @PostMapping("/{workerId}/add-orders")
    public ResponseEntity<?> addOrderToWorker(@PathVariable UUID workerId, @RequestBody UUID orderId) {
        try {
            orderService.upDateOrderToWorker(workerId, orderId);
            return ResponseEntity.ok("Order added to worker successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
