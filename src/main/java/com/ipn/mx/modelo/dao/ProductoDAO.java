/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dao.dto.ProductoDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jemil
 */
public class ProductoDAO {
    private static final String SQL_INSERT="insert into Producto(nombreProducto, descripcionProducto, precio, existencia, stockMinimo, idCategoria) values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE="update Producto set nombreProducto=?, descripcionProducto=?, precio=?, existencia=?, stockMinimo=?, idCategoria=? where idProducto = ?";
    private static final String SQL_DELETE="delete from Producto where idProducto=?";
    private static final String SQL_READ="select idProducto, nombreProducto, descripcionProducto, precio, existencia, stockMinimo, idCategoria from Producto where idProducto=?";
    private static final String SQL_READ_ALL="select idProducto, nombreProducto, descripcionProducto, precio, existencia, stockMinimo, idCategoria from Producto";
    
    //1.- Cambiar todas las clases
    //2.- Genero una clase extra, un simple donde puedo obtener la conexion y heredar todos los metodos
    //3.- Archivo de propiedades recupero la llave
    //4.- XML
    //5.- Generar data source
    
    private Connection conexion;
    /*private void conectar() {
        String user = "postgres";
        String pwd = "root";
        String url = "jdbc:postgresql://localhost:5432/3CM13";
        String pgDriver = "org.postgresql.Driver";
        try {
            Class.forName(pgDriver);
            conexion = DriverManager.getConnection(url, user, pwd);
        //Solo trabjamos con 2 excepciones:
        //-Que no encuentre el paquete de pgDriver
        //-Algun dato esta mal y no puede
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
    private void conectar() {
        String user = "aucaodstqhrqzl";
        String pwd = "761f12832a5a84802a0bf638d31ebdbb8bede5c324e0b445a00f3544da2b0e2b";
        String url = "jdbc:postgresql://ec2-3-220-214-162.compute-1.amazonaws.com:5432/d6i1pbqaomds91"; //?sslmode=require
        String pgDriver = "org.postgresql.Driver";
        try {
            Class.forName(pgDriver);
            conexion = DriverManager.getConnection(url, user, pwd);
        //Solo trabjamos con 2 excepciones:
        //-Que no encuentre el paquete de pgDriver
        //-Algun dato esta mal y no puede
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void create(ProductoDTO dto)throws SQLException{
        conectar();
        PreparedStatement ps= null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombreProducto());
            ps.setString(2, dto.getEntidad().getDescripcionProducto());
            ps.setFloat(3, dto.getEntidad().getPrecio());
            ps.setInt(4, dto.getEntidad().getExistencia());
            ps.setInt(5, dto.getEntidad().getStockMinimo());
            ps.setInt(6, dto.getEntidad().getIdCategoria());
            ps.executeUpdate();
        } finally{
            if (ps != null) {
                ps.close();
            }
            if (conexion != null)
                conexion.close();
        }
    }
    
    public void update(ProductoDTO dto)throws SQLException{
        conectar();
        PreparedStatement ps= null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombreProducto());
            ps.setString(2, dto.getEntidad().getDescripcionProducto());
            ps.setFloat(3, dto.getEntidad().getPrecio());
            ps.setInt(4, dto.getEntidad().getExistencia());
            ps.setInt(5, dto.getEntidad().getStockMinimo());
            ps.setInt(6, dto.getEntidad().getIdCategoria());
            ps.setInt(7, dto.getEntidad().getIdProducto());
            ps.executeUpdate();
        } finally{
            if (ps != null) {
                ps.close();
            }
            if (conexion != null)
                conexion.close();
        }
    }
    
    public void delete(ProductoDTO dto)throws SQLException{
        conectar();
        PreparedStatement ps= null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdProducto());
            ps.executeUpdate();
        } finally{
            if (ps != null) {
                ps.close();
            }
            if (conexion != null)
                conexion.close();
        }
    }
    
    public ProductoDTO read(ProductoDTO dto) throws SQLException{
        conectar();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = conexion.prepareStatement(SQL_READ);
            ps.setInt(1,dto.getEntidad().getIdProducto());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size()>0){
                return (ProductoDTO) resultados.get(0);
            }else{
                return null;
            }
                    
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null)
                conexion.close();
        }
    }
    
    public List readAll() throws SQLException{
        conectar();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = conexion.prepareStatement(SQL_READ_ALL);
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size()>0){
                return resultados;
            }else{
                return null;
            }
                    
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null)
                conexion.close();
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        while(rs.next()){
            ProductoDTO p = new ProductoDTO();
            p.getEntidad().setIdProducto(rs.getInt("idProducto"));
            p.getEntidad().setNombreProducto(rs.getString("nombreProducto"));
            p.getEntidad().setDescripcionProducto(rs.getString("descripcionProducto"));
            p.getEntidad().setPrecio(rs.getFloat("precio"));
            p.getEntidad().setExistencia(rs.getInt("existencia"));
            p.getEntidad().setStockMinimo(rs.getInt("stockMinimo"));
            p.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            
            resultados.add(p);
        }
        return resultados;
    }
    
    public static void main(String[]args){
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(1);
        try {
            dto = dao.read(dto);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
