/**
 * 
 */
package br.com.safemarket.dados;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Marca;
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
	public Marca pesquisarMarcaPorNome(String nome)
	{
		String consulta = "SELECT m FROM Marca m WHERE m.nome = :N";
		TypedQuery<Marca> retorno = getEntityManager().createQuery(consulta, Marca.class);
		retorno.setParameter("N", nome);
		Marca resultado;
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