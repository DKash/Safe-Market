/**
 * 
 */
package br.com.safemarket.dados;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.UnidadeMedida;
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
	public UnidadeMedida pesquisarUnidadeMedidaPorNome(String nome)
	{
		String consulta = "SELECT um FROM UnidadeMedida um WHERE um.nome = :N";
		TypedQuery<UnidadeMedida> retorno = getEntityManager().createQuery(consulta, UnidadeMedida.class);
		retorno.setParameter("N", nome);
		UnidadeMedida resultado;
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