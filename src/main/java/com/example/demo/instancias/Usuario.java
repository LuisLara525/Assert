package com.example.demo.instancias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Data
@AllArgsConstructor
@ToString
public class Usuario {
	private String email;
	private String password;
	private String nombre;
	private String apellido;
	private String telefono;	
	
}