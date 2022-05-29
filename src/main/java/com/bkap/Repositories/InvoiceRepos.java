package com.bkap.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bkap.Entities.Invoice;

@Repository
public interface InvoiceRepos extends JpaRepository<Invoice, Integer>, JpaSpecificationExecutor<Invoice> {

	@Query(value="Select * from Invoice where date between ?1 and ?2 ",nativeQuery=true)
	public List<Invoice> getByDate(Date startDate,Date endDate);
	@Query(value="select * from Invoice where status = ?1 ",nativeQuery=true)
	public List<Invoice> getByStatus(String status);
	@Query(value="select * from Invoice where customer = ?1 and customerContact = ?2 ",nativeQuery=true)
	public List<Invoice> getByCustomer(String customer,int contact);
}
