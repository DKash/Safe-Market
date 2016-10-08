/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.Perfil;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.IPerfilDAO;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
public class RNPerfil
{
	// Atributos
	private IPerfilDAO perfilDAO;

	Mensagens msg = new Mensagens();

	// Métodos
	public String validarCampos(Perfil perfil)
	{
		List<String> campos = new ArrayList<>();
		if (perfil.getNome() == null || (perfil.getNome().equals(""))) campos.add(perfil.getNome());
		int tam = campos.size();
		String resultado = "";
		while (tam > 0)
		{
			resultado += " " + msg.getMsg_campo_invalido() + campos.get(tam);
			tam--;
		}
		return resultado;
	}

	public boolean verificarPerfilExistente(Perfil perfil)
	{
		perfilDAO = DAOFactory.getPerfilDAO();
		Perfil p = null;
		try
		{
			p = perfilDAO.pesquisarPerfilPorNome(perfil.getNome());
		}
		catch (PerfilInexistenteException e)
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

	public boolean verificarPerfilExistente(int codigo)
	{
		perfilDAO = DAOFactory.getPerfilDAO();
		Perfil p = null;
		try
		{
			p = perfilDAO.consultarPorId(codigo);
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
		}
		return true;
	}
	
	public Perfil verificarPerfilExistentePorId(int codigo)
	{
		perfilDAO = DAOFactory.getPerfilDAO();
		Perfil p = null;
		try
		{
			p = perfilDAO.consultarPorId(codigo);
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