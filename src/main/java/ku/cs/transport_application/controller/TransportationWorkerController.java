package ku.cs.transport_application.controller;


import ku.cs.transport_application.service.TransportationWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transportation-workers")
public class TransportationWorkerController {

    @Autowired
    private TransportationWorkerService transportationWorkerService;

    @GetMapping
    public String transportationWorkers(Model model) {
        model.addAttribute("transportationWorkers", transportationWorkerService.getAvailableTransportationWorker());
        return "transportation-workers";
    }


}
