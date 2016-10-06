/**
 * 
 */
package br.com.safemarket.interfaces.dao;

import br.com.safemarket.classesBasicas.UnidadeMedida;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IUnidadeMedidaDAO extends IDAOGenerico<UnidadeMedida>
{
	// Métodos
	public UnidadeMedida pesquisarUnidadeMedidaPorNome(String nome) throws UnidadeMedidaInexistenteException;
}