package com.bkap.Services.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "invoicedetail")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private int invoiceDetailId;
    @Column(name = "ProductId")
    private int productId;
    @Column(name = "ProductQuantity")
    private int productQuantity;
    @ManyToOne
    @JoinColumn(name = "InvoiceId")
    @JsonIgnore
    private Bill bill;
}
