package com.bkap.Controllers;

import com.bkap.DTOs.BillDTO;
import com.bkap.DTOs.BillDetailDTO;
import com.bkap.Services.BillDetailService;
import com.bkap.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillDetailService billDetailService;

    //    BILL CRUD ENDPOINTS
    @GetMapping(value = "/getAllBills")
    public ResponseEntity<List<BillDTO>> getAllBills() {
        return ResponseEntity.status(HttpStatus.OK).body(billService.getAllBills());
    }

    @GetMapping(value = "/getBillByID")
    public ResponseEntity<BillDTO> getBillByID(@RequestParam("billId") Integer billId) {
        return ResponseEntity.status(HttpStatus.OK).body(billService.getBillByID(billId));
    }

    @PostMapping(value = "/insertBill")
    public ResponseEntity<BillDTO> insertBill(@RequestBody BillDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(billService.insertBill(dto));
    }

    @PutMapping(value = "/updateBill")
    public ResponseEntity<BillDTO> updateBill(@RequestBody BillDTO dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(billService.updateBill(dto));
    }

    @DeleteMapping(value = "/deleteBill")
    public ResponseEntity deleteBill(@RequestParam("billId") Integer billId) {
        billService.deleteBill(billId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //    BILL DETAIL CRUD ENDPOINTS
    @GetMapping(value = "/getAllBillDetails")
    public ResponseEntity<List<BillDetailDTO>> getAllBillDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(billDetailService.getAllBillDetails());
    }

    @GetMapping(value = "/getBillDetailByID")
    public ResponseEntity<BillDetailDTO> getBillDetailByID(@RequestParam("billDetailId") Integer billDetailId) {
        return ResponseEntity.status(HttpStatus.OK).body(billDetailService.getBillDetailByID(billDetailId));
    }

    @PostMapping(value = "/insertBillDetail")
    public ResponseEntity<BillDetailDTO> insertBillDetail(@RequestBody BillDetailDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(billDetailService.insertBillDetail(dto));
    }

    @PutMapping(value = "/updateBillDetail")
    public ResponseEntity<BillDetailDTO> updateBillDetail(@RequestBody BillDetailDTO dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(billDetailService.updateBillDetail(dto));
    }

    @DeleteMapping(value = "/deleteBillDetail")
    public ResponseEntity deleteBillDetail(@RequestParam("billDetailId") Integer billDetailId) {
        billDetailService.deleteBillDetail(billDetailId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/getBillDetailByBillID")
    public ResponseEntity<List<BillDetailDTO>> getBillDetailByBillID(@RequestParam("billId") Integer billId) {
        return ResponseEntity.status(HttpStatus.OK).body(billDetailService.getAllBillDetailsByBillId(billId));
    }
}
