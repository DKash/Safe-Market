package br.com.safemarket.dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Status;
import br.com.safemarket.classesBasicas.Usuario;
import br.com.safemarket.dados.gererics.DAOGenerico;
import br.com.safemarket.interfaces.dao.IUsuarioDAO;

/**
 * @author Audry Martins
 *
 */
public class UsuarioDAO extends DAOGenerico<Usuario> implements IUsuarioDAO
{
	// Atributos
	private EntityManager manager;

	// Construtores
	public UsuarioDAO(EntityManager em)
	{
		super(em);
		this.setManager(em);
	}

	// Métodos
	public List<Usuario> consultarTodosAtivos()
	{
		TypedQuery<Usuario> query = this.entityManager.createNamedQuery("Usuario.findAllActives",
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

	public Usuario efetuarLogin(Usuario usuario)
	{
		TypedQuery<Usuario> query = this.entityManager.createNamedQuery("Usuario.signIn", this.classePersistente);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
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

	public Usuario pesquisarUsuarioPorEmail(String email)
	{
		TypedQuery<Usuario> query = this.entityManager.createNamedQuery("Usuario.findByEmail", this.classePersistente);
		query.setParameter("email", email);
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