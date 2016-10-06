/**
 * 
 */
package br.com.safemarket.negocio.regras;

import br.com.safemarket.classesBasicas.UnidadeMedida;
import br.com.safemarket.dados.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.IUnidadeMedidaDAO;

/**
 * @author Audry Martins
 *
 */
public class RNUnidadeMedida
{
	// Atributos
	private IUnidadeMedidaDAO unidadeMedidaDAO;

	// Métodos
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
		return um;
	}
}