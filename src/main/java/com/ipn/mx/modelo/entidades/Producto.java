/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jemil
 */

//Para agregar el @Data debimos agregar la dependencia lombok en el pom.xml
@Data
//NOs srive para tener referenciado al constructor
@NoArgsConstructor
public class Producto implements Serializable{
    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private BigDecimal precio;
    private int existencia;
    private int stockMinimo;    
    private int idCategoria;
    
    //@Data nos referencia los setter y getters de forma interna
    
}
