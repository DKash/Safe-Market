package br.com.safemarket.dados;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Cliente;
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
	public Cliente pesquisarClientePorCPF(String cpf)
	{
		String consulta = "SELECT c FROM Cliente c WHERE c.cpf = :N";
		TypedQuery<Cliente> retorno = getEntityManager().createQuery(consulta, Cliente.class);
		retorno.setParameter("N", cpf);
		Cliente resultado;
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