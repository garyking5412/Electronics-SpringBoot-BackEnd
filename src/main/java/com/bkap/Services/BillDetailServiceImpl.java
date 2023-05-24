package com.bkap.Services;

import com.bkap.DTOs.BillDetailDTO;
import com.bkap.Model.BillDetail;
import com.bkap.Repositories.BillDetailRepository;
import com.bkap.Utils.BillDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BillDetailServiceImpl implements BillDetailService {

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private BillDetailMapper billDetailMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BillDetailDTO> getAllBillDetails() {
        List<BillDetail> billDetails = billDetailRepository.findAll();
        logger.info(">>>>>>>>Getting all bills details>>>>>>>>>");
        return billDetails.stream().map(billDetail -> billDetailMapper.convertBillDetailToBillDetailDto(billDetail)).collect(Collectors.toList());
    }

    @Override
    public BillDetailDTO getBillDetailByID(Integer billDetailId) {
        Optional<BillDetail> billDetail = billDetailRepository.findById(billDetailId);
        logger.info(">>>>>>>getting bill detail info>>>>>>>>>");
        return billDetailMapper.convertBillDetailToBillDetailDto(billDetail);
    }

    @Override
    @Transactional
    public BillDetailDTO insertBillDetail(BillDetailDTO dto) {
        BillDetail billDetail = billDetailMapper.convertBillDetailDtoToBillDetail(dto);
        BillDetail savedBillDetail = billDetailRepository.save(billDetail);
        logger.info(">>>>>>>>inserting bill detail info>>>>>>>>>>>>>");
        return billDetailMapper.convertBillDetailToBillDetailDto(savedBillDetail);
    }

    @Override
    @Transactional
    public BillDetailDTO updateBillDetail(BillDetailDTO dto) {
        BillDetail billDetail = billDetailMapper.convertBillDetailDtoToBillDetail(dto);
        BillDetail savedBillDetail = billDetailRepository.save(billDetail);
        logger.info(">>>>>>>>updating bill detail info>>>>>>>>>>>>>");
        return billDetailMapper.convertBillDetailToBillDetailDto(savedBillDetail);
    }

    @Override
    @Transactional
    public void deleteBillDetail(Integer billDetailId) {
        logger.info(">>>>>>>>>>>deleting bill detail info>>>>>>>>>>>>");
        billDetailRepository.deleteById(billDetailId);
    }

    @Override
    public List<BillDetailDTO> getAllBillDetailsByBillId(Integer billId) {
        return billDetailRepository.getBillDetailsByBill_InvoiceId(billId).stream().map(billDetail -> billDetailMapper.convertBillDetailToBillDetailDto(billDetail)).collect(Collectors.toList());
    }
}
