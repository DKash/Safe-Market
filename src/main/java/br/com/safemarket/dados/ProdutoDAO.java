package br.com.safemarket.dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Produto;
import br.com.safemarket.classesBasicas.Status;
import br.com.safemarket.dados.gererics.DAOGenerico;
import br.com.safemarket.interfaces.dao.IProdutoDAO;

/**
 * @author Audry Martins
 *
 */
public class ProdutoDAO extends DAOGenerico<Produto> implements IProdutoDAO
{
	// Atributos
	private EntityManager manager;

	// Construtores
	public ProdutoDAO(EntityManager em)
	{
		super(em);
		this.setManager(em);
	}

	// Métodos
	public Produto pesquisarProdutoPorNome(String nome)
	{
		TypedQuery<Produto> query = this.entityManager.createNamedQuery("Produto.findByName", this.classePersistente);
		query.setParameter("nome", nome);
		try
		{
			return query.setMaxResults(1).getSingleResult();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<Produto> pesquisarProdutosPorMarca(String marca)
	{
		TypedQuery<Produto> query = this.entityManager.createNamedQuery("Produto.findByMark", this.classePersistente);
		query.setParameter("marca", marca);
		try
		{
			return query.getResultList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<Produto> pesquisarProdutosPorPreco(double preco)
	{
		TypedQuery<Produto> query = this.entityManager.createNamedQuery("Produto.findByPrice", this.classePersistente);
		query.setParameter("preco", preco);
		try
		{
			return query.getResultList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<Produto> consultarTodosAtivos()
	{
		TypedQuery<Produto> query = this.entityManager.createNamedQuery("Produto.findAllActives",
				this.classePersistente);
		query.setParameter("status", Status.DISPONIVEL);
		try
		{
			return query.getResultList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// Gets e Sets
	public EntityManager getManager()
	{
		return manager;
	}

	public void setManager(EntityManager manager)
	{
		this.manager = manager;
	}
}