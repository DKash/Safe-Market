package br.com.safemarket.dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Cliente;
import br.com.safemarket.classesBasicas.Status;
import br.com.safemarket.dados.gererics.DAOGenerico;
import br.com.safemarket.interfaces.dao.IClienteDAO;

/**
 * @author Audry Martins
 *
 */
public class ClienteDAO extends DAOGenerico<Cliente> implements IClienteDAO
{
	// Atributos
	private EntityManager manager;

	// Construtores
	public ClienteDAO(EntityManager em)
	{
		super(em);
		this.setManager(em);
	}

	// Métodos
	public Cliente pesquisarClientePorNome(String nome)
	{
		TypedQuery<Cliente> query = this.entityManager.createNamedQuery("Cliente.findByName", this.classePersistente);
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

	public List<Cliente> consultarTodosAtivos()
	{
		TypedQuery<Cliente> query = this.entityManager.createNamedQuery("Cliente.findAllActives",
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

	public Cliente pesquisarClientePorCPF(String cpf)
	{
		TypedQuery<Cliente> query = this.entityManager.createNamedQuery("Cliente.findByCPF", this.classePersistente);
		query.setParameter("cpf", cpf);
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