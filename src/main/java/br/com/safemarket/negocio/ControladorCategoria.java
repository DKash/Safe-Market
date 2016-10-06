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
import br.com.safemarket.dados.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteExistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.ProdutoExistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoExistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
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
		// Falta validar os campos
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
	public String excluirCategoria(int codigo)
	{
		DAOFactory.abrir();
		categoriaDAO = DAOFactory.getCategoriaDAO();
		try
		{
			Categoria categoria = categoriaDAO.consultarPorId(codigo);
			categoria.setStatus(Status.INATIVO);
			categoriaDAO.alterar(categoria);
			return msg.getMsg_categoria_excluida_com_sucesso();
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
			return e.getMessage();
		}
		catch (MarcaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (UnidadeMedidaInexistenteException e)
		{
			// e.printStackTrace();
		}
		DAOFactory.close();
		return "";
	}

	/**
	 * Esse método lista todas as categorias cadastradas na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodosCategorias")
	@Override
	public List<Categoria> consultarTodasCategorias()
	{
		DAOFactory.abrir();
		categoriaDAO = DAOFactory.getCategoriaDAO();
		List<Categoria> categorias = new ArrayList<>();
		try
		{
			categorias = categoriaDAO.consultarTodos();
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
		DAOFactory.close();
		return categorias;
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
		categoriaDAO = DAOFactory.getCategoriaDAO();
		Categoria c = null;
		try
		{
			c = categoriaDAO.pesquisarCategoriaPorNome(nome);
		}
		catch (CategoriaInexistenteException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		if (c == null)
		{
			return null;
		}
		DAOFactory.close();
		return c;
	}

	/**
	 * Esse método pesquisa o produto cadastrado na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarCategoriaPorId/{codigo}")
	/*
	 * (non-Javadoc)
	 * @see
	 * br.com.marketedelivery.camada.interfaces.negocio.IControladorCategoria#
	 * pesquisarCategoriaPorId(int)
	 */
	@Override
	public Categoria pesquisarCategoriaPorId(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		new DAOFactory();
		categoriaDAO = DAOFactory.getCategoriaDAO();
		try
		{
			Categoria c = categoriaDAO.consultarPorId(codigo);
			return c;
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
			e.getMessage();
		}
		catch (MarcaInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (UnidadeMedidaInexistenteException e)
		{
			// e.printStackTrace();
		}
		DAOFactory.close();
		return null;
	}
}