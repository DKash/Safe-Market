/**
 * 
 */
package br.com.safemarket.interfaces.dao;

import br.com.safemarket.classesBasicas.UnidadeMedida;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.interfaces.dao.generics.IDAOGenerico;

/**
 * @author Audry Martins
 *
 */
public interface IUnidadeMedidaDAO extends IDAOGenerico<UnidadeMedida>
{
	// Métodos
	public UnidadeMedida pesquisarUnidadeMedidaPorNome(String nome) throws UnidadeMedidaInexistenteException;
}