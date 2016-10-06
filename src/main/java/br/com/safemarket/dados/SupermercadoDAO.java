/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package br.com.safemarket.dados;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Supermercado;
import br.com.safemarket.interfaces.dao.ISupermercadoDAO;

/**
 * @author Audry Martins
 *
 */
public class SupermercadoDAO extends DAOGenerico<Supermercado> implements ISupermercadoDAO
{
	// Atributos
	private EntityManager manager;

	// Construtores
	public SupermercadoDAO(EntityManager em)
	{
		super(em);
		this.setManager(em);
	}

	// Métodos
	public Supermercado pesquisarSupermercadoPorCNPJ(String cnpj)
	{
		String consulta = "SELECT c FROM Supermercado c WHERE c.cnpj = :N";
		TypedQuery<Supermercado> retorno = getEntityManager().createQuery(consulta, Supermercado.class);
		retorno.setParameter("N", cnpj);
		Supermercado resultado;
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

	public Supermercado pesquisarSupermercadoPorNome(String nome)
	{
		String comandoSelect = "SELECT s FROM Supermercado s WHERE s.nome = :N ";
		TypedQuery<Supermercado> retorno = getEntityManager().createQuery(comandoSelect, Supermercado.class);
		retorno.setParameter("N", "%" + nome + "%");
		Supermercado resultado;
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