/**
 * 
 */
package br.com.safemarket.interfaces.negocio;

import java.util.List;

import br.com.safemarket.classesBasicas.Perfil;
import br.com.safemarket.exceptions.PerfilExistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IControladorPerfil
{
	// Métodos
	public String cadastrarPerfil(Perfil perfil) throws PerfilExistenteException;

	public String alterarPerfil(Perfil perfil) throws PerfilInexistenteException;

	public List<Perfil> consultarTodosPerfis() throws PerfilInexistenteException;

	public Perfil pesquisarPerfilPorNome(String nome) throws PerfilInexistenteException;

	public Perfil pesquisarPerfilPorId(int codigo) throws PerfilInexistenteException;
}