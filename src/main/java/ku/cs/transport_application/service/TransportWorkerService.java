package ku.cs.transport_application.service;

import ku.cs.transport_application.common.TransportationWorkerStatus;
import ku.cs.transport_application.entity.TransportationWorker;
import ku.cs.transport_application.repository.TransportationWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransportWorkerService {

    @Autowired
    TransportationWorkerRepository transportationWorkerRepository;

    public List<TransportationWorker> getAllTransportationWorker() {
        return transportationWorkerRepository.findAll();
    }

    public void updateTransportationWorker(UUID workerId, TransportationWorkerStatus status) {
        TransportationWorker record = transportationWorkerRepository.findById(workerId)
                .orElseThrow(() -> new IllegalArgumentException("Worker not found"));
        record.setStatus(status);
        transportationWorkerRepository.save(record);
    }
}
