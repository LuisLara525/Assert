package com.example.demo.instancias;



public class ProductoUsuario {
	private String nombre_usuario;
	private String nombre_producto;
	private String tiempo;
	
	public ProductoUsuario() {
		
	}
	
	public ProductoUsuario(String nombre_usuario, String nombre_producto, String tiempo) {
		super();
		this.nombre_usuario = nombre_usuario;
		this.nombre_producto = nombre_producto;
		this.tiempo = tiempo;
	}
	
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
	
}
