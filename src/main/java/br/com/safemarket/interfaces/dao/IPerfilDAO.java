/**
 * 
 */
package br.com.safemarket.interfaces.dao;

import br.com.safemarket.classesBasicas.Perfil;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.interfaces.dao.generics.IDAOGenerico;

/**
 * @author Audry Martins
 *
 */
public interface IPerfilDAO extends IDAOGenerico<Perfil>
{
	// Métodos
	public Perfil pesquisarPerfilPorNome(String nome) throws PerfilInexistenteException;
}