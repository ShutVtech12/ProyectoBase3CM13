/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jemil
 */
public class Conexion {
    //Para ocupar esta variable importamos java.sql.Connection
    public Connection cone;
    
    public Connection conectar() {
        String user = "aucaodstqhrqzl";
        String pwd = "761f12832a5a84802a0bf638d31ebdbb8bede5c324e0b445a00f3544da2b0e2b";
        String url = "jdbc:postgresql://ec2-3-220-214-162.compute-1.amazonaws.com:5432/d6i1pbqaomds91"; //?sslmode=require
        String pgDriver = "org.postgresql.Driver";
        try {
            Class.forName(pgDriver);
            cone = DriverManager.getConnection(url, user, pwd);
        //Solo trabjamos con 2 excepciones:
        //-Que no encuentre el paquete de pgDriver
        //-Algun dato esta mal y no puede
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cone;
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

    public static class conectar extends Conexion {

        public Connection cone;

        public Connection conectar() {
            String user = "aucaodstqhrqzl";
            String pwd = "761f12832a5a84802a0bf638d31ebdbb8bede5c324e0b445a00f3544da2b0e2b";
            String url = "jdbc:postgresql://ec2-3-220-214-162.compute-1.amazonaws.com:5432/d6i1pbqaomds91"; //?sslmode=require
            String pgDriver = "org.postgresql.Driver";
            try {
                Class.forName(pgDriver);
                cone = DriverManager.getConnection(url, user, pwd);
                //Solo trabjamos con 2 excepciones:
                //-Que no encuentre el paquete de pgDriver
                //-Algun dato esta mal y no puede
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cone;
        }
    }
}
