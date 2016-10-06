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
import br.com.safemarket.classesBasicas.Usuario;
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
import br.com.safemarket.interfaces.dao.IUsuarioDAO;
import br.com.safemarket.interfaces.negocio.IControladorUsuario;
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
		try
		{
			try
			{
				// Falta validar os campos
				existe = rnUsuario.verificarUsuarioExistente(usuario);
			}
			catch (UsuarioInexistenteException e)
			{
				e.printStackTrace();
				e.getMessage();
			}
			if (existe == false)
			{
				usuarioDAO = DAOFactory.getUsuarioDAO();
				usuarioDAO.inserir(usuario);
			}
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
			return e.getMessage();
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
		try
		{
			try
			{
				existe = rnUsuario.verificarUsuarioExistente(usuario);
			}
			catch (UsuarioInexistenteException e)
			{
				e.printStackTrace();
				e.getMessage();
			}
			if (existe == true)
			{
				usuarioDAO = DAOFactory.getUsuarioDAO();
				usuarioDAO.alterar(usuario);
				return msg.getMsg_usuario_alterado_com_sucesso();
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
		DAOFactory.close();
		return "";
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
		usuarioDAO = DAOFactory.getUsuarioDAO();
		List<Usuario> usuarios = new ArrayList<>();
		try
		{
			usuarios = usuarioDAO.consultarTodos();
			return usuarios;
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
		DAOFactory.close();
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
		usuarioDAO = DAOFactory.getUsuarioDAO();
		try
		{
			Usuario user = usuarioDAO.consultarPorId(codigo);
			user.setStatus(Status.INATIVO);
			usuarioDAO.alterar(user);
			return msg.getMsg_usuario_excluido_com_sucesso();
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
		DAOFactory.close();
		return "";
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
		usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario u;
		try
		{
			u = usuarioDAO.efetuarLogin(usuario);
			return u;
		}
		catch (UsuarioInexistenteException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		DAOFactory.close();
		return null;
	}
}