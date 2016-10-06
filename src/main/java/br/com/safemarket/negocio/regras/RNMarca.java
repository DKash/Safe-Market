/**
 * 
 */
package br.com.safemarket.negocio.regras;

import br.com.safemarket.classesBasicas.Marca;
import br.com.safemarket.dados.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.IMarcaDAO;

/**
 * @author Audry Martins
 *
 */
public class RNMarca
{
	// Atributos
	private IMarcaDAO marcaDAO;

	// Métodos
	public Marca verificarMarcaExistente(int codigo)
	{
		marcaDAO = DAOFactory.getMarcaDAO();
		Marca m = new Marca();
		try
		{
			m.setCodigo(codigo);
			m = marcaDAO.consultarPorId(m.getCodigo());
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
			e.printStackTrace();
			e.getMessage();
		}
		catch (UnidadeMedidaInexistenteException e)
		{
			// e.printStackTrace();
		}
		return m;
	}
}