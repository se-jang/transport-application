package ku.cs.transport_application.controller;

import ku.cs.transport_application.DTO.OrderDTO;
import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.request.OrderRequest;
import ku.cs.transport_application.service.MailSenderService;
import ku.cs.transport_application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MailSenderService mailSenderService;

    @GetMapping("/orders/uncheck-orders")
    public ResponseEntity<List<OrderDTO>> getUncheckOrder() {
        return ResponseEntity.ok(orderService.getUncheckOrder());
    }

    @GetMapping("/orders/check-orders")
    public ResponseEntity<List<OrderDTO>> getCheckedOrder() {
        return ResponseEntity.ok(orderService.getCheckedOrder());
    }


    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<OrderDTO>> getUserOrder(@PathVariable("userId") UUID userID) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userID));
    }

    @GetMapping("/orders/{transportationWorkerId}")
    public ResponseEntity<List<OrderDTO>> getTransportationWorkerOrder(@PathVariable("transportationWorkerId") UUID transportationWorkerId) {
        return ResponseEntity.ok(orderService.getOrdersByWorker(transportationWorkerId));
    }

    @GetMapping("/orders/not-uncheck-orders")
    public ResponseEntity<?> getNotUncheckOrder() {
        return ResponseEntity.ok(orderService.getNotUncheckOrder());
    }

    @GetMapping("/orders/all-orders")
    public ResponseEntity <List<OrderDTO>> getAllOrder () {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/orders/delivered-orders")
    public ResponseEntity <List<OrderDTO>> getDelivered() {
        return ResponseEntity.ok(orderService.getDelivered());
    }

    @GetMapping("/orders/on-going-orders")
    public ResponseEntity <List<OrderDTO>> getOnGoingOrder() {
        return ResponseEntity.ok(orderService.getOnGoing());
    }

    @GetMapping("/orders/uploaded-orders")
    public ResponseEntity <List<OrderDTO>> getUploadedOrder() {
        return ResponseEntity.ok(orderService.getUploaded());
    }

    @GetMapping("/orders/completed-orders")
    public ResponseEntity <List<OrderDTO>> getCompletedOrder() {
        return ResponseEntity.ok(orderService.getComplete());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("orderId") UUID orderId, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(Map.of("error", "File is empty"), HttpStatus.BAD_REQUEST);
        }

        try {
            String uploadDir = "src/main/resources/static/images/uploads/";
            String fileName = file.getOriginalFilename();
            byte[] fileBytes = file.getBytes();

            assert fileName != null;

            if ((!fileName.endsWith(".pdf"))) {
                return new ResponseEntity<>(Map.of("error", "Only PDF files are allowed"), HttpStatus.BAD_REQUEST);
            }

            orderService.uploadFile(orderId, fileName, uploadDir);

            return new ResponseEntity<>(Map.of("message", "File uploaded successfully", "fileName", fileName), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(Map.of("error", "Failed to upload file"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change-order-status")
    public ResponseEntity<?> changeOrderStatus(@RequestParam("orderId") UUID orderId,
                                               @RequestParam("status") OrderStatus status) {
        try {
            orderService.upDateOrderStatus(orderId, status);
            mailSenderService.sendEmail(orderId);
            return new ResponseEntity<>(Map.of("message", "Order status updated successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to update order status"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrderDetail(@PathVariable("orderId") UUID orderId) {
        OrderRequest orderDetail = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(orderDetail);
    }

    @GetMapping("/orders/{orderId}/shipment-doc")
    public ResponseEntity<Resource> getShipmentDoc(@PathVariable UUID orderId) {
        Resource fileResource = orderService.getShipmentDoc(orderId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
                .body(fileResource);
    }

}
