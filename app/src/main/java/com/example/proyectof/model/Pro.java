package com.example.proyectof.model;

public class Pro {
    String Nombre,Piezas,color;
    public Pro(){ }

    public Pro(String Nombre,String Piezas,String color){
this.Nombre = Nombre;
this.Piezas = Piezas;
this.color = color;

   }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPiezas() {
        return Piezas;
    }

    public void setPiezas(String piezas) {
        Piezas = piezas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
