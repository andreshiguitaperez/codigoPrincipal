package com.udea.campeonato.dominio;


import com.udea.campeonato.dto.UsuarioDto;
import com.udea.campeonato.persistencia.entidad.Usuario;
import com.udea.campeonato.persistencia.repositorio.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServicios {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServicios.class);

    private UsuarioRepository usuarioRepository;

    private ModelMapper modelMapper;

    public  UsuarioServicios(UsuarioRepository usuarioRepository, ModelMapper modelMapper){

        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;

    }

    public UsuarioDto crear(UsuarioDto usuarioACrearDto){
        LOGGER.debug("Comenzar a Crear: usuarioACrearDto={}", usuarioACrearDto);

        Usuario usuarioACrear = modelMapper.map(usuarioACrearDto, Usuario.class);
        Usuario resultado = usuarioRepository.save(usuarioACrear);
        UsuarioDto dtoResultado = modelMapper.map(resultado, UsuarioDto.class);

        return dtoResultado;
    }

    public List<UsuarioDto> busquedaCompleta() {
        List<Usuario> listPeople = (List<Usuario>) usuarioRepository.findAll();
        return listPeople.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDto.class))
                .collect(Collectors.toList());
    }

    public UsuarioDto busquedaPorId(Long id) {
        Optional<Usuario> findPersonOptional = usuarioRepository.findById(id);
        Usuario findPerson = findPersonOptional.orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(findPerson, UsuarioDto.class);
    }

    public void eliminar(Long id) {
        UsuarioDto personInDb = busquedaPorId(id);
        Usuario personToDelete = modelMapper.map(personInDb, Usuario.class);
        usuarioRepository.delete(personToDelete);
    }

    public UsuarioDto editar(UsuarioDto usuarioDto) {
        busquedaPorId(usuarioDto.getId());
        return crear(usuarioDto);
    }
}
