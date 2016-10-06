package br.com.safemarket.interfaces.negocio;

import java.util.List;

import br.com.safemarket.classesBasicas.Produto;
import br.com.safemarket.exceptions.ProdutoExistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IControladorProduto
{
	// Métodos
	public String cadastrarProduto(Produto produto) throws ProdutoExistenteException;

	public String alterarProduto(Produto produto) throws ProdutoInexistenteException;

	public String excluirProduto(int codigo) throws ProdutoInexistenteException;

	public List<Produto> consultarTodosProdutos() throws ProdutoInexistenteException;

	public Produto pesquisarProdutoPorNome(String nome) throws ProdutoInexistenteException;

	public Produto pesquisarProdutoPorId(int codigo) throws ProdutoInexistenteException;
}