package com.bkap.Services;

import com.bkap.DTOs.BillDetailDTO;

import java.util.List;

public interface BillDetailService {

    List<BillDetailDTO> getAllBillDetails();

    BillDetailDTO getBillDetailByID(Integer billDetailId);

    BillDetailDTO insertBillDetail(BillDetailDTO dto);

    BillDetailDTO updateBillDetail(BillDetailDTO dto);

    void deleteBillDetail(Integer billDetailId);

    List<BillDetailDTO> getAllBillDetailsByBillId(Integer billId);
}
