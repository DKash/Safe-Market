package br.com.safemarket.interfaces.negocio;

import java.util.List;

import br.com.safemarket.classesBasicas.Marca;
import br.com.safemarket.exceptions.MarcaExistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IControladorMarca
{
	// Métodos
	public String cadastrarMarca(Marca marca) throws MarcaExistenteException;

	public String alterarMarca(Marca marca) throws MarcaInexistenteException;

	public String excluirMarca(int codigo) throws MarcaInexistenteException;

	public List<Marca> consultarTodasMarcas() throws MarcaInexistenteException;

	public List<Marca> consultarTodasMarcasAtivas() throws MarcaInexistenteException;

	public Marca pesquisarMarcaPorNome(String nome) throws MarcaInexistenteException;

	public Marca pesquisarMarcaPorId(int codigo) throws MarcaInexistenteException;
}