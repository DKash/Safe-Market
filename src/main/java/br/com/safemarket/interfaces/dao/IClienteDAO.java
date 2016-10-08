package br.com.safemarket.interfaces.dao;

import br.com.safemarket.classesBasicas.Cliente;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.interfaces.dao.generics.IDAOGenerico;

/**
 * @author Audry Martins
 *
 */
public interface IClienteDAO extends IDAOGenerico<Cliente>
{
	// Métodos
	public Cliente pesquisarClientePorNome(String nome) throws ClienteInexistenteException;

	public Cliente pesquisarClientePorCPF(String cpf) throws ClienteInexistenteException;
}