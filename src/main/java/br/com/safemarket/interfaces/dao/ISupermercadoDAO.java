package br.com.safemarket.interfaces.dao;

import br.com.safemarket.classesBasicas.Supermercado;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.interfaces.dao.generics.IDAOGenerico;

/**
 * @author Audry Martins
 *
 */
public interface ISupermercadoDAO extends IDAOGenerico<Supermercado>
{
	// Métodos
	public Supermercado pesquisarSupermercadoPorNome(String nome) throws SupermercadoInexistenteException;

	public Supermercado pesquisarSupermercadoPorCNPJ(String cnpj) throws SupermercadoInexistenteException;
}