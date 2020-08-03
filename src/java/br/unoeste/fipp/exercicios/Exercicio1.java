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
@WebServlet(name = "Exercicio1", urlPatterns = {"/Exercicio1.do"})
public class Exercicio1 extends HttpServlet {

    protected void imprimeTabelas(PrintWriter out, int totalFiguras)
            throws ServletException, IOException {
        int colunas[] = {2, 3, 7};
        for (int qtdColunas : colunas) {
            out.println(String.format("<h2>Tabela com %d colunas</h2>", qtdColunas));
            out.println("<table border=\"1\">");
            out.println("<tr>");
            for (int i = 1; i <= totalFiguras; i++) {
                int resto = i % qtdColunas;
                String src = "img/fipp" + i + ".jpg";
                String caminhoVirtual = "/" + src;
                String caminhoReal = getServletContext().getRealPath(caminhoVirtual);
                out.print("<td");
                if (i == totalFiguras && resto != 0) {
                    out.print(" colspan=\"" + (qtdColunas - resto + 1) + "\"");
                }
                out.println(">");
                out.println("   <img src=\"" + src + "\" alt=\"" + src + "\"/>");
                out.println("   <p>Virtual: " + caminhoVirtual + "</p>");
                out.println("   <p>Real: " + caminhoReal + "</p>");
                out.println("</td>");
                if (resto == 0 && i != totalFiguras) {
                    out.println("</tr>");
                    out.println("<tr>");
                }
            }
            out.println("</tr>");
            out.println("</table>");
        }
    }

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

        int totalFiguras = 12;

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercicio 1</title>");
            out.println("<link href=\"css/figuras.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Exercicio 1</h1>");
            this.imprimeTabelas(out, totalFiguras);
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
