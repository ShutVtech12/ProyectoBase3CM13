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
@WebServlet(name = "ActualizarCategoria", urlPatterns = {"/ActualizarCategoria"})
public class ActualizarCategoria extends HttpServlet {

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
            out.println("<h1>Actualizar datos de categoria</h1>");
            out.println("<form class='row g-3' name='frmDatosAct' method='get' action='Actualizacion'>");
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
            int num = dto.getEntidad().getIdCategoria();
            String name = dto.getEntidad().getNombreCategoria();
            String desc = dto.getEntidad().getDescripcionCategoria();
            if (dto != null) {
                out.println("<div class='col-4'>");
                out.println("<label for='inputClave' class='form-label'>Clave Categoria</label>");
                out.println("<input type='text' readonly='readonly' name='inputClave' class='form-control' id='inputClave' value='"+num+"'>");
                out.println("</div>");
                out.println("<div class='col-md-4'>");
                out.println("<label for='inputName' class='form-label'>Nombre</label>");
                out.println("<input type='text' name='txtName' class='form-control' id='txtName' value='"+name+"'>");
                out.println("</div>");
                out.println("<div class='col-md-4'>");
                out.println("<label for='inputDescripcion' class='form-label'>Descripción</label>");
                out.println("<input type='text' name='txtDescripcion' class='form-control' id='txtDescripcion' value='"+desc+"'>");
                out.println("</div>");
            }else{
                msg="Registro no encontrado";
                out.println("<div align='center'>");
                out.println("<b>" + msg + "</b>");
                out.println("</div>");
            }
            out.println("<div align='center' class='col-12'>");
            //out.println("<a href='Actualizacion?id='id.value'&txtName&txtDescripcion' class='btn btn-success'>Actualizar</a>");
            out.println("<button type='submit' class='btn btn-primary'>Enviar</button>");
            out.println("<a class='nav-link active' aria-current='page' href='javascript: history.go(-1)'>Regresar</a>");
            out.println("</div>");
            out.println("</form>");
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
