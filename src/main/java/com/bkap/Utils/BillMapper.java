package com.bkap.Utils;

import com.bkap.DTOs.BillDTO;
import com.bkap.DTOs.BillDetailDTO;
import com.bkap.Model.Bill;
import com.bkap.Model.BillDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BillMapper {

    @Autowired
    private BillDetailMapper billDetailMapper;

    public BillDTO convertBillToBillDto(Optional<Bill> bill) {
        BillDTO dto = new BillDTO();
        bill.ifPresent(b -> {
            dto.setInvoiceId(b.getInvoiceId());
            dto.setInvoiceDate(b.getInvoiceDate());
            dto.setInvoiceTotal(b.getInvoiceTotal());
            List<BillDetailDTO> billDetailDTOList = b.getBillDetailList().stream().map(billDetail -> {
                BillDetailDTO billDetailDTO = billDetailMapper.convertBillDetailToBillDetailDto(billDetail);
                return billDetailDTO;
            }).collect(Collectors.toList());
            dto.setBillDetailList(billDetailDTOList);
        });
        return dto;
    }

    public BillDTO convertBillToBillDto(Bill bill) {
        BillDTO dto = new BillDTO();
        dto.setInvoiceId(bill.getInvoiceId());
        dto.setInvoiceDate(bill.getInvoiceDate());
        dto.setInvoiceTotal(bill.getInvoiceTotal());
        List<BillDetailDTO> billDetailDTOList = bill.getBillDetailList().stream().map(billDetail -> {
            BillDetailDTO billDetailDTO = billDetailMapper.convertBillDetailToBillDetailDto(billDetail);
            return billDetailDTO;
        }).collect(Collectors.toList());
        dto.setBillDetailList(billDetailDTOList);
        return dto;
    }

    public Bill convertBillDtoToBill(BillDTO dto) {
        Bill bill = new Bill();
        bill.setInvoiceId(dto.getInvoiceId());
        bill.setInvoiceDate(dto.getInvoiceDate());
        bill.setInvoiceTotal(dto.getInvoiceTotal());
//        bill.setBillDetailList(dto.getBillDetailList());
        return bill;
    }

}
