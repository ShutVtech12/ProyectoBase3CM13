/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web.categorias;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.dto.CategoriaDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jemil
 */
@WebServlet(name = "VerCategoria", urlPatterns = {"/VerCategoria"})
public class VerCategoria extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ver Categoria</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container' align='center'>");
            out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand'>Menu de Categoria</a>");
            out.println("<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarSupportedContent' aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'>");
            out.println("<span class='navbar-toggler-icon'></span>");
            out.println("</button>");
            out.println("<div class='collapse navbar-collapse' id='navbarNav'>");
            out.println("<ul class='navbar-nav'>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link active' aria-current='page' href='https://practica3cm13.herokuapp.com/'>Inicio</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='TablasDeMultiplicar'>Tablas de Multiplicar</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='MostrarDatosCategoria'>Lista de Categor&iacute;as</a>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='ProductoController?accion=listaDeProductos'>Lista de Productos</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("</nav>");
            out.println("<h1>Datos de Categoria</h1>");
            out.println("<table class='table table-bordered'>");
            out.println("<tr>");
            out.println("<th> Clave </th>");
            out.println("<th> Nombre </th>");
            out.println("<th> Descripcion </th>");
            out.println("</tr>");
            String msg ="";
            CategoriaDAO dao = new CategoriaDAO();
            CategoriaDTO dto = new CategoriaDTO();
            dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
            try {
                //Debemos de condicionar los mensajes
                dto = dao.read(dto);
            } catch (SQLException ex) {
                Logger.getLogger(VerCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (dto != null) {
                out.println("<tr>");
                out.println("<td>" + dto.getEntidad().getIdCategoria() + "</td>");
                out.println("<td>" + dto.getEntidad().getNombreCategoria()+ "</td>");
                out.println("<td>" + dto.getEntidad().getDescripcionCategoria() + "</td>");
                out.println("</tr>");
                out.println("</table>");
            }else{
                out.println("</table>");
                msg="Registro no encontrado";
                out.println("<div align='center'>");
                out.println("<b>" + msg + "</b>");
                out.println("</div>");
            }
            out.println("</div>");
            out.println("<div align='center'>");
            out.println("<a class='nav-link active' aria-current='page' href='javascript: history.go(-1)'>Regresar</a>");
            out.println("</div>");
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
