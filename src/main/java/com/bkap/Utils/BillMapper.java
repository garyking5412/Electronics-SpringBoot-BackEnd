package com.bkap.Utils;

import com.bkap.DTOs.BillDTO;
import com.bkap.Model.Bill;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class BillMapper {

    public BillDTO convertBillToBillDto(Optional<Bill> bill) {
        BillDTO dto = new BillDTO();
        bill.ifPresent(b -> {
            dto.setInvoiceId(b.getInvoiceId());
            dto.setInvoiceDate(b.getInvoiceDate());
            dto.setInvoiceTotal(b.getInvoiceTotal());
            dto.setBillDetailList(b.getBillDetailList());
        });
        return dto;
    }

    public BillDTO convertBillToBillDto(Bill bill) {
        BillDTO dto = new BillDTO();
        dto.setInvoiceId(bill.getInvoiceId());
        dto.setInvoiceDate(bill.getInvoiceDate());
        dto.setInvoiceTotal(bill.getInvoiceTotal());
        dto.setBillDetailList(bill.getBillDetailList());
        return dto;
    }

    public Bill convertBillDtoToBill(BillDTO dto) {
        Bill bill = new Bill();
        bill.setInvoiceId(dto.getInvoiceId());
        bill.setInvoiceDate(dto.getInvoiceDate());
        bill.setInvoiceTotal(dto.getInvoiceTotal());
        bill.setBillDetailList(dto.getBillDetailList());
        return bill;
    }

}
