/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.Produto;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
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
		if (produto.getMarca().getCodigo() == 0) campos.add(String.valueOf(produto.getMarca().getCodigo()));
		if (produto.getDataValidade() == null || (produto.getDataValidade().equals("")))
			campos.add(produto.getDataValidade().toString());
		if (produto.getPeso() == 0) campos.add(String.valueOf(produto.getPeso()));
		if (produto.getEstoque() == 0) campos.add(String.valueOf(produto.getEstoque()));
		if (produto.getCategoria().getCodigo() == 0) campos.add(String.valueOf(produto.getCategoria().getCodigo()));
		if (produto.getSupermercado().getCodigo() == 0)
			campos.add(String.valueOf(produto.getSupermercado().getCodigo()));
		if (produto.getUnidadeMedida().getCodigo() == 0)
			campos.add(String.valueOf(produto.getUnidadeMedida().getCodigo()));
		int tam = campos.size();
		String resultado = "";
		while (tam > 0)
		{
			resultado += " " + msg.getMsg_campo_invalido() + campos.get(tam);
			tam--;
		}
		return resultado;
	}

	public boolean verificarProdutoExistente(int codigo)
	{
		produtoDAO = DAOFactory.getProdutoDAO();
		Produto p = null;
		try
		{
			p = produtoDAO.consultarPorId(codigo);
		}
		catch (ClienteInexistenteException | ProdutoInexistenteException | SupermercadoInexistenteException
				| UsuarioInexistenteException | CategoriaInexistenteException | MarcaInexistenteException
				| UnidadeMedidaInexistenteException | PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		if (p == null)
		{
			return false;
		} else
		{
			return true;
		}
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