/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.Produto;
import br.com.safemarket.dados.DAOFactory;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.interfaces.dao.IProdutoDAO;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
public class RNProduto
{
	// Atributos
	private IProdutoDAO produtoDAO;

	Mensagens msg = new Mensagens();

	// Métodos
	public String validarCampos(Produto produto)
	{
		List<String> campos = new ArrayList<>();
		if (produto.getNome() == null || (produto.getNome().equals(""))) campos.add(produto.getNome());
		if (produto.getDescricao() == null || (produto.getDescricao().equals(""))) campos.add(produto.getDescricao());
		if (produto.getMarca().getNome() == null || (produto.getMarca().getNome().equals("")))
			campos.add(produto.getMarca().getNome());
		if (produto.getDataValidade() == null || (produto.getDataValidade().equals("")))
			campos.add(produto.getDataValidade().toString());
		if (produto.getPeso() == 0) campos.add(String.valueOf(produto.getPeso()));
		if (produto.getEstoque() == 0) campos.add(String.valueOf(produto.getEstoque()));
		if (produto.getCategoria().getNome() == null || (produto.getCategoria().getNome().equals("")))
			campos.add(produto.getCategoria().getNome());
		if (produto.getCategoria().getSubcategoria() == null || (produto.getCategoria().getSubcategoria().equals("")))
			campos.add(produto.getCategoria().getSubcategoria().toString());
		if (produto.getSupermercado().getCodigo() == 0)
			campos.add(String.valueOf(produto.getSupermercado().getCodigo()));
		if (produto.getUnidadeMedida().getNome() == null || (produto.getUnidadeMedida().getNome().equals("")))
			campos.add(produto.getUnidadeMedida().getNome());
		int tam = campos.size();
		String resultado = "";
		do
		{
			resultado += " " + msg.getMsg_campo_invalido() + campos.get(tam);
			tam--;
		}
		while (tam >= 0);
		return resultado;
	}

	public boolean verificarProdutoExistente(Produto produto)
	{
		produtoDAO = DAOFactory.getProdutoDAO();
		Produto p = null;
		try
		{
			p = produtoDAO.pesquisarProdutoPorNome(produto.getNome());
		}
		catch (ProdutoInexistenteException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		if (p == null)
		{
			return false;
		} else
		{
			return true;
		}
	}
}