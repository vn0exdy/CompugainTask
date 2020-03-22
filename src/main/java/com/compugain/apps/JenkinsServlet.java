package com.compugain.apps;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class JenkinsServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter jenkinsPrintWriter = response.getWriter();
        jenkinsPrintWriter.println("Wonderful Job!!!");
        jenkinsPrintWriter.flush();
        jenkinsPrintWriter.close();
    }
 }
