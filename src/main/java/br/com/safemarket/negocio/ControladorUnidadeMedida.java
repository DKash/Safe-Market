/**
 * 
 */
package br.com.safemarket.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.safemarket.classesBasicas.Status;
import br.com.safemarket.classesBasicas.UnidadeMedida;
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
import br.com.safemarket.interfaces.dao.IUnidadeMedidaDAO;
import br.com.safemarket.interfaces.negocio.IControladorUnidadeMedida;
import br.com.safemarket.negocio.regras.RNUnidadeMedida;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
@Path("/service")
public class ControladorUnidadeMedida implements IControladorUnidadeMedida
{
	// Atributos
	private IUnidadeMedidaDAO unidadeMedidaDAO;

	private RNUnidadeMedida rnUnidadeMedida = new RNUnidadeMedida();

	private Mensagens msg = new Mensagens();

	// Métodos
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 *           Esse método cadastra uma nova UnidadeMedida
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrarUnidadeMedida")
	@Override
	public String cadastrarUnidadeMedida(UnidadeMedida unidadeMedida)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnUnidadeMedida.validarCampos(unidadeMedida);
		if (resultado.equals(""))
		{
			existe = rnUnidadeMedida.verificarUnidadeMedidaExistente(unidadeMedida);
			if (existe == false)
			{
				try
				{
					unidadeMedidaDAO = DAOFactory.getUnidadeMedidaDAO();
					unidadeMedidaDAO.inserir(unidadeMedida);
					mensagem = msg.getMsg_unidadeMedida_cadastrada_com_sucesso();
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
				catch (UnidadeMedidaExistenteException e)
				{
					e.printStackTrace();
					mensagem = e.getMessage();
				}
				catch (MarcaExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (PerfilExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (CategoriaExistenteException e)
				{
					// e.printStackTrace();
				}
			}
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Essse método altera uma UnidadeMedida já cadastrada
	 **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/alterarUnidadeMedida")
	@Override
	public String alterarUnidadeMedida(UnidadeMedida unidadeMedida)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnUnidadeMedida.validarCampos(unidadeMedida);
		if (resultado.equals(""))
		{
			try
			{
				existe = rnUnidadeMedida.verificarUnidadeMedidaExistentePorId(unidadeMedida.getCodigo());
				if (existe == true)
				{
					unidadeMedidaDAO = DAOFactory.getUnidadeMedidaDAO();
					unidadeMedidaDAO.alterar(unidadeMedida);
					mensagem = msg.getMsg_unidadeMedida_alterada_com_sucesso();
				}
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
			catch (UnidadeMedidaInexistenteException e)
			{
				e.printStackTrace();
				mensagem = e.getMessage();
			}
			catch (MarcaInexistenteException e)
			{
				// e.printStackTrace();
			}
			catch (PerfilInexistenteException e)
			{
				// e.printStackTrace();
			}
			catch (CategoriaInexistenteException e)
			{
				// e.printStackTrace();
			}
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Excluindo uma unidadeMedida pelo código
	 */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/excluirUnidadeMedida/{codigo}")
	@Override
	public String excluirUnidadeMedida(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		String mensagem = "";
		UnidadeMedida unidadeMedida = new UnidadeMedida();
		try
		{
			unidadeMedidaDAO = DAOFactory.getUnidadeMedidaDAO();
			unidadeMedida = unidadeMedidaDAO.consultarPorId(codigo);
			if (unidadeMedida != null)
			{
				if (unidadeMedida.getStatus() == Status.ATIVO)
				{
					unidadeMedida.setStatus(Status.INATIVO);
					unidadeMedidaDAO.alterar(unidadeMedida);
					mensagem = msg.getMsg_unidadeMedida_excluida_com_sucesso();
				} else
				{
					mensagem = msg.getMsg_unidadeMedida_excluida_com_sucesso();
				}
			} else
			{
				mensagem = new UnidadeMedidaInexistenteException().getMessage();
			}
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
		catch (UnidadeMedidaInexistenteException e)
		{
			e.printStackTrace();
			mensagem = e.getMessage();
		}
		catch (MarcaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (CategoriaInexistenteException e)
		{
			// e.printStackTrace();
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Esse método lista todas as unidadeMedidas cadastradas na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodasUnidadeMedidas")
	@Override
	public List<UnidadeMedida> consultarTodasUnidadeMedidas()
	{
		DAOFactory.abrir();
		List<UnidadeMedida> lista = new ArrayList<>();
		try
		{
			unidadeMedidaDAO = DAOFactory.getUnidadeMedidaDAO();
			lista = unidadeMedidaDAO.consultarTodos();
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
		catch (UnidadeMedidaInexistenteException e)
		{
			e.printStackTrace();
		}
		catch (MarcaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (CategoriaInexistenteException e)
		{
			// e.printStackTrace();
		}
		DAOFactory.close();
		if (!lista.isEmpty())
		{
			return lista;
		}
		return null;
	}

	/**
	 * Esse método lista todas as unidadeMedidas ativas cadastradas na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodasUnidadeMedidasAtivas")
	@Override
	public List<UnidadeMedida> consultarTodasUnidadesMedidasAtivas()
	{
		DAOFactory.abrir();
		List<UnidadeMedida> lista = new ArrayList<>();
		try
		{
			unidadeMedidaDAO = DAOFactory.getUnidadeMedidaDAO();
			lista = unidadeMedidaDAO.consultarTodosAtivos();
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
		catch (UnidadeMedidaInexistenteException e)
		{
			e.printStackTrace();
		}
		catch (MarcaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (CategoriaInexistenteException e)
		{
			// e.printStackTrace();
		}
		DAOFactory.close();
		if (!lista.isEmpty())
		{
			return lista;
		}
		return null;
	}

	/**
	 * Esse método pesquisa a unidadeMedida cadastrada na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarUnidadeMedidaPorNome/{nome}")
	@Override
	public UnidadeMedida pesquisarUnidadeMedidaPorNome(@PathParam("nome") String nome)
	{
		DAOFactory.abrir();
		UnidadeMedida c = null;
		try
		{
			unidadeMedidaDAO = DAOFactory.getUnidadeMedidaDAO();
			c = unidadeMedidaDAO.pesquisarUnidadeMedidaPorNome(nome);
		}
		catch (UnidadeMedidaInexistenteException e)
		{
			e.printStackTrace();
		}
		DAOFactory.close();
		if (c == null)
		{
			return null;
		}
		return c;
	}

	/**
	 * Esse método pesquisa o produto cadastrado na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarUnidadeMedidaPorId/{codigo}")
	@Override
	public UnidadeMedida pesquisarUnidadeMedidaPorId(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		UnidadeMedida c = null;
		try
		{
			unidadeMedidaDAO = DAOFactory.getUnidadeMedidaDAO();
			c = unidadeMedidaDAO.consultarPorId(codigo);
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
		DAOFactory.close();
		if (c == null)
		{
			return null;
		}
		return c;
	}
}