package com.example.goazen;

public class Usuario {
    private String Nombre;
    private String Apellido;
    private String Contrasena;
    private String Dni;
    private String Adress;
    private Long movil;
    private String email;
    private String usu_tipo;
    private Long c_bancaria;

    public Usuario(){

    }

    public Usuario(String Nombre, String Apellido, String Contrasena, String Dni, String Adress, Long movil, String email, String usu_tipo, Long c_bancaria){
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Contrasena = Contrasena;
        this.Dni = Dni;
        this.Adress = Adress;
        this.movil = movil;
        this.email = email;
        this.usu_tipo = usu_tipo;
        this.c_bancaria = c_bancaria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public Long getMovil() {
        return movil;
    }

    public void setMovil(Long movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsu_tipo() {
        return usu_tipo;
    }

    public void setUsu_tipo(String usu_tipo) {
        this.usu_tipo = usu_tipo;
    }

    public Long getC_bancaria() {
        return c_bancaria;
    }

    public void setC_bancaria(Long c_bancaria) {
        this.c_bancaria = c_bancaria;
    }
}
