package com.bkap.Utils;

import com.bkap.DTOs.BillDTO;
import com.bkap.DTOs.BillDetailDTO;
import com.bkap.Model.Bill;
import com.bkap.Model.BillDetail;
import com.bkap.Repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class BillDetailMapper {

    @Autowired
    private BillRepository billRepository;

    public BillDetailDTO convertBillDetailToBillDetailDto(Optional<BillDetail> billDetail) {
        BillDetailDTO dto = new BillDetailDTO();
        billDetail.ifPresent(b -> {
            dto.setInvoiceDetailId(b.getInvoiceDetailId());
            dto.setBillId(b.getBill().getInvoiceId());
            dto.setProductQuantity(b.getProductQuantity());
            dto.setProductId(b.getProductId());
        });
        return dto;
    }

    public BillDetailDTO convertBillDetailToBillDetailDto(BillDetail bill) {
        BillDetailDTO dto = new BillDetailDTO();
        dto.setProductId(bill.getProductId());
        dto.setInvoiceDetailId(bill.getInvoiceDetailId());
        dto.setProductQuantity(bill.getProductQuantity());
        dto.setBillId(bill.getBill().getInvoiceId());
        return dto;
    }

    public BillDetail convertBillDetailDtoToBillDetail(BillDetailDTO dto) {
        BillDetail billDetail = new BillDetail();
        billDetail.setInvoiceDetailId(dto.getInvoiceDetailId());
        billDetail.setProductId(dto.getProductId());
        billDetail.setProductQuantity(dto.getProductQuantity());
        if (Objects.nonNull(dto.getBillId())) {
            Optional<Bill> bill = billRepository.findById(dto.getBillId());
            bill.ifPresentOrElse(billDetail::setBill, () -> {
                billDetail.setBill(null);
            });
        }
        return billDetail;
    }

}
