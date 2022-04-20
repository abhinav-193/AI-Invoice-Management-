package com.hrc.backend.dao;
//db related operations
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.hrc.backend.pojo.Record;

public class RecordDao {
	private static final String insert_record="INSERT INTO winter_internship"+
			"(area_business,business_code,cust_payment_terms,doc_id,\r\n" + 
			"document_type,invoice_currency,baseline_create_date,clear_date,document_create_date,\r\n" + 
			"document_create_date1,due_in_date,posting_date,business_year,cust_number,invoice_id,\r\n" + 
			"isOpen,posting_id,total_open_amount) VALUES"+
			"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String select_all="SELECT * from winter_internship";
	private static final String select_BySlno="select * from winter_internship where sl_no=?";
	private static final String delete_BySlno="delete from winter_internship where sl_no=?";
	private static final String select_ByCno="SELECT * FROM winter_internship where cast(cust_number as CHAR(9)) LIKE ?"+
			"and doc_id LIKE ? and business_year LIKE ? and cast(invoice_id as CHAR(10)) LIKE ?";
	private static final String edit="UPDATE winter_internship SET invoice_currency =?,cust_payment_terms=? where sl_no = ?";
	public Connection getConnection() { //Driver manager class to connect app to data source
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/grey_goose?useSSL=false&allowPublicKeyRetrieval=true&zeroDateTimeBehavior=convertToNull"; // clear date has null values 
		String user = "root";
		String pass = "Abhinav";

		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) { //java related error

			e.printStackTrace();
		} catch (SQLException e) { //if password error is wrong
			e.printStackTrace();
		}
		return conn;
	}
	
	public HashMap<Object,Object> fetchAllRecords() throws Exception { 
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		ArrayList<Record> Records = new ArrayList<Record>();
		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(select_all);
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				int sl_no=r.getInt("sl_no");
				String area_business,business_code,cust_payment_terms,doc_id,document_type,invoice_currency,aging_bucket;
				Date baseline_create_date,clear_date,document_create_date,document_create_date1,due_in_date,posting_date;
				int business_year,cust_number,invoice_id,isOpen,posting_id,is_deleted;
				double total_open_amount;
				area_business=r.getString("area_business");
				baseline_create_date=r.getDate("baseline_create_date");
				business_year=r.getInt("business_year");
				business_code=r.getString("business_code");
				clear_date=r.getDate("clear_date");
				cust_number=r.getInt("cust_number");
				cust_payment_terms=r.getString("cust_payment_terms");
				doc_id=r.getString("doc_id");
				document_create_date=r.getDate("document_create_date");
				document_create_date1=r.getDate("document_create_date1");
				document_type=r.getString("document_type");
				due_in_date=r.getDate("due_in_date");
				invoice_currency=r.getString("invoice_currency");
				invoice_id=r.getInt("invoice_id");
				isOpen=r.getInt("isOpen");
				posting_date=r.getDate("posting_date");
				posting_id=r.getInt("posting_id");
				total_open_amount=r.getDouble("total_open_amount");
				is_deleted=r.getInt("is_deleted");
				aging_bucket=r.getString("aging_bucket");
				
				Record record = new Record(sl_no, business_year, cust_number, invoice_id, isOpen, posting_id,
						is_deleted, area_business, business_code, cust_payment_terms, doc_id,
						document_type, invoice_currency, aging_bucket, baseline_create_date,
						clear_date, document_create_date, document_create_date1, due_in_date,
						posting_date, total_open_amount);
				Records.add(record);
			}
			Response.put("records", Records);
		}catch(Exception E) {
			E.printStackTrace();
		}
		return Response;
	}
	
	public HashMap<Object,Object> fetchSelectedRecords(int sl_no) throws Exception {
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		ArrayList<Record> Records = new ArrayList<Record>();
		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(select_BySlno);
			ps.setInt(1, sl_no);
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				String area_business,business_code,cust_payment_terms,doc_id,document_type,invoice_currency,aging_bucket;
				Date baseline_create_date,clear_date,document_create_date,document_create_date1,due_in_date,posting_date;
				int business_year,cust_number,invoice_id,isOpen,posting_id,is_deleted;
				double total_open_amount;
				area_business=r.getString("area_business");
				baseline_create_date=r.getDate("baseline_create_date");
				business_year=r.getInt("business_year");
				business_code=r.getString("business_code");
				clear_date=r.getDate("clear_date");
				cust_number=r.getInt("cust_number");
				cust_payment_terms=r.getString("cust_payment_terms");
				doc_id=r.getString("doc_id");
				document_create_date=r.getDate("document_create_date");
				document_create_date1=r.getDate("document_create_date1");
				document_type=r.getString("document_type");
				due_in_date=r.getDate("due_in_date");
				invoice_currency=r.getString("invoice_currency");
				invoice_id=r.getInt("invoice_id");
				isOpen=r.getInt("isOpen");
				posting_date=r.getDate("posting_date");
				posting_id=r.getInt("posting_id");
				total_open_amount=r.getDouble("total_open_amount");
				is_deleted=r.getInt("is_deleted");
				aging_bucket=r.getString("aging_bucket");
				
				Record record = new Record(sl_no, business_year, cust_number, invoice_id, isOpen, posting_id,
						is_deleted, area_business, business_code, cust_payment_terms, doc_id,
						document_type, invoice_currency, aging_bucket, baseline_create_date,
						clear_date, document_create_date, document_create_date1, due_in_date,
						posting_date, total_open_amount);
				Records.add(record);
			}
			Response.put("records", Records);
		}catch(Exception E) {
			E.printStackTrace();
		}
		return Response;
	}
	
	public HashMap<Object,Object> addRecords(Record record) throws Exception {
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(insert_record);
			
			ps.setString(1, record.getArea_business());
			ps.setString(2, record.getBusiness_code());
			ps.setString(3, record.getCust_payment_terms());
			ps.setString(4, record.getDoc_id());
			ps.setString(5, record.getDocument_type());
			ps.setString(6, record.getInvoice_currency());
			ps.setDate(7, (java.sql.Date) record.getBaseline_create_date());
			ps.setDate(8, (java.sql.Date) record.getClear_date());
			ps.setDate(9, (java.sql.Date) record.getDocument_create_date());
			ps.setDate(10, (java.sql.Date) record.getDocument_create_date1());
			ps.setDate(11, (java.sql.Date) record.getDue_in_date());
			ps.setDate(12, (java.sql.Date) record.getPosting_date());
			ps.setInt(13, record.getBusiness_year());
			ps.setInt(14, record.getCust_number());
			ps.setInt(15, record.getInvoice_id());
			ps.setInt(16, record.getIsOpen());
			ps.setInt(17, record.getPosting_id());
			ps.setDouble(18, record.getTotal_open_amount());
			
			//ResultSet r = ps.executeQuery();
			if(ps.executeUpdate()>0) {
				Response.put("inserted", true);
			}else {
				Response.put("not inserted", false);
			}
			
		}catch(Exception E) {
			Response.put("not inserted", false);
			E.printStackTrace();
		}
		return Response;
	}
	
	public HashMap<Object,Object> editRecord(int sl_no,String invoice_currency,String cust_payment_terms) throws Exception {
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		ArrayList<Record> Records = new ArrayList<Record>();
		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(edit);
			ps.setString(1,invoice_currency);
			ps.setString(2,cust_payment_terms);
			ps.setInt(3,sl_no);
			
			if(ps.executeUpdate()>0) {
				Response.put("Edited", true);
			}else {
				Response.put("Edited", false);
			}
		}catch(Exception E) {
			E.printStackTrace();
		}
		return Response;
	}
	
	public ArrayList<Record> fetchSelectedCust(int cust_number, int invoice_id, String doc_id, int business_year) throws Exception {
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		ArrayList<Record> Records = new ArrayList<Record>();
		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(select_ByCno);
			//System.out.print(ps);
			
			if (cust_number==-1) ps.setString(1, "%%");
			else ps.setString(1, "%" + String.valueOf(cust_number) + "%");
			
			if (doc_id.equals("-1")) ps.setString(2, "%%");
			else ps.setString(2, "%" + doc_id + "%");
			
			if (business_year==-1) ps.setString(3, "%%");
			else ps.setString(3, "%" + String.valueOf(business_year) + "%");
			
			if (invoice_id==-1) ps.setString(4, "%%");
			else ps.setString(4, "%" + String.valueOf(invoice_id) + "%");
			
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				String area_business,business_code,cust_payment_terms,document_type,invoice_currency,aging_bucket;
				Date baseline_create_date,clear_date,document_create_date,document_create_date1,due_in_date,posting_date;
				int isOpen,posting_id,is_deleted, sl_no;
				double total_open_amount;
				sl_no=r.getInt("sl_no");
				area_business=r.getString("area_business");
				baseline_create_date=r.getDate("baseline_create_date");
				business_year=r.getInt("business_year");
				business_code=r.getString("business_code");
				clear_date=r.getDate("clear_date");
				cust_number=r.getInt("cust_number");
				cust_payment_terms=r.getString("cust_payment_terms");
				doc_id=r.getString("doc_id");
				document_create_date=r.getDate("document_create_date");
				document_create_date1=r.getDate("document_create_date1");
				document_type=r.getString("document_type");
				due_in_date=r.getDate("due_in_date");
				invoice_currency=r.getString("invoice_currency");
				invoice_id=r.getInt("invoice_id");
				isOpen=r.getInt("isOpen");
				posting_date=r.getDate("posting_date");
				posting_id=r.getInt("posting_id");
				total_open_amount=r.getDouble("total_open_amount");
				is_deleted=r.getInt("is_deleted");
				aging_bucket=r.getString("aging_bucket");
				
				Record record = new Record(sl_no, business_year, cust_number, invoice_id, isOpen, posting_id,
						is_deleted, area_business, business_code, cust_payment_terms, doc_id,
						document_type, invoice_currency, aging_bucket, baseline_create_date,
						clear_date, document_create_date, document_create_date1, due_in_date,
						posting_date, total_open_amount);
				Records.add(record);
			}
			Response.put("records", Records);
		}catch(Exception E) {
			E.printStackTrace();
		}
		return Records;
	}
	
	public HashMap<Object,Object> deleteRecord(int[] arr) throws Exception {
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(delete_BySlno);
			for(int sl_no=0;sl_no<arr.length;sl_no++) {
				ps.setInt(1, arr[ sl_no]);
				
				if(ps.executeUpdate()>0) {
					Response.put("deleted", true);
				}else {
					Response.put("deleted", false);
				}
			}
			//ResultSet r = ps.executeQuery();
		
		}catch(Exception E) {
			Response.put("deleted", false);
			E.printStackTrace();
		}
		return Response;
	}
}