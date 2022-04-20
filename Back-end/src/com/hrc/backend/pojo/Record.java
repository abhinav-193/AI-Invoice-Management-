package com.hrc.backend.pojo;
//variables and data types in dump file defined 
import java.util.*;

public class Record {
	private int sl_no;
	private int business_year;
	private int cust_number;
	private int invoice_id;
	private int isOpen;
	private int posting_id;
	private int is_deleted;
	
	private String area_business;
	private String business_code;
	private String cust_payment_terms;
	private String doc_id;
	private String document_type;
	private String invoice_currency;
	private String aging_bucket;
	
	private Date baseline_create_date;
	private Date clear_date;
	private Date document_create_date;
	private Date document_create_date1;
	private Date due_in_date;
	private Date posting_date;
	
	private double total_open_amount;
	
// getters and setters
	public int getSl_no() { //to get the value
		return sl_no;
	}

	public void setSl_no(int sl_no) { // to change the value
		this.sl_no = sl_no;
	}

	public int getBusiness_year() {
		return business_year;
	}

	public void setBusiness_year(int business_year) {
		this.business_year = business_year;
	}

	public int getCust_number() {
		return cust_number;
	}

	public void setCust_number(int cust_number) {
		this.cust_number = cust_number;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public int getPosting_id() {
		return posting_id;
	}

	public void setPosting_id(int posting_id) {
		this.posting_id = posting_id;
	}

	public int getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getArea_business() {
		return area_business;
	}

	public void setArea_business(String area_business) {
		this.area_business = area_business;
	}

	public String getBusiness_code() {
		return business_code;
	}

	public void setBusiness_code(String business_code) {
		this.business_code = business_code;
	}

	public String getCust_payment_terms() {
		return cust_payment_terms;
	}

	public void setCust_payment_terms(String cust_payment_terms) {
		this.cust_payment_terms = cust_payment_terms;
	}

	public String getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getInvoice_currency() {
		return invoice_currency;
	}

	public void setInvoice_currency(String invoice_currency) {
		this.invoice_currency = invoice_currency;
	}

	public String getAging_bucket() {
		return aging_bucket;
	}

	public void setAging_bucket(String aging_bucket) {
		this.aging_bucket = aging_bucket;
	}

	public Date getBaseline_create_date() {
		return baseline_create_date;
	}

	public void setBaseline_create_date(Date baseline_create_date) {
		this.baseline_create_date = baseline_create_date;
	}

	public Date getClear_date() {
		return clear_date;
	}

	public void setClear_date(Date clear_date) {
		this.clear_date = clear_date;
	}

	public Date getDocument_create_date() {
		return document_create_date;
	}

	public void setDocument_create_date(Date document_create_date) {
		this.document_create_date = document_create_date;
	}

	public Date getDocument_create_date1() {
		return document_create_date1;
	}

	public void setDocument_create_date1(Date document_create_date1) {
		this.document_create_date1 = document_create_date1;
	}

	public Date getDue_in_date() {
		return due_in_date;
	}

	public void setDue_in_date(Date due_in_date) {
		this.due_in_date = due_in_date;
	}

	public Date getPosting_date() {
		return posting_date;
	}

	public void setPosting_date(Date posting_date) {
		this.posting_date = posting_date;
	}

	public double getTotal_open_amount() {
		return total_open_amount;
	}

	public void setTotal_open_amount(double total_open_amount) {
		this.total_open_amount = total_open_amount;
	}

	
	//constructor without sl_no add
	public Record(int business_year, int cust_number, int invoice_id, int isOpen, int posting_id, int is_deleted,
			String area_business, String business_code, String cust_payment_terms, String doc_id, String document_type,
			String invoice_currency, String aging_bucket, Date baseline_create_date, Date clear_date,
			Date document_create_date, Date document_create_date1, Date due_in_date, Date posting_date,
			double total_open_amount) { 
		super();
		this.business_year = business_year;
		this.cust_number = cust_number;
		this.invoice_id = invoice_id;
		this.isOpen = isOpen;
		this.posting_id = posting_id;
		this.is_deleted = is_deleted;
		this.area_business = area_business;
		this.business_code = business_code;
		this.cust_payment_terms = cust_payment_terms;
		this.doc_id = doc_id;
		this.document_type = document_type;
		this.invoice_currency = invoice_currency;
		this.aging_bucket = aging_bucket;
		this.baseline_create_date = baseline_create_date;
		this.clear_date = clear_date;
		this.document_create_date = document_create_date;
		this.document_create_date1 = document_create_date1;
		this.due_in_date = due_in_date;
		this.posting_date = posting_date;
		this.total_open_amount = total_open_amount;
	}
	
	//constructor with sl_no fetch, adv search and all
	public Record(int sl_no, int business_year, int cust_number, int invoice_id, int isOpen, int posting_id,
			int is_deleted, String area_business, String business_code, String cust_payment_terms, String doc_id,
			String document_type, String invoice_currency, String aging_bucket, Date baseline_create_date,
			Date clear_date, Date document_create_date, Date document_create_date1, Date due_in_date,
			Date posting_date, double total_open_amount) {
		super();
		this.sl_no = sl_no;
		this.business_year = business_year;
		this.cust_number = cust_number;
		this.invoice_id = invoice_id;
		this.isOpen = isOpen;
		this.posting_id = posting_id;
		this.is_deleted = is_deleted;
		this.area_business = area_business;
		this.business_code = business_code;
		this.cust_payment_terms = cust_payment_terms;
		this.doc_id = doc_id;
		this.document_type = document_type;
		this.invoice_currency = invoice_currency;
		this.aging_bucket = aging_bucket;
		this.baseline_create_date = baseline_create_date;
		this.clear_date = clear_date;
		this.document_create_date = document_create_date;
		this.document_create_date1 = document_create_date1;
		this.due_in_date = due_in_date;
		this.posting_date = posting_date;
		this.total_open_amount = total_open_amount;
	}

}
