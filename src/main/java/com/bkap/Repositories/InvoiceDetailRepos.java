package com.bkap.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bkap.Entities.InvoiceDetail;

@Repository
public interface InvoiceDetailRepos extends JpaRepository<InvoiceDetail, Integer> {

	@Query(value="Select * from InvoiceDetails where invoiceId = ?1 ",nativeQuery=true)
	public List<InvoiceDetail> getByInvoiceId( int invoiceId);
	@Query(value="select d.quantity,p.name,p.color,p.spec,p.price,d.quantity*p.price as subtotal,d.id as detailId,d.invoiceId  from InvoiceDetails d inner join Product p on d.productId=p.id where d.invoiceId = ?1",nativeQuery=true)
	public List<Object> getDetailsByInvoiceId( int invoiceId);
	@Query(value="select * from InvoiceDetails where productId = ?1",nativeQuery=true)
	public List<InvoiceDetail> getDetailsByProductId( int productId);
	
}	
