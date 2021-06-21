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

import com.example.demo.controladorServ.ControladorUsuario;
import com.example.demo.instancias.ProductoUsuario;
import com.example.demo.instancias.Usuario;

import lombok.Getter;
import lombok.Setter;

@Service
public class ServiciosUsuario extends RouteBuilder {
	


	@Autowired
	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public SqlComponent sql(DataSource dataSource) {
		SqlComponent sql = new SqlComponent();
		sql.setDataSource(dataSource);
		return sql;
	}

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		// RUTA PARA INSERTAR USUARIOS
		
		from("direct:insert").log("Proccesing Message: ").setHeader("message", body()).process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				Usuario usuarios = xchg.getIn().getBody(Usuario.class);
				Map<String, Object> usuariosMap = new HashMap<String, Object>();
				usuariosMap.put("email", usuarios.getEmail());
				usuariosMap.put("password", usuarios.getPassword());
				usuariosMap.put("nombre", usuarios.getNombre());
				usuariosMap.put("apellido", usuarios.getApellido());
				usuariosMap.put("telefono", usuarios.getTelefono());
				xchg.getIn().setBody(usuariosMap);
			}
		}).to("sql:INSERT INTO USUARIO(email, password, nombre, apellido, telefono) VALUES (:#email,:#password, :#nombre, :#apellido, :#telefono)");

		// RUTA PARA VER USUARIOS
		
		from("direct:select").log("Proccesing Message: ").setHeader("message", body()).process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				Usuario usuarios = xchg.getIn().getBody(Usuario.class);
				Map<String, Object> usuariosMap = new HashMap<String, Object>();
				usuariosMap.put("email", usuarios.getEmail());
				usuariosMap.put("password", usuarios.getPassword());
				xchg.getIn().setBody(usuariosMap);
			}
		}).to("sql:SELECT * FROM USUARIO WHERE email=:#email and password=:#password").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) xchg.getIn().getBody();
				List<Usuario> usuarios = new ArrayList<Usuario>();
				System.out.println(dataList);
				for (Map<String, String> data : dataList) {
					Usuario usuario = new Usuario(null, null, null, null, null);
					usuario.setEmail(data.get("email"));
					usuario.setPassword(data.get("password"));
					usuario.setNombre(data.get("nombre"));
					usuario.setApellido(data.get("apellido"));
					usuario.setTelefono(data.get("telefono"));
					usuarios.add(usuario);
				}
				xchg.getIn().setBody(usuarios);
			}
		});
		/*
		from("direct:select").to("sql:select * from USUARIO").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) xchg.getIn().getBody();
				List<Usuario> usuarios = new ArrayList<Usuario>();
				System.out.println(dataList);
				for (Map<String, String> data : dataList) {
					Usuario usuario = new Usuario(null, null, null, null, null);
					usuario.setEmail(data.get("email"));
					usuario.setPassword(data.get("password"));
					usuario.setNombre(data.get("nombre"));
					usuario.setApellido(data.get("apellido"));
					usuario.setTelefono(data.get("telefono"));
					usuarios.add(usuario);
				}
				xchg.getIn().setBody(usuarios);
			}
		});
		
	*/
	}
}
