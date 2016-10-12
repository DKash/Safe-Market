/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.Categoria;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.ICategoriaDAO;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
public class RNCategoria
{
	// Atributos
	private ICategoriaDAO categoriaDAO;

	Mensagens msg = new Mensagens();

	// Métodos
	public String validarCampos(Categoria categoria)
	{
		List<String> campos = new ArrayList<>();
		if (categoria.getNome() == null || (categoria.getNome().equals(""))) campos.add(categoria.getNome());
		if (categoria.getSubcategoria() == null || (categoria.getSubcategoria().equals("")))
			campos.add(categoria.getSubcategoria());
		int tam = campos.size();
		String resultado = "";
		while (tam > 0)
		{
			resultado += " " + msg.getMsg_campo_invalido() + campos.get(tam);
			tam--;
		}
		return resultado;
	}

	public boolean verificarCategoriaExistente(Categoria categoria)
	{
		categoriaDAO = DAOFactory.getCategoriaDAO();
		Categoria c = null;
		try
		{
			c = categoriaDAO.pesquisarCategoriaPorNome(categoria.getNome());
		}
		catch (CategoriaInexistenteException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		if (c == null)
		{
			return false;
		} else
		{
			return true;
		}
	}

	public boolean verificarCategoriaExistentePorId(int codigo)
	{
		categoriaDAO = DAOFactory.getCategoriaDAO();
		Categoria c = new Categoria();
		try
		{
			c.setCodigo(codigo);
			c = categoriaDAO.consultarPorId(c.getCodigo());
		}
		catch (ClienteInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (ProdutoInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (SupermercadoInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (UsuarioInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (CategoriaInexistenteException e)
		{
			e.printStackTrace();
		}
		catch (MarcaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (UnidadeMedidaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		if (c == null)
		{
			return false;
		}
		return true;
	}

	public Categoria verificarCategoriaExistente(int codigo)
	{
		categoriaDAO = DAOFactory.getCategoriaDAO();
		Categoria c = new Categoria();
		try
		{
			c.setCodigo(codigo);
			c = categoriaDAO.consultarPorId(c.getCodigo());
		}
		catch (ClienteInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (ProdutoInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (SupermercadoInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (UsuarioInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (CategoriaInexistenteException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		catch (MarcaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (UnidadeMedidaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		return c;
	}
}