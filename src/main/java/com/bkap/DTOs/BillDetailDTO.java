package com.bkap.DTOs;

import lombok.Data;

@Data
public class BillDetailDTO {
    private int invoiceDetailId;
    private int productId;
    private String productName;
    private String productCategory;
    private long productPrice;
    private int productQuantity;
    private int billId;
}
