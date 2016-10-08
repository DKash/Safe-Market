/**
 * 
 */
package br.com.safemarket.dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Categoria;
import br.com.safemarket.classesBasicas.Status;
import br.com.safemarket.dados.gererics.DAOGenerico;
import br.com.safemarket.interfaces.dao.ICategoriaDAO;

/**
 * @author Audry Martins
 *
 */
public class CategoriaDAO extends DAOGenerico<Categoria> implements ICategoriaDAO
{
	// Atributos
	private EntityManager manager;

	// Construtores
	public CategoriaDAO(EntityManager em)
	{
		super(em);
		this.setManager(em);
	}

	// Métodos
	public Categoria pesquisarCategoriaPorNome(String nome)
	{
		TypedQuery<Categoria> query = this.entityManager.createNamedQuery("Categoria.findByName",
				this.classePersistente);
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

	public List<Categoria> consultarTodosAtivos()
	{
		TypedQuery<Categoria> query = this.entityManager.createNamedQuery("Categoria.findAllActives",
				this.classePersistente);
		query.setParameter("status", Status.ATIVO);
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