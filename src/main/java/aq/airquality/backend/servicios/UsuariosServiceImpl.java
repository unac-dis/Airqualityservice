package aq.airquality.backend.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import aq.airquality.backend.entidades.Usuarios;
import aq.airquality.backend.repositorios.UsuariosRepository;

@Service("usuariosService")
public class UsuariosServiceImpl implements UsuariosService {
	
	@Autowired
	@Qualifier("usuariosRepository")
	private UsuariosRepository usuariosService;
	
	@Override
	public Usuarios saveUsuariosService(Usuarios usuarios) {
		return usuariosService.save(usuarios);
	}

}
