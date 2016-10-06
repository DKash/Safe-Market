package br.com.safemarket.interfaces.dao;

import br.com.safemarket.classesBasicas.Cliente;
import br.com.safemarket.exceptions.ClienteInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IClienteDAO extends IDAOGenerico<Cliente>
{
	// Métodos
	public Cliente pesquisarClientePorCPF(String cpf) throws ClienteInexistenteException;
}