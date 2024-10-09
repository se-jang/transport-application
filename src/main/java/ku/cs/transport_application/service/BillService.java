package ku.cs.transport_application.service;

import ku.cs.transport_application.common.BillStatus;
import ku.cs.transport_application.entity.Bill;
import ku.cs.transport_application.repository.BillRepository;
import ku.cs.transport_application.request.BillRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static ku.cs.transport_application.common.BillStatus.PENDING;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void createBill(BillRequest request) {
        Bill modelBill = modelMapper.map(request, Bill.class);
        modelBill.setStatus(PENDING);
        modelBill.setShippingCost();
        modelBill.setDate(LocalDateTime.now());
        billRepository.save(modelBill);
    }

    public void updateBill(UUID billId, BillStatus status) {
        Bill record = billRepository.findById(billId)
                .orElseThrow(() -> new IllegalArgumentException("Bill not found"));
        record.setStatus(status);
        record.setPaidDate(LocalDateTime.now());
        billRepository.save(record);
    }
}
