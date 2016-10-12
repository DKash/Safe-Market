package br.com.safemarket.interfaces.negocio;

import java.util.List;

import br.com.safemarket.classesBasicas.Categoria;
import br.com.safemarket.exceptions.CategoriaExistenteException;
import br.com.safemarket.exceptions.CategoriaInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IControladorCategoria
{
	// Métodos
	public String cadastrarCategoria(Categoria categoria) throws CategoriaExistenteException;

	public String alterarCategoria(Categoria categoria) throws CategoriaInexistenteException;

	public String excluirCategoria(int codigo) throws CategoriaInexistenteException;

	public List<Categoria> consultarTodasCategorias() throws CategoriaInexistenteException;

	public List<Categoria> consultarTodasCategoriasAtivas() throws CategoriaInexistenteException;

	public Categoria pesquisarCategoriaPorNome(String nome) throws CategoriaInexistenteException;

	public Categoria pesquisarCategoriaPorId(int codigo) throws CategoriaInexistenteException;
}