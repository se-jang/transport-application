package ku.cs.transport_application.repository;

import ku.cs.transport_application.common.TransportationWorkerStatus;
import ku.cs.transport_application.entity.TransportationWorker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransportationWorkerRepository extends JpaRepository<TransportationWorker, UUID> {
    TransportationWorker findByUsername(String username);
    TransportationWorker findByStatus(TransportationWorkerStatus status);
}
