package com.example.demo.instancias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ProductoUsuario {
	private String nombre_usuario;
	private String nombre_producto;
	private String tiempo;
}
