package br.com.safemarket.dados;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.safemarket.classesBasicas.Endereco;
import br.com.safemarket.dados.gererics.DAOGenerico;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.IEnderecoDAO;

/**
 * @author Audry Martins
 *
 */
public class EnderecoDAO extends DAOGenerico<Endereco> implements IEnderecoDAO
{
	// Atributos
	private EntityManager manager;

	// Construtores
	public EnderecoDAO(EntityManager em)
	{
		super(em);
		this.setManager(em);
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

	@Override
	public List<Endereco> consultarTodosAtivos() throws ClienteInexistenteException, ProdutoInexistenteException,
			SupermercadoInexistenteException, UsuarioInexistenteException, CategoriaInexistenteException,
			MarcaInexistenteException, UnidadeMedidaInexistenteException, PerfilInexistenteException
	{
		return null;
	}
}