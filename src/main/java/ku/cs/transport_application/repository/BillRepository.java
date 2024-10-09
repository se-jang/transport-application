package ku.cs.transport_application.repository;

import ku.cs.transport_application.common.BillStatus;
import ku.cs.transport_application.entity.Bill;
import ku.cs.transport_application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {
    Bill findByStatus(BillStatus status);
    Bill findByCompany(User company);
}