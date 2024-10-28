package ku.cs.transport_application.service;

import ku.cs.transport_application.DTO.TransportationWorkerDTO;
import ku.cs.transport_application.common.TransportationWorkerStatus;
import ku.cs.transport_application.entity.TransportationWorker;
import ku.cs.transport_application.repository.TransportationWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static ku.cs.transport_application.common.TransportationWorkerStatus.AVAILABLE;

@Service
public class TransportationWorkerService {

    @Autowired
    TransportationWorkerRepository transportationWorkerRepository;

    public List<TransportationWorkerDTO> getAllTransportationWorker() {
        List<TransportationWorker> workers = transportationWorkerRepository.findAll();
        return workers.stream().map(worker -> {
            TransportationWorkerDTO dto = new TransportationWorkerDTO();
            dto.setId(worker.getId());
            dto.setUsername(worker.getUsername());
            dto.setName(worker.getName());
            dto.setPhoneNumber(worker.getPhoneNumber());
            dto.setEmail(worker.getEmail());
            dto.setStatus(worker.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    public void updateTransportationWorker(UUID workerId, TransportationWorkerStatus status) {
        TransportationWorker record = transportationWorkerRepository.findById(workerId)
                .orElseThrow(() -> new IllegalArgumentException("Worker not found"));
        record.setStatus(status);
        transportationWorkerRepository.save(record);
    }

    public TransportationWorker findWorkerByUsername(String username) {
        return transportationWorkerRepository.findByUsername(username);
    }

    public List<TransportationWorkerDTO> getAvailableWorker() {
        List<TransportationWorker> workers = transportationWorkerRepository.findByStatus(AVAILABLE);
        return workers.stream().map(worker -> {
            TransportationWorkerDTO dto = new TransportationWorkerDTO();
            dto.setId(worker.getId());
            dto.setUsername(worker.getUsername());
            dto.setName(worker.getName());
            dto.setPhoneNumber(worker.getPhoneNumber());
            dto.setEmail(worker.getEmail());
            dto.setStatus(worker.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }
}
