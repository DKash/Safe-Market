/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.Supermercado;
import br.com.safemarket.dados.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
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
		if (supermercado.getInscricaoEstatdual() == null || (supermercado.getInscricaoEstatdual().equals("")))
			campos.add(supermercado.getInscricaoEstatdual());
		if (supermercado.getEstoque() == 0) campos.add(String.valueOf(supermercado.getEstoque()));
		if (supermercado.getTelefone() == null || (supermercado.getTelefone().equals("")))
			campos.add(supermercado.getTelefone());
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

	public boolean verificarSupermercadoExistente(Supermercado supermercado)
	{
		new DAOFactory();
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		Supermercado s = null;
		try
		{
			s = supermercadoDAO.pesquisarSupermercadoPorCNPJ(supermercado.getCnpj());
		}
		catch (SupermercadoInexistenteException e)
		{
			e.printStackTrace();
			e.getMessage();
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
		return s;
	}
}