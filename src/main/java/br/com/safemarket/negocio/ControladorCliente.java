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

import br.com.safemarket.classesBasicas.Cliente;
import br.com.safemarket.classesBasicas.Endereco;
import br.com.safemarket.classesBasicas.Status;
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
import br.com.safemarket.interfaces.dao.IClienteDAO;
import br.com.safemarket.interfaces.negocio.IControladorCliente;
import br.com.safemarket.negocio.regras.RNCliente;
import br.com.safemarket.negocio.regras.RNEndereco;
import br.com.safemarket.negocio.regras.RNUsuario;
import br.com.safemarket.util.Mensagens;

@Path("/service")
public class ControladorCliente implements IControladorCliente
{
	private IClienteDAO clienteDAO;

	private RNCliente rnCliente = new RNCliente();

	private RNEndereco rnEndereco = new RNEndereco();

	private RNUsuario rnUsuario = new RNUsuario();

	Mensagens msg = new Mensagens();

	/**
	 * @throws ClienteInexistenteException
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 *           Esse método cadastra um novo cliente
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrarCliente")
	public String cadastrarCliente(Cliente cliente)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnCliente.validarCampos(cliente);
		if (resultado.equals(""))
		{
			existe = rnCliente.verificarClienteExistente(cliente);
			if (existe == false)
			{
				try
				{
					clienteDAO = DAOFactory.getClienteDAO();
					Endereco endereco = rnEndereco.verificarEnderecoExistente(cliente.getEndereco().getCodigo());
					Usuario usuario = rnUsuario.verificarUsuarioExistente(cliente.getUsuario().getCodigo());
					if (endereco != null)
					{
						cliente.setEndereco(endereco);
						if (usuario != null)
						{
							cliente.setUsuario(usuario);
							clienteDAO.inserir(cliente);
							mensagem = msg.getMsg_cliente_cadastrado_com_sucesso();
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
					e.printStackTrace();
					mensagem = e.getMessage();
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
					// e.printStackTrace();
				}
			}
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Essse método altera um cliente já cadastrado
	 **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/alterarCliente")
	public String alterarCliente(Cliente cliente)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnCliente.validarCampos(cliente);
		if (resultado.equals(""))
		{
			existe = rnCliente.verificarClienteExistente(cliente.getCodigo());
			if (existe == true)
			{
				try
				{
					clienteDAO = DAOFactory.getClienteDAO();
					Endereco endereco = rnEndereco.verificarEnderecoExistente(cliente.getEndereco().getCodigo());
					Usuario usuario = rnUsuario.verificarUsuarioExistente(cliente.getUsuario().getCodigo());
					if (endereco != null)
					{
						cliente.setEndereco(endereco);
						if (usuario != null)
						{
							cliente.setUsuario(usuario);
							clienteDAO.alterar(cliente);
							mensagem = msg.getMsg_cliente_alterado_com_sucesso();
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
					e.printStackTrace();
					mensagem = e.getMessage();
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
					// e.printStackTrace();
				}
			}
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Excluindo um cliente pelo código
	 */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/excluirCliente/{codigo}")
	@Override
	public String excluirCliente(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		String mensagem = "";
		try
		{
			clienteDAO = DAOFactory.getClienteDAO();
			Cliente c = clienteDAO.consultarPorId(codigo);
			if (c != null)
			{
				if (c.getUsuario().getStatus() == Status.ATIVO)
				{
					c.getUsuario().setStatus(Status.INATIVO);
					clienteDAO.alterar(c);
					mensagem = msg.getMsg_cliente_excluido_com_sucesso();
				} else
				{
					mensagem = msg.getMsg_cliente_excluido_com_sucesso();
				}
			} else
			{
				mensagem = new ClienteInexistenteException().getMessage();
			}
		}
		catch (ClienteInexistenteException e)
		{
			e.printStackTrace();
			mensagem = e.getMessage();
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
			// e.printStackTrace();
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Esse método lista todos os clientes cadastrados na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodosClientes")
	public List<Cliente> consultarTodosClientes()
	{
		DAOFactory.abrir();
		clienteDAO = DAOFactory.getClienteDAO();
		List<Cliente> lista = new ArrayList<>();
		try
		{
			lista = clienteDAO.consultarTodos();
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
	 * Esse método lista todos os clientes cadastrados na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodosClientesAtivos")
	public List<Cliente> consultarTodosClientesAtivos()
	{
		DAOFactory.abrir();
		List<Cliente> lista = new ArrayList<>();
		try
		{
			clienteDAO = DAOFactory.getClienteDAO();
			lista = clienteDAO.consultarTodosAtivos();
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
	 * Esse método pesquisa o cliente cadastrado na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarClientePorCPF/{cpf}")
	public Cliente pesquisarCliente(@PathParam("cpf") String cpf)
	{
		DAOFactory.abrir();
		Cliente c = null;
		try
		{
			clienteDAO = DAOFactory.getClienteDAO();
			c = clienteDAO.pesquisarClientePorCPF(cpf);
		}
		catch (ClienteInexistenteException e)
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
	 * Esse método pesquisa o cliente cadastrado na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarClientePorId/{codigo}")
	@Override
	public Cliente pesquisarClientePorId(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		Cliente c = null;
		try
		{
			clienteDAO = DAOFactory.getClienteDAO();
			c = clienteDAO.consultarPorId(codigo);
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