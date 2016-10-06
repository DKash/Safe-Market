package br.com.safemarket.interfaces.dao;

import br.com.safemarket.classesBasicas.Usuario;
import br.com.safemarket.exceptions.UsuarioInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IUsuarioDAO extends IDAOGenerico<Usuario>
{
	// Métodos
	public Usuario efetuarLogin(Usuario usuario) throws UsuarioInexistenteException;

	public Usuario pesquisarUsuarioPorEmail(String email) throws UsuarioInexistenteException;
}