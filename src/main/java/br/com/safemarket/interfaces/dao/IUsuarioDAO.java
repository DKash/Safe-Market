package br.com.safemarket.interfaces.dao;

import br.com.safemarket.classesBasicas.Usuario;
import br.com.safemarket.exceptions.LoginInvalidoException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.generics.IDAOGenerico;

/**
 * @author Audry Martins
 *
 */
public interface IUsuarioDAO extends IDAOGenerico<Usuario>
{
	// Métodos
	public Usuario efetuarLogin(Usuario usuario) throws LoginInvalidoException;

	public Usuario pesquisarUsuarioPorEmail(String email) throws UsuarioInexistenteException;
}