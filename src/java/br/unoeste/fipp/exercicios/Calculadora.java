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
@WebServlet(name = "Calculadora", urlPatterns = {"/calculadora.do", "/Exercicio5.do"})
public class Calculadora extends HttpServlet {

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
        String erro = "";
        String botao = request.getParameter("ok");
        String valor1 = request.getParameter("campo1");
        String valor2 = request.getParameter("campo2");
        String operacao = request.getParameter("operacao");

        String[] selected = {"", "", "", "", ""};

        double resposta = 0;
        double val1, val2;
        if (botao != null) {//botão submit foi clicado
            try {
                val1 = Double.parseDouble(valor1);
            } catch (NumberFormatException | NullPointerException ex) {
                erro += "<li>Primeiro valor incorreto</li>";
                val1 = 0;
            }
            boolean erro2 = false;
            try {
                val2 = Double.parseDouble(valor2);
            } catch (NumberFormatException | NullPointerException ex) {
                erro += "<li>Segundo valor incorreto</li>";
                val2 = 0;
                erro2 = true;
            }
            switch (operacao) {
                case "+":
                    resposta = val1 + val2;
                    selected[0] = " selected=\"selected\"";
                    break;
                case "-":
                    resposta = val1 - val2;
                    selected[1] = " selected=\"selected\"";
                    break;
                case "*":
                    resposta = val1 * val2;
                    selected[2] = " selected=\"selected\"";
                    break;
                case "/":
                    if (val2 == 0) {
                        if (!erro2) {
                            erro += "<li>Divisão por zero.</li>";
                        }
                    } else {
                        resposta = val1 / val2;
                    }
                    selected[3] = " selected=\"selected\"";
                    break;
                case "!":
                    selected[4] = " selected=\"selected\"";
                    break;
                default:
                    erro += "<li>Operação não definida.</li>";
            }

        }

        if (request.getParameter("limpar") != null) {
            valor1 = "";
            valor2 = "";
            selected[0] = " selected=\"selected\"";
        }

        //-------- Tordesilhas --------
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Calculadora</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Calculadora</h1>");
            if (!erro.isEmpty()) {
                out.println("<ul>" + erro + "</ul>");
            }
            out.println("<form method=\"post\" action=\"calculadora.do\">");
            out.println("<input type=\"text\" name=\"campo1\" value=\"" + (valor1 == null ? "" : valor1) + "\"/>");
            out.println("<select NAME=\"operacao\">");
            out.println("    <OPTION value=\"+\"" + selected[0] + ">+</OPTION>");
            out.println("    <OPTION value=\"-\"" + selected[1] + ">-</OPTION>");
            out.println("    <OPTION value=\"*\"" + selected[2] + ">*</OPTION>");
            out.println("    <OPTION value=\"/\"" + selected[3] + ">/</OPTION>");
            out.println("    <OPTION value=\"!\"" + selected[4] + ">Fatorial</OPTION>");
            out.println("</select>");
            out.println("<INPUT TYPE=\"text\" NAME=\"campo2\" VALUE=\"" + (valor2 == null ? "" : valor2) + "\"/>");
            out.println("<INPUT TYPE=\"submit\" NAME=\"ok\" VALUE=\"=\"/>");
            out.println("<INPUT TYPE=\"text\" NAME=\"result\" VALUE=\"" + resposta + "\"/>");
            out.println("<br/><INPUT TYPE=\"submit\" NAME=\"limpar\" VALUE=\"C\"/>");
            out.println("</form>");
            out.println("<p><a href=\"index.jsp\">Voltar</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        return "Servlet para implementar uma calculadora.";
    }

}
