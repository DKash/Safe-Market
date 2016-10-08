/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.Marca;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.IMarcaDAO;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
public class RNMarca
{
	// Atributos
	private IMarcaDAO marcaDAO;

	Mensagens msg = new Mensagens();

	// Métodos
	public String validarCampos(Marca marca)
	{
		List<String> campos = new ArrayList<>();
		if (marca.getNome() == null || (marca.getNome().equals(""))) campos.add(marca.getNome());
		int tam = campos.size();
		String resultado = "";
		while (tam > 0)
		{
			resultado += " " + msg.getMsg_campo_invalido() + campos.get(tam);
			tam--;
		}
		return resultado;
	}

	public boolean verificarMarcaExistente(Marca marca)
	{
		marcaDAO = DAOFactory.getMarcaDAO();
		Marca m = null;
		try
		{
			m = marcaDAO.pesquisarMarcaPorNome(marca.getNome());
		}
		catch (MarcaInexistenteException e)
		{
			e.printStackTrace();
		}
		if (m == null)
		{
			return false;
		} else
		{
			return true;
		}
	}

	public boolean verificarMarcaExistente(int codigo)
	{
		marcaDAO = DAOFactory.getMarcaDAO();
		Marca m = new Marca();
		m.setCodigo(codigo);
		try
		{
			m = marcaDAO.consultarPorId(m.getCodigo());
		}
		catch (ClienteInexistenteException | ProdutoInexistenteException | SupermercadoInexistenteException
				| UsuarioInexistenteException | CategoriaInexistenteException | MarcaInexistenteException
				| UnidadeMedidaInexistenteException | PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		if (m == null)
		{
			return false;
		}
		return true;
	}

	public Marca verificarMarcaExistentePorId(int codigo)
	{
		marcaDAO = DAOFactory.getMarcaDAO();
		Marca p = null;
		try
		{
			p = marcaDAO.consultarPorId(codigo);
		}
		catch (ClienteInexistenteException | ProdutoInexistenteException | SupermercadoInexistenteException
				| UsuarioInexistenteException | CategoriaInexistenteException | MarcaInexistenteException
				| UnidadeMedidaInexistenteException | PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		if (p == null)
		{
			return null;
		}
		return p;
	}
}