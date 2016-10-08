/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package br.com.safemarket.dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.safemarket.classesBasicas.Status;
import br.com.safemarket.classesBasicas.Supermercado;
import br.com.safemarket.dados.gererics.DAOGenerico;
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
	public List<Supermercado> consultarTodosAtivos()
	{
		TypedQuery<Supermercado> query = this.entityManager.createNamedQuery("Supermercado.findAllActives",
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

	public Supermercado pesquisarSupermercadoPorCNPJ(String cnpj)
	{
		TypedQuery<Supermercado> query = this.entityManager.createNamedQuery("Supermercado.findByCNPJ",
				this.classePersistente);
		query.setParameter("cnpj", cnpj);
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

	public Supermercado pesquisarSupermercadoPorNome(String nome)
	{
		TypedQuery<Supermercado> query = this.entityManager.createNamedQuery("Supermercado.findByName",
				this.classePersistente);
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