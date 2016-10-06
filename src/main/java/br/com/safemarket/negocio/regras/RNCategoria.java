/**
 * 
 */
package br.com.safemarket.negocio.regras;

import br.com.safemarket.classesBasicas.Categoria;
import br.com.safemarket.dados.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
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

	// Construtores
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
		return c;
	}
}