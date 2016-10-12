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
import br.com.safemarket.classesBasicas.Marca;
import br.com.safemarket.classesBasicas.Produto;
import br.com.safemarket.classesBasicas.Status;
import br.com.safemarket.classesBasicas.Supermercado;
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
import br.com.safemarket.interfaces.dao.IProdutoDAO;
import br.com.safemarket.interfaces.negocio.IControladorProduto;
import br.com.safemarket.negocio.regras.RNCategoria;
import br.com.safemarket.negocio.regras.RNMarca;
import br.com.safemarket.negocio.regras.RNProduto;
import br.com.safemarket.negocio.regras.RNSupermercado;
import br.com.safemarket.negocio.regras.RNUnidadeMedida;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
@Path("/service")
public class ControladorProduto implements IControladorProduto
{
	// Atributos
	private IProdutoDAO produtoDAO;

	RNProduto rnProduto = new RNProduto();

	RNCategoria rnCategoria = new RNCategoria();

	RNMarca rnMarca = new RNMarca();

	RNUnidadeMedida rnUnidadeMedida = new RNUnidadeMedida();

	RNSupermercado rnSupermercado = new RNSupermercado();

	Mensagens msg = new Mensagens();

	// Métodos
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 *           Esse método cadastra um novo Produto
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrarProduto")
	public String cadastrarProduto(Produto produto)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnProduto.validarCampos(produto);
		if (resultado.equals(""))
		{
			existe = rnProduto.verificarProdutoExistente(produto);
			if (existe == false)
			{
				try
				{
					produtoDAO = DAOFactory.getProdutoDAO();
					Categoria categoria = rnCategoria.verificarCategoriaExistente(produto.getCategoria().getCodigo());
					Marca marca = rnMarca.verificarMarcaExistentePorId(produto.getMarca().getCodigo());
					UnidadeMedida um = rnUnidadeMedida
							.verificarUnidadeMedidaExistente(produto.getUnidadeMedida().getCodigo());
					Supermercado supermercado = rnSupermercado
							.verificarSupermercadoExistente(produto.getSupermercado().getCodigo());
					if (categoria != null)
					{
						produto.setCategoria(categoria);
						if (marca != null)
						{
							produto.setMarca(marca);
						} else
						{
							mensagem = new MarcaInexistenteException().getMessage();
						}
						if (um != null)
						{
							produto.setUnidadeMedida(um);
						} else
						{
							mensagem = new UnidadeMedidaInexistenteException().getMessage();
						}
						if (supermercado != null)
						{
							produto.setSupermercado(supermercado);
							produtoDAO.inserir(produto);
							mensagem = msg.getMsg_produto_cadastrado_com_sucesso();
						} else
						{
							mensagem = new SupermercadoInexistenteException().getMessage();
						}
					} else
					{
						mensagem = new CategoriaInexistenteException().getMessage();
					}
				}
				catch (ClienteExistenteException e)
				{
					// e.printStackTrace();
				}
				catch (ProdutoExistenteException e)
				{
					e.printStackTrace();
					mensagem = e.getMessage();
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
	 * Essse método altera um produto já cadastrado
	 **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/alterarProduto")
	public String alterarProduto(Produto produto)
	{
		DAOFactory.abrir();
		boolean existe = false;
		String mensagem = "";
		String resultado = rnProduto.validarCampos(produto);
		if (resultado.equals(""))
		{
			existe = rnProduto.verificarProdutoExistente(produto.getCodigo());
			if (existe == true)
			{
				try
				{
					produtoDAO = DAOFactory.getProdutoDAO();
					Categoria categoria = rnCategoria.verificarCategoriaExistente(produto.getCategoria().getCodigo());
					Marca marca = rnMarca.verificarMarcaExistentePorId(produto.getMarca().getCodigo());
					UnidadeMedida um = rnUnidadeMedida
							.verificarUnidadeMedidaExistente(produto.getUnidadeMedida().getCodigo());
					Supermercado supermercado = rnSupermercado
							.verificarSupermercadoExistente(produto.getSupermercado().getCodigo());
					if (categoria != null)
					{
						produto.setCategoria(categoria);
						if (marca != null)
						{
							produto.setMarca(marca);
						} else
						{
							mensagem = new MarcaInexistenteException().getMessage();
						}
						if (um != null)
						{
							produto.setUnidadeMedida(um);
						} else
						{
							mensagem = new UnidadeMedidaInexistenteException().getMessage();
						}
						if (supermercado != null)
						{
							produto.setSupermercado(supermercado);
							produtoDAO.alterar(produto);
							mensagem = msg.getMsg_produto_alterado_com_sucesso();
						} else
						{
							mensagem = new SupermercadoInexistenteException().getMessage();
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
					e.printStackTrace();
					mensagem = e.getMessage();
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
	 * Excluindo um produto pelo código
	 */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/excluirProduto/{codigo}")
	public String excluirProduto(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		String mensagem = "";
		try
		{
			produtoDAO = DAOFactory.getProdutoDAO();
			Produto p = produtoDAO.consultarPorId(codigo);
			if (p != null)
			{
				if (p.getStatus() == Status.ATIVO || p.getStatus() == Status.DISPONIVEL)
				{
					if (p.getEstoque() != 0)
					{
						p.setStatus(Status.INATIVO);
					} else
					{
						p.setStatus(Status.INDISPONIVEL);
					}
					produtoDAO.alterar(p);
					mensagem = msg.getMsg_produto_excluido_com_sucesso();
				} else
				{
					mensagem = msg.getMsg_produto_excluido_com_sucesso();
				}
			} else
			{
				mensagem = new ProdutoInexistenteException().getMessage();
			}
		}
		catch (ClienteInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (ProdutoInexistenteException e)
		{
			e.printStackTrace();
			mensagem = e.getMessage();
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
	@Path("/consultarTodosProdutos")
	public List<Produto> consultarTodosProdutos()
	{
		DAOFactory.abrir();
		List<Produto> lista = new ArrayList<>();
		try
		{
			produtoDAO = DAOFactory.getProdutoDAO();
			lista = produtoDAO.consultarTodos();
		}
		catch (ClienteInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (ProdutoInexistenteException e)
		{
			e.printStackTrace();
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
	 * Esse método lista todos os produtos ativos cadastrados na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/consultarTodosProdutosAtivos")
	public List<Produto> consultarTodosProdutosAtivos()
	{
		DAOFactory.abrir();
		List<Produto> lista = new ArrayList<>();
		try
		{
			produtoDAO = DAOFactory.getProdutoDAO();
			lista = produtoDAO.consultarTodosAtivos();
		}
		catch (ClienteInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (ProdutoInexistenteException e)
		{
			e.printStackTrace();
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
	 * Esse método pesquisa o produto cadastrado na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarProdutoPorId/{codigo}")
	@Override
	public Produto pesquisarProdutoPorId(@PathParam("codigo") int codigo)
	{
		DAOFactory.abrir();
		Produto p = null;
		try
		{
			produtoDAO = DAOFactory.getProdutoDAO();
			p = produtoDAO.consultarPorId(codigo);
		}
		catch (ClienteInexistenteException e)
		{
			// e.printStackTrace();
		}
		catch (ProdutoInexistenteException e)
		{
			e.printStackTrace();
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
		if (p == null)
		{
			return null;
		}
		return p;
	}

	/**
	 * Esse método pesquisa o produto cadastrado na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarProdutoPorNome/{nome}")
	public Produto pesquisarProdutoPorNome(@PathParam("nome") String nome)
	{
		DAOFactory.abrir();
		Produto p = null;
		try
		{
			produtoDAO = DAOFactory.getProdutoDAO();
			p = produtoDAO.pesquisarProdutoPorNome(nome);
		}
		catch (ProdutoInexistenteException e)
		{
			e.printStackTrace();
		}
		DAOFactory.close();
		if (p == null)
		{
			return null;
		}
		return p;
	}

	/**
	 * Esse método pesquisa o produto cadastrado na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarProdutoPorMarca/{marca}")
	public List<Produto> pesquisarProdutoPorMarca(@PathParam("marca") String marca)
	{
		DAOFactory.abrir();
		List<Produto> lista = new ArrayList<>();
		try
		{
			produtoDAO = DAOFactory.getProdutoDAO();
			lista = produtoDAO.pesquisarProdutosPorMarca(marca);
		}
		catch (ProdutoInexistenteException e)
		{
			e.printStackTrace();
		}
		DAOFactory.close();
		if (!lista.isEmpty())
		{
			return lista;
		}
		return null;
	}

	/**
	 * Esse método pesquisa o produto cadastrado na base
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/pesquisarProdutoPorPreco/{preco}")
	public List<Produto> pesquisarProdutoPorPreco(@PathParam("preco") double preco)
	{
		DAOFactory.abrir();
		List<Produto> lista = new ArrayList<>();
		try
		{
			produtoDAO = DAOFactory.getProdutoDAO();
			lista = produtoDAO.pesquisarProdutosPorPreco(preco);
		}
		catch (ProdutoInexistenteException e)
		{
			e.printStackTrace();
		}
		DAOFactory.close();
		if (!lista.isEmpty())
		{
			return lista;
		}
		return null;
	}
}