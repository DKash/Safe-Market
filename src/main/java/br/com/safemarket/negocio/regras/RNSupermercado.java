/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.Supermercado;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.ISupermercadoDAO;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
public class RNSupermercado
{
	// Atributos
	private ISupermercadoDAO supermercadoDAO;

	Mensagens msg = new Mensagens();

	// Métodos
	public String validarCampos(Supermercado supermercado)
	{
		List<String> campos = new ArrayList<>();
		if (supermercado.getCnpj() == null || (supermercado.getCnpj().equals(""))) campos.add(supermercado.getCnpj());
		if (supermercado.getEmail() == null || (supermercado.getEmail().equals("")))
			campos.add(supermercado.getEmail());
		if (supermercado.getNome() == null || (supermercado.getNome().equals(""))) campos.add(supermercado.getNome());
		if (supermercado.getInscricaoEstadual() == null || (supermercado.getInscricaoEstadual().equals("")))
			campos.add(supermercado.getInscricaoEstadual());
		if (supermercado.getTelefone() == null || (supermercado.getTelefone().equals("")))
			campos.add(supermercado.getTelefone());
		if (supermercado.getEndereco().getCodigo() == 0)
			campos.add(String.valueOf(supermercado.getEndereco().getCodigo()));
		if (supermercado.getUsuario().getCodigo() == 0)
			campos.add(String.valueOf(supermercado.getUsuario().getCodigo()));
		int tam = campos.size();
		String resultado = "";
		while (tam > 0)
		{
			resultado += " " + msg.getMsg_campo_invalido() + campos.get(tam);
			tam--;
		}
		return resultado;
	}

	public boolean verificarSupermercadoExistente(Supermercado supermercado)
	{
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		Supermercado s = null;
		try
		{
			s = supermercadoDAO.pesquisarSupermercadoPorCNPJ(supermercado.getCnpj());
		}
		catch (SupermercadoInexistenteException e)
		{
			e.printStackTrace();
		}
		if (s == null)
		{
			return false;
		} else
		{
			return true;
		}
	}

	public boolean verificarSupermercadoExistentePorId(int codigo)
	{
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		Supermercado s = new Supermercado();
		try
		{
			s.setCodigo(codigo);
			s = supermercadoDAO.consultarPorId(s.getCodigo());
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
			e.printStackTrace();
			e.getMessage();
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
			// e.printStackTrace();
		}
		catch (PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		if (s == null)
		{
			return false;
		} else
		{
			return true;
		}
	}

	public Supermercado verificarSupermercadoExistente(int codigo)
	{
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		Supermercado s = new Supermercado();
		try
		{
			s.setCodigo(codigo);
			s = supermercadoDAO.consultarPorId(s.getCodigo());
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
			e.printStackTrace();
			e.getMessage();
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
			// e.printStackTrace();
		}
		catch (PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		return s;
	}
}