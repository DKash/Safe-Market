/**
 * 
 */
package br.com.safemarket.dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Perfil;
import br.com.safemarket.dados.gererics.DAOGenerico;
import br.com.safemarket.interfaces.dao.IPerfilDAO;

/**
 * @author Audry Martins
 *
 */
public class PerfilDAO extends DAOGenerico<Perfil> implements IPerfilDAO
{
	// Atributos
	private EntityManager manager;

	// Construtores
	public PerfilDAO(EntityManager em)
	{
		super(em);
		this.setManager(em);
	}

	// Métodos
	public Perfil pesquisarPerfilPorNome(String nome)
	{
		TypedQuery<Perfil> query = this.entityManager.createNamedQuery("Perfil.findByName", this.classePersistente);
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

	public List<Perfil> consultarTodosAtivos()
	{
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