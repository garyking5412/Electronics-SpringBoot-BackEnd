package com.bkap.Services.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "invoice")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "InvoiceId")
    private int invoiceId;
    @Column(name = "InvoiceDate")
    private Date invoiceDate;
    @Column(name = "InvoiceTotal")
    private Long invoiceTotal;
    @JsonIgnore
    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY)
    private List<BillDetail> billDetailList;
}
