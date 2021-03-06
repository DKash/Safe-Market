/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.Cliente;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.IClienteDAO;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
public class RNCliente
{
	// Atributos
	private IClienteDAO clienteDAO;

	Mensagens msg = new Mensagens();

	// Métodos
	public String validarCampos(Cliente cliente)
	{
		List<String> campos = new ArrayList<>();
		if (cliente.getNome() == null || (cliente.getNome().equals(""))) campos.add(cliente.getNome());
		if (cliente.getCpf() == null || (cliente.getCpf().equals(""))) campos.add(cliente.getCpf());
		if (cliente.getCelular() == null || (cliente.getCelular().equals("")))
			campos.add(cliente.getCelular().toString());
		if (cliente.getUsuario().getCodigo() == 0) campos.add(String.valueOf(cliente.getUsuario().getCodigo()));
		if (cliente.getEndereco().getCodigo() == 0) campos.add(String.valueOf(cliente.getEndereco().getCodigo()));
		int tam = campos.size();
		String resultado = "";
		while (tam > 0)
		{
			resultado += " " + msg.getMsg_campo_invalido() + campos.get(tam);
			tam--;
		}
		return resultado;
	}

	public boolean verificarClienteExistente(Cliente cliente)
	{
		clienteDAO = DAOFactory.getClienteDAO();
		Cliente c = null;
		try
		{
			c = clienteDAO.pesquisarClientePorCPF(cliente.getCpf());
		}
		catch (ClienteInexistenteException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		if (c == null)
		{
			return false;
		} else
		{
			return true;
		}
	}

	public boolean verificarClienteExistente(int codigo)
	{
		clienteDAO = DAOFactory.getClienteDAO();
		Cliente c = null;
		try
		{
			c = clienteDAO.consultarPorId(codigo);
		}
		catch (ClienteInexistenteException | ProdutoInexistenteException | SupermercadoInexistenteException
				| UsuarioInexistenteException | CategoriaInexistenteException | MarcaInexistenteException
				| UnidadeMedidaInexistenteException | PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		if (c == null)
		{
			return false;
		} else
		{
			return true;
		}
	}
}