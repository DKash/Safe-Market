/**
 * 
 */
package br.com.safemarket.interfaces.dao;

import java.util.List;

import br.com.safemarket.classesBasicas.Produto;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.interfaces.dao.generics.IDAOGenerico;

/**
 * @author Audry Martins
 *
 */
public interface IProdutoDAO extends IDAOGenerico<Produto>
{
	// Métodos
	public Produto pesquisarProdutoPorNome(String nome) throws ProdutoInexistenteException;

	public List<Produto> pesquisarProdutosPorMarca(String marca) throws ProdutoInexistenteException;

	public List<Produto> pesquisarProdutosPorPreco(double preco) throws ProdutoInexistenteException;
}