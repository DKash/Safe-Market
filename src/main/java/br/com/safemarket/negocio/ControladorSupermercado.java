/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
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

import br.com.safemarket.classesBasicas.Endereco;
import br.com.safemarket.classesBasicas.Status;
import br.com.safemarket.classesBasicas.Supermercado;
import br.com.safemarket.classesBasicas.Usuario;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.CategoriaExistenteException;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteExistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.EnderecoInexistenteException;
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
import br.com.safemarket.interfaces.dao.ISupermercadoDAO;
import br.com.safemarket.interfaces.negocio.IControladorSupermercado;
import br.com.safemarket.negocio.regras.RNEndereco;
import br.com.safemarket.negocio.regras.RNSupermercado;
import br.com.safemarket.negocio.regras.RNUsuario;
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

	private RNEndereco rnEndereco = new RNEndereco();

	private RNUsuario rnUsuario = new RNUsuario();

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
		boolean existe = false;
		String mensagem = "";
		String resultado = rnSupermercado.validarCampos(supermercado);
		if (resultado.equals(""))
		{
			supermercadoDAO = DAOFactory.getSupermercadoDAO();
			existe = rnSupermercado.verificarSupermercadoExistente(supermercado);
			if (existe == false)
			{
				try
				{
					Endereco endereco = rnEndereco.verificarEnderecoExistente(supermercado.getEndereco().getCodigo());
					Usuario usuario = rnUsuario.verificarUsuarioExistente(supermercado.getUsuario().getCodigo());
					if (endereco != null)
					{
						supermercado.setEndereco(endereco);
						if (usuario != null)
						{
							supermercado.setUsuario(usuario);
							supermercadoDAO.inserir(supermercado);
							mensagem = msg.getMsg_supermercado_cadastrado_com_sucesso();
						} else
						{
							mensagem = new UsuarioInexistenteException().getMessage();
						}
					} else
					{
						mensagem = new EnderecoInexistenteException().getMessage();
					}
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
					mensagem = e.getMessage();
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
					// e.printStackTrace();
				}
			}
		}
		DAOFactory.close();
		return mensagem;
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
		boolean existe = false;
		String mensagem = "";
		String resultado = rnSupermercado.validarCampos(supermercado);
		if (resultado.equals(""))
		{
			existe = rnSupermercado.verificarSupermercadoExistentePorId(supermercado.getCodigo());
			if (existe == true)
			{
				try
				{
					supermercadoDAO = DAOFactory.getSupermercadoDAO();
					Endereco endereco = rnEndereco.verificarEnderecoExistente(supermercado.getEndereco().getCodigo());
					Usuario usuario = rnUsuario.verificarUsuarioExistente(supermercado.getUsuario().getCodigo());
					if (endereco != null)
					{
						supermercado.setEndereco(endereco);
						if (usuario != null)
						{
							supermercado.setUsuario(usuario);
							supermercadoDAO.alterar(supermercado);
							mensagem = msg.getMsg_supermercado_alterado_com_sucesso();
						} else
						{
							mensagem = new UsuarioInexistenteException().getMessage();
						}
					} else
					{
						mensagem = new EnderecoInexistenteException().getMessage();
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
					e.printStackTrace();
					mensagem = e.getMessage();
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
			}
		}
		DAOFactory.close();
		return mensagem;
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
		String mensagem = "";
		try
		{
			supermercadoDAO = DAOFactory.getSupermercadoDAO();
			Supermercado s = supermercadoDAO.consultarPorId(codigo);
			if (s != null)
			{
				if (s.getUsuario().getStatus() == Status.ATIVO)
				{
					s.getUsuario().setStatus(Status.INATIVO);
					supermercadoDAO.alterar(s);
					mensagem = msg.getMsg_supermercado_excluido_com_sucesso();
				} else
				{
					mensagem = msg.getMsg_supermercado_excluido_com_sucesso();
				}
			} else
			{
				mensagem = new SupermercadoInexistenteException().getMessage();
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
			e.printStackTrace();
			mensagem = e.getMessage();
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
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Esse método pesquisa um supermercado cadastrado pelo CNPJ
	 * 
	 * @throws SupermercadoInexistenteException
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarSupermercadoPorCNPJ/{cnpj}")
	public Supermercado pesquisarSupermercadoPorCNPJ(@PathParam("cnpj") String cnpj)
	{
		DAOFactory.abrir();
		Supermercado s = null;
		try
		{
			supermercadoDAO = DAOFactory.getSupermercadoDAO();
			cnpj = "1234567890/0001-10";
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
		Supermercado s = null;
		try
		{
			supermercadoDAO = DAOFactory.getSupermercadoDAO();
			s = supermercadoDAO.consultarPorId(codigo);
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
			return null;
		}
		return s;
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
		List<Supermercado> lista = new ArrayList<>();
		try
		{
			supermercadoDAO = DAOFactory.getSupermercadoDAO();
			lista = supermercadoDAO.consultarTodos();
		}
		catch (ClienteInexistenteException e)
		{
			e.printStackTrace();
		}
		catch (ProdutoInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (SupermercadoInexistenteException e)
		{
			e.printStackTrace();
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
		DAOFactory.close();
		if (!lista.isEmpty())
		{
			return lista;
		}
		return null;
	}

	/**
	 * Esse método lista todos os supermercados cadastrados na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodosSupermercadosAtivos")
	public List<Supermercado> consultarTodosSupermercadosAtivos()
	{
		DAOFactory.abrir();
		List<Supermercado> lista = new ArrayList<>();
		try
		{
			supermercadoDAO = DAOFactory.getSupermercadoDAO();
			lista = supermercadoDAO.consultarTodosAtivos();
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
		DAOFactory.close();
		if (!lista.isEmpty())
		{
			return lista;
		}
		return null;
	}
}