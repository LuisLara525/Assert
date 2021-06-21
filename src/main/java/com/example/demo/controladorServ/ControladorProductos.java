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
public class ControladorProductos {

	@Autowired
	ProducerTemplate producerTemplate;

	@RequestMapping(value = "/producto", method = RequestMethod.GET)
	public List<Producto> getUsuariosAll() {
		List<Producto> productos = producerTemplate.requestBody("direct:selectp", null, List.class);
		return productos;
	}
	
	@RequestMapping(value = "/productoU/insert", consumes = "application/json", produces="application/json", method = RequestMethod.POST)
	public String insertProducto(@RequestBody ProductoUsuario usuariop) {
		producerTemplate.requestBody("direct:insertp", usuariop, List.class);
		return "{\"status\":\"true\"}";
	}
	
	@RequestMapping(value = "/productoU/select", method = RequestMethod.POST)
	public List<ProductoUsuario> getUsuarioP(@RequestBody ProductoUsuario usuariop) {
		List<ProductoUsuario> productos = producerTemplate.requestBody("direct:selectpu", usuariop, List.class);
		return productos;
	}
	
	
	@RequestMapping(value = "/productoU/delete", consumes = "application/json", produces="application/json", method = RequestMethod.POST)
	public String deleteProductoU(@RequestBody ProductoUsuario usuariop) {
		producerTemplate.requestBody("direct:deletep", usuariop, List.class);
		return "{\"status\":\"true\"}";
	}
	
	@RequestMapping(value = "/productoU/update", consumes = "application/json", produces="application/json", method = RequestMethod.POST)
	public String UpdateProductoU(@RequestBody ProductoUsuario usuariop) {
		producerTemplate.requestBody("direct:updatep", usuariop, List.class);
		return "{\"status\":\"true\"}";
	}
}
