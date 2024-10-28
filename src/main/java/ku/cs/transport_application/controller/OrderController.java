package ku.cs.transport_application.controller;

import ku.cs.transport_application.DTO.OrderDTO;
import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.common.TransportationWorkerStatus;
import ku.cs.transport_application.service.MailSenderService;
import ku.cs.transport_application.service.OrderService;
import ku.cs.transport_application.service.TransportationWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @Autowired
    private TransportationWorkerService transportationWorkerService;

    @GetMapping("/orders/uncheck-orders")
    public ResponseEntity<List<OrderDTO>> getUncheckOrder() {
        return ResponseEntity.ok(orderService.getUncheckOrder());
    }

    @GetMapping("/orders/check-orders")
    public ResponseEntity<List<OrderDTO>> getCheckedOrder() {
        return ResponseEntity.ok(orderService.getCheckedOrder());
    }


    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getUserOrder(@PathVariable("userId") UUID userID) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userID));
    }

    @GetMapping("/orders/worker/{transportationWorkerId}")
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
            String fileName = file.getOriginalFilename();
            assert fileName != null;

            if (!fileName.endsWith(".pdf")) {
                return new ResponseEntity<>(Map.of("error", "Only PDF files are allowed"), HttpStatus.BAD_REQUEST);
            }

            orderService.uploadFile(orderId, file);

            return new ResponseEntity<>(Map.of("message", "File uploaded successfully", "fileName", fileName), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(Map.of("error", "Failed to upload file"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change-order-worker-status")
    public ResponseEntity<?> changeOrderAndWorkerStatus(@RequestParam("orderId") UUID orderId,
                                               @RequestParam("workerId") UUID workerId,
                                               @RequestParam("status") OrderStatus status,
                                               @RequestParam("workerStatus") TransportationWorkerStatus workerStatus) {
        try {
            orderService.upDateOrderStatus(orderId, status);
            transportationWorkerService.updateTransportationWorker(workerId, workerStatus);
            mailSenderService.sendEmail(orderId);
            return new ResponseEntity<>(Map.of("message", "Order status updated successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to update order status"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/orders/order-detail/{orderId}/checked-order")
    public ResponseEntity<?> changeOrderTOChecked(@PathVariable("orderId") UUID orderId,
                                               @RequestParam("status") OrderStatus status) {
        try {
            orderService.upDateOrderStatus(orderId, status);
            mailSenderService.sendEmail(orderId);
            return new ResponseEntity<>(Map.of("message", "Order status updated successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to update order status"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/order-detail/{orderId}")
    public ResponseEntity<?> getOrderDetailById(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(orderService.getOrderDetail(orderId));
    }

    @GetMapping("/orders/{orderId}/shipment-doc")
    public ResponseEntity<Resource> getShipmentDoc(@PathVariable UUID orderId) {
        Resource fileResource = orderService.getShipmentDoc(orderId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
                .body(fileResource);
    }

    @PostMapping("/orders/{orderId}/assign-worker")
    public ResponseEntity<?> assignWorkerToOrder(@PathVariable("orderId") UUID orderId, @RequestParam("workerId") UUID workerId) {
        try {
            orderService.upDateOrderToWorker(workerId, orderId);
            return new ResponseEntity<>(Map.of("message", "Worker assigned to order successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to assign worker to order"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
