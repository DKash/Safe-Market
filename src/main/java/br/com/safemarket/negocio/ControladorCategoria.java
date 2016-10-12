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

import br.com.safemarket.classesBasicas.Categoria;
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
import br.com.safemarket.interfaces.dao.ICategoriaDAO;
import br.com.safemarket.interfaces.negocio.IControladorCategoria;
import br.com.safemarket.negocio.regras.RNCategoria;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
@Path("/service")
public class ControladorCategoria implements IControladorCategoria
{
	// Atributos
	private ICategoriaDAO categoriaDAO;

	private RNCategoria rnCategoria = new RNCategoria();

	private Mensagens msg = new Mensagens();

	// Métodos
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 *           Esse método cadastra uma nova Categoria
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrarCategoria")
	@Override
	public String cadastrarCategoria(Categoria categoria)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnCategoria.validarCampos(categoria);
		if (resultado.equals(""))
		{
			existe = rnCategoria.verificarCategoriaExistente(categoria);
			if (existe == false)
			{
				try
				{
					categoriaDAO = DAOFactory.getCategoriaDAO();
					categoriaDAO.inserir(categoria);
					mensagem = msg.getMsg_categoria_cadastrada_com_sucesso();
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
					e.printStackTrace();
					mensagem = e.getMessage();
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
					// e.printStackTrace();
				}
			}
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Essse método altera uma Categoria já cadastrada
	 **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/alterarCategoria")
	@Override
	public String alterarCategoria(Categoria categoria)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnCategoria.validarCampos(categoria);
		if (resultado.equals(""))
		{
			try
			{
				existe = rnCategoria.verificarCategoriaExistente(categoria);
				if (existe == true)
				{
					categoriaDAO = DAOFactory.getCategoriaDAO();
					categoriaDAO.alterar(categoria);
					mensagem = msg.getMsg_categoria_alterada_com_sucesso();
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
			catch (CategoriaInexistenteException e)
			{
				e.printStackTrace();
				mensagem = e.getMessage();
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
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Excluindo uma categoria pelo código
	 */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/excluirCategoria/{codigo}")
	@Override
	public String excluirCategoria(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		String mensagem = "";
		Categoria c = null;
		try
		{
			categoriaDAO = DAOFactory.getCategoriaDAO();
			c = categoriaDAO.consultarPorId(codigo);
			if (c != null)
			{
				if (c.getStatus() == Status.ATIVO)
				{
					c.setStatus(Status.INATIVO);
					categoriaDAO.alterar(c);
					mensagem = msg.getMsg_categoria_excluida_com_sucesso();
				} else
				{
					mensagem = msg.getMsg_categoria_excluida_com_sucesso();
				}
			} else
			{
				mensagem = new CategoriaInexistenteException().getMessage();
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
		catch (CategoriaInexistenteException e)
		{
			e.printStackTrace();
			mensagem = e.getMessage();
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
		return mensagem;
	}

	/**
	 * Esse método lista todas as categorias cadastradas na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodasCategorias")
	@Override
	public List<Categoria> consultarTodasCategorias()
	{
		DAOFactory.abrir();
		List<Categoria> lista = new ArrayList<>();
		try
		{
			categoriaDAO = DAOFactory.getCategoriaDAO();
			lista = categoriaDAO.consultarTodos();
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
		if (!lista.isEmpty())
		{
			return lista;
		}
		return null;
	}

	/**
	 * Esse método lista todas as categorias cadastradas na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodasCategoriasAtivas")
	@Override
	public List<Categoria> consultarTodasCategoriasAtivas()
	{
		DAOFactory.abrir();
		List<Categoria> lista = new ArrayList<>();
		try
		{
			categoriaDAO = DAOFactory.getCategoriaDAO();
			lista = categoriaDAO.consultarTodosAtivos();
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
		if (!lista.isEmpty())
		{
			return lista;
		}
		return null;
	}

	/**
	 * Esse método pesquisa a categoria cadastrada na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarCategoriaPorNome/{nome}")
	@Override
	public Categoria pesquisarCategoriaPorNome(@PathParam("nome") String nome)
	{
		DAOFactory.abrir();
		Categoria c = null;
		try
		{
			categoriaDAO = DAOFactory.getCategoriaDAO();
			c = categoriaDAO.pesquisarCategoriaPorNome(nome);
		}
		catch (CategoriaInexistenteException e)
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
	@Path("/pesquisarCategoriaPorId/{codigo}")
	@Override
	public Categoria pesquisarCategoriaPorId(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		Categoria c = null;
		try
		{
			categoriaDAO = DAOFactory.getCategoriaDAO();
			c = categoriaDAO.consultarPorId(codigo);
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