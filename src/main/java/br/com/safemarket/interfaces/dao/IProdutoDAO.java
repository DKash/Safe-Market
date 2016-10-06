/**
 * 
 */
package br.com.safemarket.interfaces.dao;

import br.com.safemarket.classesBasicas.Produto;
import br.com.safemarket.exceptions.ProdutoInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IProdutoDAO extends IDAOGenerico<Produto>
{
	// Métodos
	public Produto pesquisarProdutoPorNome(String nome) throws ProdutoInexistenteException;
}