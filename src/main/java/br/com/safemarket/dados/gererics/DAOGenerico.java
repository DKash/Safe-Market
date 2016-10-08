package br.com.safemarket.dados.gererics;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.safemarket.interfaces.dao.generics.IDAOGenerico;

/**
 * @author Audry Martins
 *
 */
public abstract class DAOGenerico<Entidade> implements IDAOGenerico<Entidade>
{
	// Atributos
	protected EntityManager entityManager;

	protected Class<Entidade> classePersistente;

	// Construtores
	@SuppressWarnings("unchecked")
	public DAOGenerico(EntityManager em)
	{
		this.entityManager = em;
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		classePersistente = (Class<Entidade>) parameterizedType.getActualTypeArguments()[0];
	}

	// Métodos
	/**
	 * Salva o objeto atual na base de dados.
	 * 
	 * @param objeto
	 *            a ser salvo
	 */
	public final void inserir(Entidade objeto)
	{
		EntityTransaction tx = getEntityManager().getTransaction();
		try
		{
			tx.begin();
			getEntityManager().persist(objeto);
			tx.commit();
			System.out.println(classePersistente.getSimpleName() + " salvo com sucesso");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null && tx.isActive())
			{
				tx.rollback();
			}
		}
	}

	/**
	 * Executa o merge do objeto que se encontra em mem�ria.
	 * 
	 * @param objeto
	 *            a ser realizado o merge
	 * @return objeto que foi executado o merge
	 */
	public final void alterar(Entidade objeto)
	{
		EntityTransaction tx = getEntityManager().getTransaction();
		try
		{
			tx.begin();
			objeto = getEntityManager().merge(objeto);
			tx.commit();
			System.out.println(classePersistente.getSimpleName() + " alterado com sucesso");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null && tx.isActive())
			{
				tx.rollback();
			}
		}
	}

	public List<Entidade> consultarTodos()
	{
		try
		{
			String sql = "from " + classePersistente.getSimpleName();
			TypedQuery<Entidade> query = entityManager.createQuery(sql, classePersistente);
			return query.getResultList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<Entidade> consultarTodos(Integer indiceInicial, Integer quantidade)
	{
		try
		{
			String sql = "from " + classePersistente.getSimpleName();
			TypedQuery<Entidade> query = entityManager.createQuery(sql, classePersistente);
			query = query.setFirstResult(indiceInicial).setMaxResults(quantidade);
			return query.getResultList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Busca o objeto uma vez passado sua chave como par�metro.
	 * 
	 * @param chave
	 *            identificador
	 * @return Objeto do tipo T
	 */
	public final Entidade consultarPorId(Integer chave)
	{
		Entidade instance = null;
		try
		{
			instance = (Entidade) getEntityManager().find(classePersistente, chave);
		}
		catch (RuntimeException re)
		{
			re.printStackTrace();
		}
		return instance;
	}

	// Gets e Sets
	/**
	 * Utilizado para se injetar o Entity manager no DAO.
	 * 
	 * @param entityManager
	 *            entity manager
	 */
	public void setEntityManager(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager()
	{
		return entityManager;
	}
}