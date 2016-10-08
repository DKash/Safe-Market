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

import br.com.safemarket.classesBasicas.Marca;
import br.com.safemarket.classesBasicas.Status;
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
import br.com.safemarket.interfaces.dao.IMarcaDAO;
import br.com.safemarket.interfaces.negocio.IControladorMarca;
import br.com.safemarket.negocio.regras.RNMarca;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
@Path("/service")
public class ControladorMarca implements IControladorMarca
{
	// Atributos
	private IMarcaDAO marcaDAO;

	private RNMarca rnMarca = new RNMarca();

	private Mensagens msg = new Mensagens();

	// Métodos
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 *           Esse método cadastra uma nova Marca
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrarMarca")
	@Override
	public String cadastrarMarca(Marca marca)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnMarca.validarCampos(marca);
		if (resultado.equals(""))
		{
			existe = rnMarca.verificarMarcaExistente(marca);
			if (existe == false)
			{
				try
				{
					marcaDAO = DAOFactory.getMarcaDAO();
					marcaDAO.inserir(marca);
					mensagem = msg.getMsg_marca_cadastrada_com_sucesso();
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
				catch (MarcaExistenteException e)
				{
					e.printStackTrace();
					mensagem = e.getMessage();
				}
				catch (PerfilExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (CategoriaExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (UnidadeMedidaExistenteException e)
				{
					// e.printStackTrace();
				}
			}
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Essse método altera uma Marca já cadastrada
	 **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/alterarMarca")
	@Override
	public String alterarMarca(Marca marca)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnMarca.validarCampos(marca);
		if (resultado.equals(""))
		{
			try
			{
				existe = rnMarca.verificarMarcaExistente(marca.getCodigo());
				if (existe == true)
				{
					marcaDAO = DAOFactory.getMarcaDAO();
					marcaDAO.alterar(marca);
					mensagem = msg.getMsg_marca_alterada_com_sucesso();
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
			catch (MarcaInexistenteException e)
			{
				e.printStackTrace();
				mensagem = e.getMessage();
			}
			catch (PerfilInexistenteException e)
			{
				// e.printStackTrace();
			}
			catch (CategoriaInexistenteException e)
			{
				// e.printStackTrace();
			}
			catch (UnidadeMedidaInexistenteException e)
			{
				// e.printStackTrace();
			}
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Excluindo uma marca pelo código
	 */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/excluirMarca/{codigo}")
	@Override
	public String excluirMarca(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		String mensagem = "";
		Marca marca = new Marca();
		try
		{
			marcaDAO = DAOFactory.getMarcaDAO();
			marca = marcaDAO.consultarPorId(codigo);
			if (marca != null)
			{
				if (marca.getStatus() == Status.ATIVO)
				{
					marca.setStatus(Status.INATIVO);
					marcaDAO.alterar(marca);
					mensagem = msg.getMsg_marca_excluida_com_sucesso();
				} else
				{
					mensagem = msg.getMsg_marca_excluida_com_sucesso();
				}
			} else
			{
				mensagem = new MarcaInexistenteException().getMessage();
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
		catch (MarcaInexistenteException e)
		{
			e.printStackTrace();
			mensagem = e.getMessage();
		}
		catch (PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (CategoriaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (UnidadeMedidaInexistenteException e)
		{
			// e.printStackTrace();
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Esse método lista todas as marcas cadastradas na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodasMarcas")
	@Override
	public List<Marca> consultarTodasMarcas()
	{
		DAOFactory.abrir();
		List<Marca> lista = new ArrayList<>();
		try
		{
			marcaDAO = DAOFactory.getMarcaDAO();
			lista = marcaDAO.consultarTodos();
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
		catch (MarcaInexistenteException e)
		{
			e.printStackTrace();
		}
		catch (PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (CategoriaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (UnidadeMedidaInexistenteException e)
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
	 * Esse método lista todas as marcas ativas cadastradas na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodasMarcasAtivas")
	@Override
	public List<Marca> consultarTodasMarcasAtivas() throws MarcaInexistenteException
	{
		DAOFactory.abrir();
		List<Marca> lista = new ArrayList<>();
		try
		{
			marcaDAO = DAOFactory.getMarcaDAO();
			lista = marcaDAO.consultarTodosAtivos();
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
		catch (MarcaInexistenteException e)
		{
			e.printStackTrace();
		}
		catch (PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (CategoriaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (UnidadeMedidaInexistenteException e)
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
	 * Esse método pesquisa a marca cadastrada na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarMarcaPorNome/{nome}")
	@Override
	public Marca pesquisarMarcaPorNome(@PathParam("nome") String nome)
	{
		DAOFactory.abrir();
		Marca c = null;
		try
		{
			marcaDAO = DAOFactory.getMarcaDAO();
			c = marcaDAO.pesquisarMarcaPorNome(nome);
		}
		catch (MarcaInexistenteException e)
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
	@Path("/pesquisarMarcaPorId/{codigo}")
	@Override
	public Marca pesquisarMarcaPorId(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		Marca c = null;
		try
		{
			marcaDAO = DAOFactory.getMarcaDAO();
			c = marcaDAO.consultarPorId(codigo);
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
		catch (PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (UnidadeMedidaInexistenteException e)
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