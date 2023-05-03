package com.dataentry.api.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomePageServlet extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Hit Done");
        System.out.println("Host = " + request.getServerName());
        System.out.println("Port = " + request.getServerPort());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
        System.out.println(request.getRequestURI());
        dispatcher.forward(request,response);
    }
}
