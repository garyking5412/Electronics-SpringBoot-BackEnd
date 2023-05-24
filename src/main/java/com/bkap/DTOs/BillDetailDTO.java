package com.bkap.DTOs;

import com.bkap.Model.Bill;
import lombok.Data;

import javax.persistence.*;

@Data
public class BillDetailDTO {
    private int invoiceDetailId;
    private int productId;
    private int productQuantity;
    private int billId;
}
