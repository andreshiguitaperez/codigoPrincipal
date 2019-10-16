package com.udea.campeonato.persistencia.repositorio;

import com.udea.campeonato.persistencia.entidad.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
