package com.example.demo.instancias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Producto {

	private String nombrep;
	private String descr;
	private String foto;
}
