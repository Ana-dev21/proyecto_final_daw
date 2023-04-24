package com.edix.cookbook.services.impl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.repository.UsuarioRepository;
import com.edix.cookbook.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired UsuarioRepository uRepo;

	@Override
	public List<Usuario> findAll() {
		return uRepo.findAll();
	}

	@Override
	public Usuario findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario registerNewUsuario (Usuario usuario) throws Exception {
		if (!this.checkIfUserExists(usuario) && usuario != null) {
			Calendar cal = Calendar.getInstance();
	        usuario.setFechaRegistro(cal.getTime());
			return uRepo.save(usuario);
		}else
			throw new Exception ("El mail o username ya existe");
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public Usuario login(String email, String password) {
        Usuario usuario = this.findByEmail(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }
	
	@Override
	public Usuario findByEmail(String email) {
		Usuario usuario = uRepo.findByEmail(email);
		if (usuario !=null) {}
		return usuario;
	}

	@Override
	public Usuario findByUsername(String username) {
		Usuario usuario = uRepo.findByUsername(username);
		return usuario;
	}

	@Override
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Boolean checkIfUserExists(Usuario usuario) {
		Usuario userSameEmail = this.findByEmail(usuario.getEmail());
		Usuario userSameUsername = this.findByUsername(usuario.getUsername());
		if (userSameEmail !=null && userSameUsername != null) {
			return true;
		}else
			return false;
	}

}
