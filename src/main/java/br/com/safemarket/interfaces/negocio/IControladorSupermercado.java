/**
 * 
 */
package br.com.safemarket.interfaces.negocio;

import java.util.List;

import br.com.safemarket.classesBasicas.Supermercado;
import br.com.safemarket.exceptions.SupermercadoExistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IControladorSupermercado
{
	// Métodos
	public String cadastrarSupermercado(Supermercado Supermercado) throws SupermercadoExistenteException;

	public String alterarSupermercado(Supermercado Supermercado) throws SupermercadoInexistenteException;

	public String excluirSupermercado(int codigo) throws SupermercadoInexistenteException;

	public Supermercado pesquisarSupermercadoPorCnpj(String cnpj) throws SupermercadoInexistenteException;

	public Supermercado pesquisarSupermercadoPorId(int codigo) throws SupermercadoInexistenteException;

	public List<Supermercado> consultarTodosSupermercados() throws SupermercadoInexistenteException;
}