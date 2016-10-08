/**
 * 
 */
package br.com.safemarket.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.safemarket.classesBasicas.Perfil;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.CategoriaExistenteException;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteExistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaExistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.PerfilExistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.exceptions.ProdutoExistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoExistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaExistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioExistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.IPerfilDAO;
import br.com.safemarket.interfaces.negocio.IControladorPerfil;
import br.com.safemarket.negocio.regras.RNPerfil;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
@Path("/service")
public class ControladorPerfil implements IControladorPerfil
{
	// Atributos
	private IPerfilDAO perfilDAO;

	private RNPerfil rnPerfil = new RNPerfil();

	private Mensagens msg = new Mensagens();

	// Métodods
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrarPerfil")
	@Override
	public String cadastrarPerfil(Perfil perfil)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnPerfil.validarCampos(perfil);
		if (resultado.equals(""))
		{
			existe = rnPerfil.verificarPerfilExistente(perfil);
			if (existe == false)
			{
				try
				{
					perfilDAO = DAOFactory.getPerfilDAO();
					perfilDAO.inserir(perfil);
					mensagem = msg.getMsg_perfil_cadastrado_com_sucesso();
				}
				catch (ClienteExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (ProdutoExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (SupermercadoExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (UsuarioExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (CategoriaExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (MarcaExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (UnidadeMedidaExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (PerfilExistenteException e)
				{
					e.printStackTrace();
					mensagem = e.getMessage();
				}
			}
		}
		DAOFactory.close();
		return mensagem;
	}

	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/alterarPerfil")
	@Override
	public String alterarPerfil(Perfil perfil)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnPerfil.validarCampos(perfil);
		if (resultado.equals(""))
		{
			existe = rnPerfil.verificarPerfilExistente(perfil.getCodigo());
			if (existe == true)
			{
				try
				{
					perfilDAO = DAOFactory.getPerfilDAO();
					perfilDAO.alterar(perfil);
					mensagem = msg.getMsg_perfil_alterado_com_sucesso();
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
					// e.printStackTrace();
				}
				catch (PerfilInexistenteException e)
				{
					e.printStackTrace();
					mensagem = e.getMessage();
				}
			}
		}
		DAOFactory.close();
		return mensagem;
	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodosPerfis")
	@Override
	public List<Perfil> consultarTodosPerfis()
	{
		DAOFactory.abrir();
		List<Perfil> lista = new ArrayList<>();
		try
		{
			perfilDAO = DAOFactory.getPerfilDAO();
			lista = perfilDAO.consultarTodos();
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
			// e.printStackTrace();
		}
		catch (PerfilInexistenteException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		DAOFactory.close();
		if (!lista.isEmpty())
		{
			return lista;
		}
		return null;
	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarPerfilPorNome/{nome}")
	@Override
	public Perfil pesquisarPerfilPorNome(@PathParam("nome") String nome)
	{
		DAOFactory.abrir();
		Perfil p = null;
		perfilDAO = DAOFactory.getPerfilDAO();
		try
		{
			p = perfilDAO.pesquisarPerfilPorNome(nome);
		}
		catch (PerfilInexistenteException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		DAOFactory.close();
		if (p == null)
		{
			return null;
		}
		return p;
	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarPerfilPorId/{codigo}")
	@Override
	public Perfil pesquisarPerfilPorId(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		Perfil p = null;
		try
		{
			perfilDAO = DAOFactory.getPerfilDAO();
			p = perfilDAO.consultarPorId(codigo);
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
			// e.printStackTrace();
		}
		catch (PerfilInexistenteException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		DAOFactory.close();
		if (p == null)
		{
			return null;
		}
		return p;
	}
}