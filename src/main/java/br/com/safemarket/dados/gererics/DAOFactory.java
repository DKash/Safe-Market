/**
 * 
 */
package br.com.safemarket.dados.gererics;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.safemarket.dados.CategoriaDAO;
import br.com.safemarket.dados.ClienteDAO;
import br.com.safemarket.dados.EnderecoDAO;
import br.com.safemarket.dados.MarcaDAO;
import br.com.safemarket.dados.PerfilDAO;
import br.com.safemarket.dados.ProdutoDAO;
import br.com.safemarket.dados.SupermercadoDAO;
import br.com.safemarket.dados.UnidadeMedidaDAO;
import br.com.safemarket.dados.UsuarioDAO;
import br.com.safemarket.interfaces.dao.ICategoriaDAO;
import br.com.safemarket.interfaces.dao.IClienteDAO;
import br.com.safemarket.interfaces.dao.IEnderecoDAO;
import br.com.safemarket.interfaces.dao.IMarcaDAO;
import br.com.safemarket.interfaces.dao.IPerfilDAO;
import br.com.safemarket.interfaces.dao.IProdutoDAO;
import br.com.safemarket.interfaces.dao.ISupermercadoDAO;
import br.com.safemarket.interfaces.dao.IUnidadeMedidaDAO;
import br.com.safemarket.interfaces.dao.IUsuarioDAO;

/**
 * @author Audry Martins
 *
 */
public class DAOFactory
{
	// Atributos
	private static final String unit_name = "safe-market-db";

	private static EntityManagerFactory factory;

	public static IClienteDAO clienteDAO;

	public static IEnderecoDAO enderecoDAO;

	public static ICategoriaDAO categoriaDAO;

	public static IMarcaDAO marcaDAO;

	public static IUnidadeMedidaDAO unidadeMedidaDAO;

	public static IProdutoDAO produtoDAO;

	public static IPerfilDAO perfilDAO;

	public static ISupermercadoDAO supermercadoDAO;

	public static IUsuarioDAO usuarioDAO;
	// Construtores
	static
	{
		factory = Persistence.createEntityManagerFactory(unit_name);
	}

	// Métodos
	public static void abrir()
	{
		if (factory == null || !(factory.isOpen()))
		{
			factory = Persistence.createEntityManagerFactory(unit_name);
		}
	}

	public static void close()
	{
		if (factory != null && factory.isOpen())
		{
			factory.close();
		}
	}

	// Gets e Sets
	public static IClienteDAO getClienteDAO()
	{
		clienteDAO = new ClienteDAO(factory.createEntityManager());
		return clienteDAO;
	}

	public static IEnderecoDAO getEnderecoDAO()
	{
		enderecoDAO = new EnderecoDAO(factory.createEntityManager());
		return enderecoDAO;
	}

	public static ICategoriaDAO getCategoriaDAO()
	{
		categoriaDAO = new CategoriaDAO(factory.createEntityManager());
		return categoriaDAO;
	}

	public static IMarcaDAO getMarcaDAO()
	{
		marcaDAO = new MarcaDAO(factory.createEntityManager());
		return marcaDAO;
	}

	public static IUnidadeMedidaDAO getUnidadeMedidaDAO()
	{
		unidadeMedidaDAO = new UnidadeMedidaDAO(factory.createEntityManager());
		return unidadeMedidaDAO;
	}

	public static IProdutoDAO getProdutoDAO()
	{
		produtoDAO = new ProdutoDAO(factory.createEntityManager());
		return produtoDAO;
	}

	public static IPerfilDAO getPerfilDAO()
	{
		perfilDAO = new PerfilDAO(factory.createEntityManager());
		return perfilDAO;
	}

	public static IUsuarioDAO getUsuarioDAO()
	{
		usuarioDAO = new UsuarioDAO(factory.createEntityManager());
		return usuarioDAO;
	}

	public static ISupermercadoDAO getSupermercadoDAO()
	{
		supermercadoDAO = new SupermercadoDAO(factory.createEntityManager());
		return supermercadoDAO;
	}
}