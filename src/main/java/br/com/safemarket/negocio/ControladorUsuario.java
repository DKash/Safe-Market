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

import br.com.safemarket.classesBasicas.Perfil;
import br.com.safemarket.classesBasicas.Status;
import br.com.safemarket.classesBasicas.Usuario;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.CategoriaExistenteException;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteExistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.LoginInvalidoException;
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
import br.com.safemarket.interfaces.dao.IUsuarioDAO;
import br.com.safemarket.interfaces.negocio.IControladorUsuario;
import br.com.safemarket.negocio.regras.RNPerfil;
import br.com.safemarket.negocio.regras.RNUsuario;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
@Path("/service")
public class ControladorUsuario implements IControladorUsuario
{
	// Atributos
	private IUsuarioDAO usuarioDAO;

	RNUsuario rnUsuario = new RNUsuario();

	RNPerfil rnPerfil = new RNPerfil();

	private Mensagens msg = new Mensagens();

	// Métodos
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 *           Esse método cadastra um novo Usuário
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrarUsuario")
	public String cadastrarUsuario(Usuario usuario)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnUsuario.validarCampos(usuario);
		if (resultado.equals(""))
		{
			existe = rnUsuario.verificarUsuarioExistente(usuario);
			if (existe == false)
			{
				usuarioDAO = DAOFactory.getUsuarioDAO();
				Perfil perfil = rnPerfil.verificarPerfilExistentePorId(usuario.getPerfil().getCodigo());
				if (perfil != null)
				{
					try
					{
						usuario.setPerfil(perfil);
						usuarioDAO.inserir(usuario);
						mensagem = msg.getMsg_usuario_cadastrado_com_sucesso();
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
						e.printStackTrace();
						mensagem = e.getMessage();
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
				} else
				{
					mensagem = new PerfilInexistenteException().getMessage();
				}
			}
		}
		DAOFactory.close();
		return mensagem;
	}

	/**
	 * Essse método altera um Usuário já cadastrado
	 **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/alterarUsuario")
	public String alterarUsuario(Usuario usuario)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnUsuario.validarCampos(usuario);
		if (resultado.equals(""))
		{
			existe = rnUsuario.verificarUsuarioExistente(usuario);
			if (existe == true)
			{
				try
				{
					usuarioDAO = DAOFactory.getUsuarioDAO();
					usuarioDAO.alterar(usuario);
					mensagem = msg.getMsg_usuario_alterado_com_sucesso();
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
					e.printStackTrace();
					mensagem = e.getMessage();
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
	 * Esse método lista todos os Usuários cadastrados na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodosUsuarios")
	public List<Usuario> consultarTodosUsuarios()
	{
		DAOFactory.abrir();
		List<Usuario> lista = new ArrayList<>();
		try
		{
			usuarioDAO = DAOFactory.getUsuarioDAO();
			lista = usuarioDAO.consultarTodos();
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
			e.printStackTrace();
			e.getMessage();
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
	 * Esse método lista todos os Usuários cadastrados na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodosUsuariosAtivos")
	public List<Usuario> consultarTodosUsuariosAtivos()
	{
		DAOFactory.abrir();
		List<Usuario> lista = new ArrayList<>();
		try
		{
			usuarioDAO = DAOFactory.getUsuarioDAO();
			lista = usuarioDAO.consultarTodosAtivos();
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
			e.printStackTrace();
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
	 * Excluindo um Usuário pelo código
	 */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/excluirUsuario/{codigo}")
	public String excluirUsuario(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		String mensagem = "";
		Usuario user = new Usuario();
		try
		{
			usuarioDAO = DAOFactory.getUsuarioDAO();
			user = usuarioDAO.consultarPorId(codigo);
			if (user != null)
			{
				if (user.getStatus() == Status.ATIVO)
				{
					user.setStatus(Status.INATIVO);
					usuarioDAO.alterar(user);
					mensagem = msg.getMsg_usuario_excluido_com_sucesso();
				} else
				{
					mensagem = msg.getMsg_usuario_excluido_com_sucesso();
				}
			} else
			{
				mensagem = new UsuarioInexistenteException().getMessage();
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
			e.printStackTrace();
			return e.getMessage();
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
	 * Esse método efetua o Login do Usuário cadastrado na base
	 */
	@POST
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/efetuarLogin")
	@Override
	public Usuario efetuarLogin(Usuario usuario)
	{
		DAOFactory.abrir();
		Usuario u = null;
		try
		{
			usuarioDAO = DAOFactory.getUsuarioDAO();
			u = usuarioDAO.efetuarLogin(usuario);
		}
		catch (LoginInvalidoException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		DAOFactory.close();
		if (u == null)
		{
			return null;
		}
		return u;
	}
}