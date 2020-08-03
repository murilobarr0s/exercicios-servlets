package br.unoeste.fipp.exercicios;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "Exercicio3", urlPatterns = {"/Exercicio3.do"})
public class Exercicio3 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int totalCampos = 10;
        String[] campos = new String[totalCampos];

        String botao = request.getParameter("bOK");
        if (botao != null) {
            for (int i = 0; i < totalCampos; i++) {
                campos[i] = request.getParameter("campo" + i);
            }
        }
        //--------------------------------------------------
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercicio 3</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Exercicio 3</h1>");
            out.println("<form action=\"Exercicio3.do\" method=\"post\" >");
            int j = totalCampos;
            for (int i = 0; i < totalCampos; i++) {
                out.println("<label>Campo " + i + "</label><br/><input value=\"" + (campos[--j] == null ? "" : campos[j]) + "\" name=\"campo" + i + "\" type=\"text\" size=\"50\" /><br/><br/>");
            }
            out.println("<input type=\"submit\" name=\"bOK\" value=\"Inverter\" />");
            out.println("</form>");
            out.println("<p><a href=\"index.jsp\">Voltar</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
