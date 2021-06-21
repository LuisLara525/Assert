package com.example.demo.controladorServ;

import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.instancias.Producto;
import com.example.demo.instancias.ProductoUsuario;
import com.example.demo.instancias.Usuario;

@RestController
public class ControladorUsuario {

	@Autowired
	ProducerTemplate producerTemplate;
/*
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public List<Usuario> getUsuariosAll() {
		List<Usuario> usuarios = producerTemplate.requestBody("direct:select", null, List.class);
		return usuarios;
	}
	*/
	
	@RequestMapping(value = "/usuario/select", method = RequestMethod.POST)
	public List<Usuario> getUsuarioP(@RequestBody Usuario usuario) {
		List<Usuario> usuarios = producerTemplate.requestBody("direct:select", usuario, List.class);
		return usuarios;
	}
	

	@RequestMapping(value = "/usuario/insert", consumes = "application/json", produces="application/json", method = RequestMethod.POST)
	public String insertUsuario(@RequestBody Usuario usuario) {
		producerTemplate.requestBody("direct:insert", usuario, List.class);
		return "{\"status\":\"true\"}";
	}
	

}
