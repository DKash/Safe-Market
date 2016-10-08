/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.UnidadeMedida;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.IUnidadeMedidaDAO;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
public class RNUnidadeMedida
{
	// Atributos
	private IUnidadeMedidaDAO unidadeMedidaDAO;

	Mensagens msg = new Mensagens();

	// Métodos
	public String validarCampos(UnidadeMedida unidadeMedida)
	{
		List<String> campos = new ArrayList<>();
		if (unidadeMedida.getNome() == null || (unidadeMedida.getNome().equals("")))
			campos.add(unidadeMedida.getNome());
		int tam = campos.size();
		String resultado = "";
		while (tam > 0)
		{
			resultado += " " + msg.getMsg_campo_invalido() + campos.get(tam);
			tam--;
		}
		return resultado;
	}

	public boolean verificarUnidadeMedidaExistente(UnidadeMedida unidadeMedida)
	{
		unidadeMedidaDAO = DAOFactory.getUnidadeMedidaDAO();
		UnidadeMedida um = null;
		try
		{
			um = unidadeMedidaDAO.pesquisarUnidadeMedidaPorNome(unidadeMedida.getNome());
		}
		catch (UnidadeMedidaInexistenteException e)
		{
			// e.printStackTrace();
		}
		if (um == null)
		{
			return false;
		} else
		{
			return true;
		}
	}

	public boolean verificarUnidadeMedidaExistentePorId(int codigo)
	{
		unidadeMedidaDAO = DAOFactory.getUnidadeMedidaDAO();
		UnidadeMedida um = new UnidadeMedida();
		try
		{
			um.setCodigo(codigo);
			um = unidadeMedidaDAO.consultarPorId(um.getCodigo());
		}
		catch (ClienteInexistenteException | ProdutoInexistenteException | SupermercadoInexistenteException
				| UsuarioInexistenteException | CategoriaInexistenteException | MarcaInexistenteException
				| UnidadeMedidaInexistenteException | PerfilInexistenteException e)
		{
			e.printStackTrace();
		}
		if (um == null)
		{
			return false;
		} else
		{
			return true;
		}
	}

	public UnidadeMedida verificarUnidadeMedidaExistente(int codigo)
	{
		unidadeMedidaDAO = DAOFactory.getUnidadeMedidaDAO();
		UnidadeMedida um = new UnidadeMedida();
		try
		{
			um.setCodigo(codigo);
			um = unidadeMedidaDAO.consultarPorId(um.getCodigo());
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
			// e.printStackTrace();
		}
		catch (MarcaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (UnidadeMedidaInexistenteException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		catch (PerfilInexistenteException e)
		{
			// Oe.printStackTrace();
		}
		return um;
	}
}