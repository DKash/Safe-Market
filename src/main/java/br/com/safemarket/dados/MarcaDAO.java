/**
 * 
 */
package br.com.safemarket.dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Marca;
import br.com.safemarket.classesBasicas.Status;
import br.com.safemarket.dados.gererics.DAOGenerico;
import br.com.safemarket.interfaces.dao.IMarcaDAO;

/**
 * @author Audry Martins
 *
 */
public class MarcaDAO extends DAOGenerico<Marca> implements IMarcaDAO
{
	// Atributos
	private EntityManager manager;

	// Construtores
	public MarcaDAO(EntityManager em)
	{
		super(em);
		this.setManager(em);
	}

	// Métodos
	public List<Marca> consultarTodosAtivos()
	{
		TypedQuery<Marca> query = this.entityManager.createNamedQuery("Marca.findAllActives", this.classePersistente);
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

	public Marca pesquisarMarcaPorNome(String nome)
	{
		TypedQuery<Marca> query = this.entityManager.createNamedQuery("Marca.findByName", this.classePersistente);
		query.setParameter("nome", nome);
		try
		{
			return query.getSingleResult();
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