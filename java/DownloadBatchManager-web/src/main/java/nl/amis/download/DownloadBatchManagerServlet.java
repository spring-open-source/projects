/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amis.download;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oracle
 */
@WebServlet(name = "DownloadBatchManagerServlet", urlPatterns = {"/DownloadBatchManager"})
public class DownloadBatchManagerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Object fileURLListParam = request.getParameter("fileURLList");
            String[] fileURLs =  ((String)fileURLListParam).split("\\r?\\n");
           
            long jobId = submitJobFromXML(fileURLs);
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DownloadBatchManagerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DownloadBatchManagerServlet at " + request.getContextPath() + "</h1>");
            out.println("<p>Started processing the download job with id = "+jobId+"</p>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger(DownloadBatchManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }
    private long submitJobFromXML(String[] fileUrls)
            throws Exception {
        System.out.println("Start Download Job  ");
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Properties props = new Properties();
        String files ="";
        for (int i=0;i<fileUrls.length;i++) {
            files = files +fileUrls[i]+";";
        }
        System.out.println("Concatenated file list "+files);
        props.setProperty("fileList", files);
        return jobOperator.start("multiFileBatchProcessing-job", props);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
