package br.com.safemarket.dados;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Usuario;
import br.com.safemarket.interfaces.dao.IUsuarioDAO;

/**
 * @author Audry Martins
 *
 */
public class UsuarioDAO extends DAOGenerico<Usuario> implements IUsuarioDAO
{
	private EntityManager manager;

	public UsuarioDAO(EntityManager em)
	{
		super(em);
		this.setManager(em);
	}

	public Usuario efetuarLogin(Usuario usuario)
	{
		EntityTransaction tx = getEntityManager().getTransaction();
		try
		{
			String sql = "SELECT u FROM Usuario WHERE u.email = :email and u.senha = :senha";
			TypedQuery<Usuario> queryUsuario = this.entityManager.createQuery(sql, Usuario.class);
			queryUsuario.setParameter("email", usuario.getEmail());
			queryUsuario.setParameter("senha", usuario.getSenha());
			usuario = queryUsuario.getSingleResult();
			return usuario;
		}
		catch (Exception e)
		{
			if (tx != null && tx.isActive())
			{
				tx.rollback();
			}
		}
		return null;
	}

	public Usuario pesquisarUsuarioPorEmail(String email)
	{
		String consulta = "SELECT u FROM Usuario u WHERE u.email = :email";
		TypedQuery<Usuario> retorno = getEntityManager().createQuery(consulta, Usuario.class);
		retorno.setParameter("email", email);
		Usuario resultado;
		try
		{
			resultado = retorno.getSingleResult();
			return resultado;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public EntityManager getManager()
	{
		return manager;
	}

	public void setManager(EntityManager manager)
	{
		this.manager = manager;
	}
}