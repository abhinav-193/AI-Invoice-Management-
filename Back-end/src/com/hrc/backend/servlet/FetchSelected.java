package com.hrc.backend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hrc.backend.dao.RecordDao;
import com.hrc.backend.pojo.Record;


@WebServlet("/FetchSelected")
public class FetchSelected extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FetchSelected() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		ArrayList<Record> Records = new ArrayList<Record>();
		try {
			RecordDao record = new RecordDao();
			int cust_number = Integer.parseInt(request.getParameter("cust_number"));
			int invoice_id = Integer.parseInt(request.getParameter("invoice_id"));
			String doc_id = request.getParameter("doc_id");
			int business_year = Integer.parseInt(request.getParameter("business_year"));
			
			//System.out.print(cust_number+"\n"+invoice_id+"\n"+doc_id+"\n"+business_year);
			//Response = record.fetchSelectedCust(cust_number);
			
			Records = record.fetchSelectedCust(cust_number, invoice_id, doc_id, business_year);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(Records);
		//response.getWriter().append(jsonResponse);
		
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        out.print(jsonResponse);
        out.flush();   
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
