package ku.cs.transport_application.controller;

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
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MailSenderService mailSenderService;

    @GetMapping("/uncheck-order")
    public ResponseEntity<?> getUncheckOrder() {
        return ResponseEntity.ok(orderService.getUncheckOrder());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserOrder(@PathVariable("userID") UUID userID) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userID));
    }

    @GetMapping("/{transportationWorkerId}")
    public ResponseEntity<?> getTransportationWorkerOrder(@PathVariable("transportationWorkerId") UUID transportationWorkerId) {
        return ResponseEntity.ok(orderService.getOrdersByWorker(transportationWorkerId));
    }

    @GetMapping("/order")
    public ResponseEntity<?> getNotUncheckOrder() {
        return ResponseEntity.ok(orderService.getNotUncheckOrder());
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

    @PostMapping("/{orderId}")
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
