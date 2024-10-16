package ku.cs.transport_application.controller;


import ku.cs.transport_application.service.TransportationWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransportationWorkerController {

    @Autowired
    private TransportationWorkerService transportationWorkerService;

    @GetMapping("/transportation-workers")
    public ResponseEntity<?> transportationWorkers(Model model) {
        model.addAttribute("transportationWorkers", transportationWorkerService.getAvailableTransportationWorker());
        return ResponseEntity.ok(transportationWorkerService.getAvailableTransportationWorker());
    }


}
