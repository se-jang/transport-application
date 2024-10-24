package ku.cs.transport_application.controller;

import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/uncheck-order")
    public ResponseEntity<?> getUncheckOrder() {
        return ResponseEntity.ok(orderService.getUncheckOrder());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(Map.of("error", "File is empty"), HttpStatus.BAD_REQUEST);
        }

        try {
            byte[] fileBytes = file.getBytes();
            String fileName = file.getOriginalFilename();

            assert fileName != null;
            return new ResponseEntity<>(Map.of("message", "File uploaded successfully", "fileName", fileName), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(Map.of("error", "Failed to upload file"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change-order-status")
    public ResponseEntity<?> changeOrderStatus(@RequestParam("orderId") UUID orderId,
                                               @RequestParam("status") OrderStatus status) {
        try {
            boolean updated = orderService.changeOrderStatus(orderId, status);
            if (updated) {
                return new ResponseEntity<>(Map.of("message", "Order status updated successfully"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", "Order not found or status update failed"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to update order status"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<?> getOrderDetail(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(orderService.getOrdersByWorker(orderId));
    }
}
