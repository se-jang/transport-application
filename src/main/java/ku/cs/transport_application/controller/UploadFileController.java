package ku.cs.transport_application.controller;

import ku.cs.transport_application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadFileController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestPart("orderId") UUID orderId, @RequestPart("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String fileName = file.getOriginalFilename();
        assert fileName != null;

        if (!fileName.endsWith(".pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed");
        }

        orderService.uploadFile(orderId, file);
        return ResponseEntity.ok(Map.of("message", "File uploaded successfully", "fileName", fileName));
    }
}
