package com.bkap.Controllers;

import com.bkap.DTOs.BillDTO;
import com.bkap.DTOs.BillDetailDTO;
import com.bkap.DTOs.MessageDTO;
import com.bkap.DTOs.invoiceDTO;
import com.bkap.Services.BillDetailService;
import com.bkap.Services.BillService;
import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.lang.Nullable;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;

import javax.activation.FileTypeMap;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;

@Controller
public class KafkaMQController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Nullable
    private javax.mail.Session session;
    @Nullable
    private String defaultEncoding;
    @Nullable
    private FileTypeMap defaultFileTypeMap;
    private Properties javaMailProperties = new Properties();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public synchronized javax.mail.Session getSession() {
        if (this.session == null) {
            this.session = javax.mail.Session.getInstance(this.javaMailProperties);
        }

        return this.session;
    }

    @Nullable
    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }

    @Nullable
    public FileTypeMap getDefaultFileTypeMap() {
        return this.defaultFileTypeMap;
    }

//    public MimeMessage createMimeMessage() throws MessagingException {
//        return new MimeMessage(this.getSession(), this.getDefaultEncoding(), this.getDefaultFileTypeMap());
//    }

    @KafkaListener(id = "invoiceService", topics = "invoice")
    public void listen(invoiceDTO dto) {
        logger.info("received new event from invoice topics: "+dto.getInvoiceDate());
        try {
            BillDTO savedBillDto = insertBillAndBillDetails(dto);
            BillDTO savedBill = billService.getBillByID(savedBillDto.getInvoiceId());
            System.out.println(savedBill);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @KafkaListener(id="dltGroup", topics = "invoice.DLT")
    public void errorHandler(invoiceDTO dto){
        logger.info("received new event from dead letter topics: "+dto.getInvoiceDate());
    }

    @Transactional
    public BillDTO insertBillAndBillDetails(invoiceDTO dto) {
        BillDTO billDTO = new BillDTO();
        billDTO.setInvoiceDate(dto.getInvoiceDate());
        billDTO.setInvoiceTotal(dto.getInvoiceTotal());
        logger.info(">>>>>>>>>>>> received invoice request >>>>>>>>>>>>>>>>>>>>>");
        BillDTO savedBillDTO = billService.insertBill(billDTO);
        if (Objects.nonNull(savedBillDTO)) {
            logger.info(">>>>>>>>>>>> saving invoice detail for invoice id of: " + savedBillDTO.getInvoiceId() + " >>>>>>>>>>>>>>>>>>>>>");
            dto.getBillDetailList().forEach(detail -> {
                BillDetailDTO detailDto = new BillDetailDTO();
                detailDto.setBillId(savedBillDTO.getInvoiceId());
                detailDto.setProductId(detail.getProductId());
                detailDto.setProductQuantity(detail.getProductQuantity());
                billDetailService.insertBillDetail(detailDto);
            });
        }
        return savedBillDTO;
    }

    @KafkaListener(id = "notiService", topics = "notification")
    public void listen(MessageDTO dto) {
        logger.info(">>>>>>>>>>>> received message inbound for: " + dto.getToName() + " >>>>>>>>>>>>>>>>>>>>>");
        MimeMessage mimeMessage = new MimeMessage(this.getSession());
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, CharEncoding.UTF_8);
            message.setTo(dto.getTo());
            message.setFrom("TXTHAI@KAFKASERVER.VN");
            message.setSubject("test kafka notification messaging service");
            message.setText(dto.getContent());
            message.setSentDate(new Date());
            javaMailSender.send(mimeMessage);
            logger.debug("Sent e-mail to User '{}'", dto.getTo());
        } catch (Exception e) {
            logger.warn("E-mail could not be sent to user '{}', exception is: {}", dto.getTo(), e.getMessage());
        }
    }
}
