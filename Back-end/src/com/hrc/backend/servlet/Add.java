package com.hrc.backend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hrc.backend.dao.RecordDao;
import com.hrc.backend.pojo.Record;


@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		try {
			RecordDao record = new RecordDao();
			String area_business,business_code,cust_payment_terms,doc_id,document_type,invoice_currency,aging_bucket;
			Date baseline_create_date,clear_date,document_create_date,document_create_date1,due_in_date,posting_date;
			int business_year,cust_number,invoice_id,isOpen,posting_id,is_deleted,sl_no;
			double total_open_amount;
			String sDate1="2019-01-1";
			Date sample=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		    java.sql.Date date1 = new java.sql.Date(sample.getTime());
			area_business="";
			sl_no=1;
				sample=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("baseline_create_date"));
				baseline_create_date= new java.sql.Date(sample.getTime());
				business_year=Integer.parseInt(request.getParameter("buisness_year"));
				business_code=request.getParameter("business_code");
				sample = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("clear_date"));
				clear_date= new java.sql.Date(sample.getTime());
				cust_number= Integer.parseInt(request.getParameter("cust_number"));
				cust_payment_terms=request.getParameter("cust_payment_terms");
				doc_id=request.getParameter("doc_id");
				sample = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("document_create_date"));
				document_create_date=new java.sql.Date(sample.getTime());
			sample = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
			document_create_date1=new java.sql.Date(sample.getTime());
				document_type=request.getParameter("document_type");
				sample = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("due_in_date"));
				due_in_date=new java.sql.Date(sample.getTime());
				invoice_currency=request.getParameter("invoice_currency");
				invoice_id = Integer.parseInt(request.getParameter("invoice_id"));
			isOpen = Integer.parseInt("1");
				sample = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("posting_date"));
				posting_date= new java.sql.Date(sample.getTime());
				posting_id = Integer.parseInt(request.getParameter("posting_id"));
				total_open_amount=Double.parseDouble(request.getParameter("total_open_amount"));
			is_deleted= Integer.parseInt("0");
			aging_bucket="";
		
			Record r1 = new Record(sl_no, business_year,cust_number ,invoice_id , isOpen, posting_id,
					is_deleted, area_business,business_code , cust_payment_terms, doc_id,
					document_type, invoice_currency,aging_bucket , baseline_create_date,
					clear_date,document_create_date, document_create_date1, due_in_date,
					posting_date, total_open_amount);
			Response = record.addRecords(r1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(Response);
		//response.getWriter().append(jsonResponse);
		
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        out.print(jsonResponse);
        out.flush();   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}