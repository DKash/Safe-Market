/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package br.com.safemarket.negocio;

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
import br.com.safemarket.classesBasicas.Supermercado;
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
import br.com.safemarket.interfaces.dao.ISupermercadoDAO;
import br.com.safemarket.interfaces.negocio.IControladorSupermercado;
import br.com.safemarket.negocio.regras.RNSupermercado;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
@Path("/service")
public class ControladorSupermercado implements IControladorSupermercado
{
	private ISupermercadoDAO supermercadoDAO;

	private RNSupermercado rnSupermercado = new RNSupermercado();

	private Mensagens msg = new Mensagens();

	/**
	 * @throws SupermercadoInexistenteException
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 *           Esse método cadastra um novo supermercado
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrarSupermercado")
	public String cadastrarSupermercado(Supermercado supermercado)
	{
		DAOFactory.abrir();
		String resultado = rnSupermercado.validarCampos(supermercado);
		if (!resultado.equals("") || resultado.length() != 0)
		{
			boolean existe = rnSupermercado.verificarSupermercadoExistente(supermercado);
			if (existe == false)
			{
				try
				{
					supermercadoDAO = DAOFactory.getSupermercadoDAO();
					supermercadoDAO.inserir(supermercado);
					return msg.getMsg_supermercado_cadastrado_com_sucesso();
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
					e.printStackTrace();
					return e.getMessage();
				}
				catch (UsuarioExistenteException e)
				{
					// e.printStackTrace();
				}
			}
		}
		DAOFactory.close();
		return "";
	}

	/**
	 * Essse método altera um supermercado já cadastrado
	 * 
	 * @throws SupermercadoInexistenteException
	 **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/alterarSupermercado")
	public String alterarSupermercado(Supermercado supermercado)
	{
		DAOFactory.abrir();
		String resultado = rnSupermercado.validarCampos(supermercado);
		if (!resultado.equals("") || resultado.length() != 0)
		{
			boolean existe = rnSupermercado.verificarSupermercadoExistente(supermercado);
			if (existe == true)
			{
				try
				{
					supermercadoDAO = DAOFactory.getSupermercadoDAO();
					supermercadoDAO.alterar(supermercado);
					return msg.getMsg_cliente_alterado_com_sucesso();
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
					return e.getMessage();
				}
				catch (UsuarioInexistenteException e)
				{
					// e.printStackTrace();
				}
			}
		}
		DAOFactory.close();
		return "";
	}

	/**
	 * Excluindo um supermercado pelo código
	 * 
	 * @throws SupermercadoInexistenteException
	 */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/excluirSupermercado/{codigo}")
	public String excluirSupermercado(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		new DAOFactory();
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		try
		{
			Supermercado s = supermercadoDAO.consultarPorId(codigo);
			s.getUsuario().setStatus(Status.INATIVO);
			supermercadoDAO.alterar(s);
			return msg.getMsg_cliente_excluido_com_sucesso();
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
			return e.getMessage();
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
		DAOFactory.close();
		return "";
	}

	/**
	 * Esse método pesquisa um supermercado cadastrado pelo CNPJ
	 * 
	 * @throws SupermercadoInexistenteException
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarSupermercadoPorCnpj/{cnpj}")
	public Supermercado pesquisarSupermercadoPorCnpj(@PathParam("cnpj") String cnpj)
	{
		DAOFactory.abrir();
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		Supermercado s = null;
		try
		{
			s = supermercadoDAO.pesquisarSupermercadoPorCNPJ(cnpj);
		}
		catch (SupermercadoInexistenteException e)
		{
			e.printStackTrace();
		}
		if (s == null)
		{
			return null;
		}
		return s;
	}

	/**
	 * Esse método busca um supermercado cadastrado pelo código
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarSupermercadoPorId/{codigo}")
	public Supermercado pesquisarSupermercadoPorId(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		new DAOFactory();
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		Supermercado s;
		try
		{
			s = supermercadoDAO.consultarPorId(codigo);
			return s;
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
		DAOFactory.close();
		return null;
	}

	/**
	 * Esse método lista todos os supermercados cadastrados na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodosSupermercados")
	public List<Supermercado> consultarTodosSupermercados()
	{
		DAOFactory.abrir();
		new DAOFactory();
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		List<Supermercado> supermercados;
		try
		{
			supermercados = supermercadoDAO.consultarTodos();
			return supermercados;
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
		DAOFactory.close();
		return null;
	}
}