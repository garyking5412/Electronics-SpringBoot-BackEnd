package com.bkap.DTOs;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BillDTO {
    private int invoiceId;
    private Date invoiceDate;
    private Long invoiceTotal;
    private List<BillDetailDTO> billDetailList;
}
