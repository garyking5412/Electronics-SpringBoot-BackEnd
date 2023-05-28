package com.bkap.DTOs;

import com.bkap.Model.BillDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class BillDTO {
    private int invoiceId;
    private Date invoiceDate;
    private Long invoiceTotal;
    private List<BillDetailDTO> billDetailList;
}
