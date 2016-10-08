package br.com.safemarket.interfaces.dao.generics;

import java.util.List;

import br.com.safemarket.exceptions.CategoriaExistenteException;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteExistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaExistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.PerfilExistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.exceptions.ProdutoExistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoExistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaExistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioExistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;

/**
 * @author Audry Martins
 *
 */
public interface IDAOGenerico<Entidade>
{
	// Métodos
	public void inserir(Entidade entidade) throws ClienteExistenteException, ProdutoExistenteException,
			SupermercadoExistenteException, UsuarioExistenteException, CategoriaExistenteException,
			MarcaExistenteException, UnidadeMedidaExistenteException, PerfilExistenteException;

	public void alterar(Entidade entidade) throws ClienteInexistenteException, ProdutoInexistenteException,
			SupermercadoInexistenteException, UsuarioInexistenteException, CategoriaInexistenteException,
			MarcaInexistenteException, UnidadeMedidaInexistenteException, PerfilInexistenteException;

	public Entidade consultarPorId(Integer id) throws ClienteInexistenteException, ProdutoInexistenteException,
			SupermercadoInexistenteException, UsuarioInexistenteException, CategoriaInexistenteException,
			MarcaInexistenteException, UnidadeMedidaInexistenteException, PerfilInexistenteException;

	public List<Entidade> consultarTodos() throws ClienteInexistenteException, ProdutoInexistenteException,
			SupermercadoInexistenteException, UsuarioInexistenteException, CategoriaInexistenteException,
			MarcaInexistenteException, UnidadeMedidaInexistenteException, PerfilInexistenteException;

	public List<Entidade> consultarTodosAtivos() throws ClienteInexistenteException, ProdutoInexistenteException,
			SupermercadoInexistenteException, UsuarioInexistenteException, CategoriaInexistenteException,
			MarcaInexistenteException, UnidadeMedidaInexistenteException, PerfilInexistenteException;
}