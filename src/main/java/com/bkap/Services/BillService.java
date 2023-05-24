package com.bkap.Services;

import com.bkap.DTOs.BillDTO;
import com.bkap.Model.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {

    List<BillDTO> getAllBills();

    BillDTO getBillByID(Integer billId);

    BillDTO insertBill(BillDTO dto);

    BillDTO updateBill(BillDTO dto);

    void deleteBill(Integer billId);
}
