package com.example.demo.instancias;



public class Producto {

	private String nombrep;
	private String descr;
	private String foto;
	
	
	public Producto(String nombrep, String descr, String foto) {
		super();
		this.nombrep = nombrep;
		this.descr = descr;
		this.foto = foto;
	}
	
	public Producto() {
		
	}
	public String getNombrep() {
		return nombrep;
	}
	public void setNombrep(String nombrep) {
		this.nombrep = nombrep;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
}
