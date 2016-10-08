/**
 * 
 */
package br.com.safemarket.interfaces.negocio;

import java.util.List;

import br.com.safemarket.classesBasicas.Usuario;
import br.com.safemarket.exceptions.UsuarioExistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;

/**
 * @author Audry Martins
 *
 *
 */
public interface IControladorUsuario
{
	// Métodos
	public String cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException;

	public String alterarUsuario(Usuario usuario) throws UsuarioInexistenteException;

	public String excluirUsuario(int codigo) throws UsuarioInexistenteException;

	public List<Usuario> consultarTodosUsuarios() throws UsuarioInexistenteException;

	public Usuario efetuarLogin(Usuario usuario) throws UsuarioInexistenteException;
}