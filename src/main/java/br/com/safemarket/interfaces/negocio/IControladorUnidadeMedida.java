package br.com.safemarket.interfaces.negocio;

import java.util.List;

import br.com.safemarket.classesBasicas.UnidadeMedida;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaExistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IControladorUnidadeMedida
{
	// Métodos
	public String cadastrarUnidadeMedida(UnidadeMedida unidadeMedida) throws UnidadeMedidaExistenteException;

	public String alterarUnidadeMedida(UnidadeMedida unidadeMedida) throws UnidadeMedidaInexistenteException;

	public String excluirUnidadeMedida(int codigo) throws MarcaInexistenteException;

	public List<UnidadeMedida> consultarTodasUnidadeMedidas() throws MarcaInexistenteException;

	public UnidadeMedida pesquisarUnidadeMedidaPorNome(String nome) throws MarcaInexistenteException;

	public UnidadeMedida pesquisarUnidadeMedidaPorId(int codigo) throws MarcaInexistenteException;
}