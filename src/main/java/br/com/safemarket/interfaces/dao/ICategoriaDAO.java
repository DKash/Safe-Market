/**
 * 
 */
package br.com.safemarket.interfaces.dao;

import br.com.safemarket.classesBasicas.Categoria;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.interfaces.dao.generics.IDAOGenerico;

/**
 * @author Audry Martins
 *
 */
public interface ICategoriaDAO extends IDAOGenerico<Categoria>
{
	// Métodos
	public Categoria pesquisarCategoriaPorNome(String nome) throws CategoriaInexistenteException;
}