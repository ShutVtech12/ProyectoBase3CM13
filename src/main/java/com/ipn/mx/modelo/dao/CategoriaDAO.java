/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dao.dto.CategoriaDTO;
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
public class CategoriaDAO {

    //Los datos que el usuario metera sera reemplazados por ?
    //Manejarlo como un arreglo indice 1 e indice 2
    private static final String SQL_INSERT = "insert into Categoria (nombreCategoria, descripcionCategoria) values (?, ?)";
    private static final String SQL_UPDATE = "update Categoria set nombreCategoria = ?, descripcionCategoria = ? where idCategoria = ?";
    private static final String SQL_DELETE = "delete from Categoria where idCategoria = ?";
    private static final String SQL_READ = "select idCategoria, nombreCategoria, descripcionCategoria from Categoria where idCategoria = ?";
    private static final String SQL_READ_ALL = "select idCategoria, nombreCategoria, descripcionCategoria from Categoria";

    //Para ocupar esta variable importamos java.sql.Connection
    private Connection conexion;
    
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

    //Debemos modificar este por el manejo de Data Source
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
    
    public void create(CategoriaDTO dto) throws SQLException{
        // A partir de aqui ya conectamos con el manejor de datos
        conectar();
        PreparedStatement ps=null;
        try
        {
            ps = conexion.prepareStatement(SQL_INSERT);
            //EL primer argumento hace referencia al nombre de la categoria
            ps.setString(1, dto.getEntidad().getNombreCategoria());
            ps.setString(2, dto.getEntidad().getDescripcionCategoria());
            //Como son consultas de actualizacion se pone
            ps.executeUpdate();
        }finally{
            if (ps != null){
                ps.close();
            }
            if (conexion != null){
                conexion.close();
            }
        }
    }
    
    public void update(CategoriaDTO dto) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombreCategoria());
            ps.setString(2, dto.getEntidad().getDescripcionCategoria());
            ps.setInt(3, dto.getEntidad().getIdCategoria());
            ps.executeUpdate();
        }finally{
            if(ps != null){
                ps.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }
    
    public void delete(CategoriaDTO dto) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdCategoria());
            ps.executeUpdate();
        }finally{
            if(ps != null){
                ps.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }
    
    public CategoriaDTO read(CategoriaDTO dto) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conexion.prepareStatement(SQL_READ);
            ps.setInt(1, dto.getEntidad().getIdCategoria());
            //Es una consulta de seleccion
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size()> 0){
                return (CategoriaDTO) resultados.get(0);
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }
    
    public List readAll() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conexion.prepareStatement(SQL_READ_ALL);
            //Es una consulta de seleccion
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size()>0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while(rs.next()){
            CategoriaDTO dto = new CategoriaDTO();
            dto.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            dto.getEntidad().setNombreCategoria(rs.getString("nombreCategoria"));
            dto.getEntidad().setDescripcionCategoria(rs.getString("descripcionCategoria"));
            resultados.add(dto);
        }
        return resultados;
    }
    
    public static void main(String[] args)
    {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setNombreCategoria("Linea");
        dto.getEntidad().setDescripcionCategoria("Lavadora");
        dto.getEntidad().setIdCategoria(2);
        try{
            dao.update(dto);
        }catch(SQLException ex){
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
