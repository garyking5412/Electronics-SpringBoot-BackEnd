package com.bkap.Repositories;

import com.bkap.Model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail,Integer> {
    List<BillDetail> getBillDetailsByBill_InvoiceId(Integer billId);
}
