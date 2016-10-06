/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.Cliente;
import br.com.safemarket.dados.DAOFactory;
import br.com.safemarket.exceptions.ClienteInexistenteException;
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
		if (cliente.getTelefone() == null || (cliente.getTelefone().equals(""))) campos.add(cliente.getTelefone());
		if (cliente.getCelular() == null || (cliente.getCelular().equals("")))
			campos.add(cliente.getCelular().toString());
		if (cliente.getUsuario().getEmail() == null || (cliente.getUsuario().getEmail().equals("")))
			campos.add(cliente.getUsuario().getEmail());
		if (cliente.getUsuario().getSenha() == null || (cliente.getUsuario().getSenha().equals("")))
			campos.add(cliente.getUsuario().getSenha().toString());
		int tam = campos.size();
		String resultado = "";
		do
		{
			resultado += " " + msg.getMsg_campo_invalido() + campos.get(tam);
			tam--;
		}
		while (tam >= 0);
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
}