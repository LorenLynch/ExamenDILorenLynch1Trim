/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examendilorenlynch;

import java.text.DecimalFormat;

/**
 *
 * @author loren
 */
public class Persona {
    
    String nombre;
    String sexo;
    Integer edad;
    Double peso;
    Integer altura;
    String actividad;
    Double GER;
    Double GET;
    Integer actividadValor;

    public Double getGER() {
        return GER;
    }

    public void setGER(Double GER) {
        this.GER = GER;
        
    }

    public Double getGET() {
        return GET;
    }

    public void setGET(Double GET) {
        this.GET = GET;
    }
    
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Integer getActividadValor() {
        return actividadValor;
    }

    public void setActividadValor() {
        Integer valor = 1;
        if(this.sexo.equals("Mujer")){
            switch(this.actividad){
                case "Muy ligera": 
                    valor = 13;
                    break;
                case "Ligera": 
                    valor = 15;
                    break;
                case "Moderada": 
                    valor = 16;
                    break;
                case "Intensa": 
                    valor = 19;
                    break;
            }
        }else if(this.sexo.equals("Hombre")){
            switch(this.actividad){
                case "Muy ligera": 
                    valor = 13;
                    break;
                case "Ligera": 
                    valor = 16;
                    break;
                case "Moderada": 
                    valor = 17;
                    break;
                case "Intensa": 
                    valor = 21;
                    break;
            }
        }
        
        this.actividadValor = valor;
    }
    
}
