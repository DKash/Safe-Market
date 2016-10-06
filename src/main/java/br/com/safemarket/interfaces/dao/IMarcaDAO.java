/**
 * 
 */
package br.com.safemarket.interfaces.dao;

import br.com.safemarket.classesBasicas.Marca;
import br.com.safemarket.exceptions.MarcaInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IMarcaDAO extends IDAOGenerico<Marca>
{
	// Métodos
	public Marca pesquisarMarcaPorNome(String nome) throws MarcaInexistenteException;
}