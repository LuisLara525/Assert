package com.example.demo.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sql.SqlComponent;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.instancias.Producto;
import com.example.demo.instancias.ProductoUsuario;
import com.example.demo.instancias.Usuario;

@Service
public class ServiciosProducto extends RouteBuilder {

	@Autowired
	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public SqlComponent sql1(DataSource dataSource) {
		SqlComponent sql = new SqlComponent();
		sql.setDataSource(dataSource);
		return sql;
	}
	
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("direct:selectp").to("sql:select * from PRODUCTO").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) xchg.getIn().getBody();
				List<Producto> productos = new ArrayList<Producto>();
				System.out.println(dataList);
				for (Map<String, String> data : dataList) {
					Producto prod = new Producto(null, null, null);
					prod.setNombrep(data.get("nombrep"));
					prod.setDescr(data.get("descr"));
					prod.setFoto(data.get("foto"));
					productos.add(prod);
				}
				xchg.getIn().setBody(productos);
			}
		});
		
		from("direct:insertp").log("Proccesing Message: ").setHeader("message", body()).process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				ProductoUsuario usuariosp = xchg.getIn().getBody(ProductoUsuario.class);
				Map<String, Object> usuariosMap = new HashMap<String, Object>();
				usuariosMap.put("nombre_usuario", usuariosp.getNombre_usuario());
				usuariosMap.put("nombre_producto", usuariosp.getNombre_producto());
				usuariosMap.put("tiempo", usuariosp.getTiempo());
				xchg.getIn().setBody(usuariosMap);
			}
		}).to("sql:INSERT INTO PRODUCTOS_USUARIOS(nombre_usuario, nombre_producto, tiempo) VALUES (:#nombre_usuario, :#nombre_producto, :#tiempo)");

		
		
		from("direct:selectpu").log("Proccesing Message: ").setHeader("message", body()).process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				ProductoUsuario usuariosp = xchg.getIn().getBody(ProductoUsuario.class);
				Map<String, Object> usuariosMap = new HashMap<String, Object>();
				usuariosMap.put("nombre_usuario", usuariosp.getNombre_usuario());
				xchg.getIn().setBody(usuariosMap);
			}
		}).to("sql:SELECT * FROM PRODUCTOS_USUARIOS WHERE nombre_usuario=:#nombre_usuario").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) xchg.getIn().getBody();
				List<ProductoUsuario> productos = new ArrayList<ProductoUsuario>();
				System.out.println(dataList);
				for (Map<String, String> data : dataList) {
					ProductoUsuario prod = new ProductoUsuario(null, null, null);
					prod.setNombre_usuario(data.get("nombre_usuario"));
					prod.setNombre_producto(data.get("nombre_producto"));
					prod.setTiempo(data.get("tiempo"));
					productos.add(prod);
				}
				xchg.getIn().setBody(productos);
			}
		});
		
		from("direct:deletep").log("Proccesing Message: ").setHeader("message", body()).process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				ProductoUsuario usuariosp = xchg.getIn().getBody(ProductoUsuario.class);
				Map<String, Object> usuariosMap = new HashMap<String, Object>();
				usuariosMap.put("nombre_usuario", usuariosp.getNombre_usuario());
				usuariosMap.put("nombre_producto", usuariosp.getNombre_producto());
				xchg.getIn().setBody(usuariosMap);
			}
		}).to("sql:DELETE FROM PRODUCTOS_USUARIOS WHERE NOMBRE_USUARIO=:#nombre_usuario and NOMBRE_PRODUCTO=:#nombre_producto");
		
		from("direct:updatep").log("Proccesing Message: ").setHeader("message", body()).process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				ProductoUsuario usuariosp = xchg.getIn().getBody(ProductoUsuario.class);
				Map<String, Object> usuariosMap = new HashMap<String, Object>();
				usuariosMap.put("nombre_usuario", usuariosp.getNombre_usuario());
				usuariosMap.put("nombre_producto", usuariosp.getNombre_producto());
				usuariosMap.put("tiempo", usuariosp.getTiempo());
				xchg.getIn().setBody(usuariosMap);
			}
		}).to("sql:UPDATE PRODUCTOS_USUARIOS SET TIEMPO=:#tiempo WHERE NOMBRE_USUARIO=:#nombre_usuario and NOMBRE_PRODUCTO=:#nombre_producto");


		
		
	}
}
