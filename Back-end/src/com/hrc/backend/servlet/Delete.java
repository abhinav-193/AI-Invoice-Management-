package com.hrc.backend.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hrc.backend.dao.RecordDao;


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Delete() {
        super();
        
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		try {
			RecordDao record = new RecordDao();
			StringBuilder sb = new StringBuilder();
		    BufferedReader reader = request.getReader();
		    try {
		        String line;
		        while ((line = reader.readLine()) != null) {
		            sb.append(line).append('\n');
		        }
		    } finally {
		        reader.close();
		    }
		    //System.out.println(sb.toString());
		    int l = sb.toString().length();
		    String sub = sb.toString().substring(9, l-2);
		    //System.out.println(sub+l);
		    String[] string = sub.replaceAll("\\[", "").replaceAll("]", "").split(",");
		    int[] arr = new int[string.length];
		    for (int i = 0; i < string.length; i++) {
	            arr[i] = Integer.valueOf(string[i]);
	        }
		    //System.out.print("\nInteger array : "+ Arrays.toString(arr));
		    
			Response = record.deleteRecord(arr);
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}