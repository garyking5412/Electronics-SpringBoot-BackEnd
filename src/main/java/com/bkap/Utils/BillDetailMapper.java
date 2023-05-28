package com.bkap.Utils;

import com.bkap.DTOs.BillDTO;
import com.bkap.DTOs.BillDetailDTO;
import com.bkap.Model.Bill;
import com.bkap.Model.BillDetail;
import com.bkap.Repositories.BillRepository;
import com.example.electronicsspringbootclientservice.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class BillDetailMapper {

    @Autowired
    private BillRepository billRepository;

    public BillDetailDTO convertBillDetailToBillDetailDto(Optional<BillDetail> billDetail) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",3004).usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(channel);
        BillDetailDTO dto = new BillDetailDTO();
        billDetail.ifPresent(b -> {
            dto.setInvoiceDetailId(b.getInvoiceDetailId());
            dto.setBillId(b.getBill().getInvoiceId());
            dto.setProductQuantity(b.getProductQuantity());
            dto.setProductId(b.getProductId());
            try {
                com.example.electronicsspringbootclientservice.GetProductByIdRequest request = com.example.electronicsspringbootclientservice.GetProductByIdRequest.newBuilder().setProductId(b.getProductId()).build();
                com.example.electronicsspringbootclientservice.GetProductByIdResponse response = stub.getProductById(request);
                dto.setProductName(response.getProductName());
                dto.setProductCategory(response.getProductCategoryName());
                dto.setProductPrice(response.getProductPrice());
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                channel.shutdown();
            }

        });
        return dto;
    }

    public BillDetailDTO convertBillDetailToBillDetailDto(BillDetail billDetail) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 3004).usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(channel);
        BillDetailDTO dto = new BillDetailDTO();
        dto.setInvoiceDetailId(billDetail.getInvoiceDetailId());
        dto.setBillId(billDetail.getBill().getInvoiceId());
        dto.setProductQuantity(billDetail.getProductQuantity());
        dto.setProductId(billDetail.getProductId());
        try {
            com.example.electronicsspringbootclientservice.GetProductByIdRequest request = com.example.electronicsspringbootclientservice.GetProductByIdRequest.newBuilder().setProductId(billDetail.getProductId()).build();
            com.example.electronicsspringbootclientservice.GetProductByIdResponse response = stub.getProductById(request);
            dto.setProductName(response.getProductName());
            dto.setProductCategory(response.getProductCategoryName());
            dto.setProductPrice(response.getProductPrice());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.shutdown();
        }
        return dto;
    }

    public BillDetail convertBillDetailDtoToBillDetail(BillDetailDTO dto) {
        BillDetail billDetail = new BillDetail();
        billDetail.setInvoiceDetailId(dto.getInvoiceDetailId());
        billDetail.setProductId(dto.getProductId());
        billDetail.setProductQuantity(dto.getProductQuantity());
        if (Objects.nonNull(dto.getBillId())) {
            Optional<Bill> bill = billRepository.findById(dto.getBillId());
            bill.ifPresentOrElse(billDetail::setBill, () -> {
                billDetail.setBill(null);
            });
        }
        return billDetail;
    }

}
