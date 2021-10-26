/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web.producto;

import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dao.dto.ProductoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jemil
 */
@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

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
        
        String accion = request.getParameter("accion");
        //String txtNombreProducto=request.getParameter("txtNombreProducto");
        if(accion.equals("listaDeProductos")){
            listaDeProductos(request, response);
        }else{
            if(accion.equals("nuevo")){
                nuevoProducto(request, response);
            }else{
                if(accion.equals("eliminar")){
                    eliminarProducto(request, response);
                }else{
                    if(accion.equals("actualizar")){
                        actualizarProducto(request, response);
                    }else{
                        if(accion.equals("ver")){
                            verProducto(request, response);
                        }else{
                            if(accion.equals("guardar") || request.getParameter("txtNombreProducto")!=null){
                                almacenarProducto(request, response);
                            }
                        }
                    }
                }
            }
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

    private void listaDeProductos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Producto</title>"); 
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand'>Menu de Productos</a>");
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
            out.println("<a class='nav-link' href='ProductoController?accion=listaDeProductos'>Mostrar Datos</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='ProductoController?accion=nuevo'>Nuevo</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("</nav>");
            out.println("<table class='table table-stripped'");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td> Clave </td>");
            out.println("<td> Nombre </td>");
            out.println("<td> Descripcion </td>");
            out.println("<td> Precio </td>");
            out.println("<td> Existencia </td>");
            out.println("<td> Stock </td>");
            out.println("<td> Categoria </td>");
            out.println("<td> Eliminar </td>");
            out.println("<td> Actualizar </td>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            ProductoDAO dao = new ProductoDAO();
            List lista;
            try {
                lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    ProductoDTO dto = (ProductoDTO) lista.get(i);
                    out.println("<tr>");
                    out.println("<td><a href='ProductoController?accion=ver&id=" + dto.getEntidad().getIdProducto() + "' class='btn btn-warning'>" + dto.getEntidad().getIdProducto() + "</td>");
                    out.println("<td>" + dto.getEntidad().getNombreProducto() + "</td>");
                    out.println("<td>" + dto.getEntidad().getDescripcionProducto() + "</td>");
                    out.println("<td>$"+dto.getEntidad().getPrecio()+ "</td>");
                    out.println("<td>" + dto.getEntidad().getExistencia() + "</td>");
                    out.println("<td>" + dto.getEntidad().getStockMinimo() + "</td>");
                    out.println("<td>" + dto.getEntidad().getIdCategoria()+ "</td>");
                    out.println("<td><a href='ProductoController?accion=eliminar&id=" + dto.getEntidad().getIdProducto() + "'class='btn btn-danger'>Eliminar</a></td>");
                    out.println("<td><a href='ProductoController?accion=actualizar&id=" + dto.getEntidad().getIdProducto() + "'class='btn btn-success'>Actualizar</a></td>");
                    out.println("</tr>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            out.println("<div align='center'>");
            out.println("<a href='ProductoController?accion=nuevo' class='btn btn-primary'>Registrar Producto</a>");
            out.println("<a class='nav-link active' aria-current='page' href='https://practica3cm13.herokuapp.com/'>Regresar a la pagina principal</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void nuevoProducto(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("productoForm.html");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        String msg = "";
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Producto</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container' align='center'>");
            try {
                dao.delete(dto);
                msg = "Registro Eliminado";
                out.println("<div class='alert alert-success alert-dismissible fade show' role='alert'>");
                out.println("<strong>" + msg + "</strong>");
                out.println("<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
                out.println("</div>");
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("<div align='center'>");
            out.println("<br/>");
            out.println("<a href='ProductoController?accion=listaDeProductos' class='btn btn-success'>Lista de Productos</a>");
            out.println("</div>");

        }
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        //String txtNombreProducto=request.getParameter("txtNombreProducto");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Actualizar Producto</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container' align='center'>");
            out.println("<form method='post' action='ProductoController?accion=guardar&txtal=1'>");
            out.println("<h1>Actualizar datos de Producto</h1>");
            String msg = "";
            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
            try {
                //Debemos de condicionar los mensajes
                dto = dao.read(dto);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (dto != null) {
                out.println("<table class='table table-stripped'");
                out.println("<tr>");
                out.println("<th> Clave Producto </th>");
                out.println("<td><input type='text' id='id' name='id' readonly='readonly'"
                        + "class='form-control' required='required' value='" + dto.getEntidad().getIdProducto() + "'/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Nombre Producto </th>");
                out.println("<td><input type='text' id='txtNombreProducto' name='txtNombreProducto'"
                        + "class='form-control' required='required' value='" + dto.getEntidad().getNombreProducto() + "'/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Descripcion Producto </th>");
                out.println("<td><input type='text' id='txtDescripcionProducto' name='txtDescripcionProducto'"
                        + "class='form-control' required='required' value='" + dto.getEntidad().getDescripcionProducto() + "'/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Precio </th>");
                out.println("<td><input type='text' id='txtPrecio' name='txtPrecio'"
                        + "class='form-control' required='required' value='" + dto.getEntidad().getPrecio() + "'/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Existencia </th>");
                out.println("<td><input type='text' id='txtExistencia' name='txtExistencia' step='1' min='1' max='100'"
                        + "class='form-control' required='required' value='" + dto.getEntidad().getExistencia() + "'/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Stock </th>");
                out.println("<td><input type='text' id='txtStock' name='txtStock' min='10' max='100'"
                        + "class='form-control' required='required' value='" + dto.getEntidad().getStockMinimo() + "'/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Clave Categoria </th>");
                out.println("<td><input type='text' id='txtClave' name='txtClave' readonly='readonly'"
                        + "class='form-control' required='required' value='" + dto.getEntidad().getIdCategoria() + "'/></td>");
                out.println("</tr>");
                out.println("</table>");
            } else {
                msg = "Registro no encontrado";
                out.println("<div align='center'>");
                out.println("<b>" + msg + "</b>");
                out.println("</div>");
            }
            out.println("<div align='center' class='col-12'>");
            out.println("<button type='submit' class='btn btn-primary' name='btnEnviar'>Enviar</button>");
            out.println("<a class='nav-link active' aria-current='page' href='javascript: history.go(-1)'>Regresar</a>");
            out.println("</div>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void verProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Productos</title>"); 
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div align='center'><h1>Datos de Producto</h1></div>");
            String msg ="";
            ProductoDAO dao = new ProductoDAO();
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
            try {
                //Debemos de condicionar los mensajes
                dto = dao.read(dto);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (dto != null) {
                out.println("<table class='table table-stripped'");
                out.println("<tr>");
                out.println("<th> Clave Producto </th>");
                out.println("<td>" + dto.getEntidad().getIdProducto() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Nombre Producto </th>");
                out.println("<td>" + dto.getEntidad().getNombreProducto() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Descripcion Producto </th>");
                out.println("<td>" + dto.getEntidad().getDescripcionProducto() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Precio </th>");
                out.println("<td>" + dto.getEntidad().getPrecio() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Existencia </th>");
                out.println("<td>" + dto.getEntidad().getExistencia() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Stock </th>");
                out.println("<td>" + dto.getEntidad().getStockMinimo() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Clave Categoria </th>");
                out.println("<td>" + dto.getEntidad().getIdCategoria() + "</td>");
                out.println("</tr>");
                out.println("</table>");
            }else{
                msg="Registro no encontrado";
                out.println("<div align='center'>");
                out.println("<b>" + msg + "</b>");
                out.println("</div>");
            }
            out.println("</div>");
            out.println("<div align='center'>");
            out.println("<a class='nav-link active' aria-current='page' href='ProductoController?accion=listaDeProductos'>Regresar</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void almacenarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String msg="";
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Producto</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container' align='center'>");
            if (Integer.parseInt(request.getParameter("txtal")) == 1) {
                dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
                dto.getEntidad().setNombreProducto(request.getParameter("txtNombreProducto"));
                dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcionProducto"));
                dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("txtPrecio")));
                dto.getEntidad().setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
                dto.getEntidad().setStockMinimo(Integer.parseInt(request.getParameter("txtStock")));
                dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtClave")));
                try {
                    dao.update(dto);
                    msg = "Registro Actualizado";
                    out.println("<div class='alert alert-success alert-dismissible fade show' role='alert'>");
                    out.println("<strong>" + msg + "</strong>");
                    out.println("<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
                    out.println("</div>");
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                dto.getEntidad().setNombreProducto(request.getParameter("txtNombreProducto"));
                dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcionProducto"));
                dto.getEntidad().setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
                dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("txtPrecio")));
                dto.getEntidad().setStockMinimo(Integer.parseInt(request.getParameter("txtStockMinimo")));
                dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtClaveCategoria")));
                try {
                    dao.create(dto);
                    msg = "Nuevo Producto Guardado";
                    out.println("<div class='alert alert-success alert-dismissible fade show' role='alert'>");
                    out.println("<strong>" + msg + "</strong>");
                    out.println("<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
                    out.println("</div>");
                } catch (SQLException ex) {
                    msg = "Producto no guardado. ERROR: Categoria Inexistente";
                    out.println("<div class='alert alert-warning alert-dismissible fade show' role='alert'>");
                    out.println("<strong>" + msg + "</strong>");
                    out.println("<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
                    out.println("</div>");
                    Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            out.println("<div align='center'>");
            out.println("<br/>");
            out.println("<a href='ProductoController?accion=listaDeProductos' class='btn btn-success'>Lista de Productos</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        }
    }

}
