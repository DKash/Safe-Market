/**
 * 
 */
package br.com.safemarket.dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Status;
import br.com.safemarket.classesBasicas.UnidadeMedida;
import br.com.safemarket.dados.gererics.DAOGenerico;
import br.com.safemarket.interfaces.dao.IUnidadeMedidaDAO;

/**
 * @author Audry Martins
 *
 */
public class UnidadeMedidaDAO extends DAOGenerico<UnidadeMedida> implements IUnidadeMedidaDAO
{
	// Atributos
	private EntityManager manager;

	// Construtores
	public UnidadeMedidaDAO(EntityManager em)
	{
		super(em);
		this.setManager(em);
	}

	// Métodos
	public List<UnidadeMedida> consultarTodosAtivos()
	{
		TypedQuery<UnidadeMedida> query = this.entityManager.createNamedQuery("UnidadeMedida.findAllActives",
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

	public UnidadeMedida pesquisarUnidadeMedidaPorNome(String nome)
	{
		TypedQuery<UnidadeMedida> query = this.entityManager.createNamedQuery("UnidadeMedida.findByName",
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