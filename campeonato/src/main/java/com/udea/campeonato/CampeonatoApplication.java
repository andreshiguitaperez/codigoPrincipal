package com.udea.campeonato;

import com.udea.campeonato.persistencia.entidad.Usuario;
import com.udea.campeonato.persistencia.repositorio.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CampeonatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampeonatoApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(UsuarioRepository usuarioRepository) {
		return args -> {
			Usuario usuario1 = new Usuario();
			usuario1.setNombre("Andres");
			usuario1.setApellido("Higuita");
			usuario1.setCorreo("andres.higuita.perez@gmail.com");
			usuario1.setContrasena("jotapeje");
			usuario1.setEdad(21);
			usuarioRepository.save(usuario1);


		};

	}

}
