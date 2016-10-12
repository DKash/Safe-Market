/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.Endereco;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.CategoriaInexistenteException;
import br.com.safemarket.exceptions.ClienteInexistenteException;
import br.com.safemarket.exceptions.MarcaInexistenteException;
import br.com.safemarket.exceptions.PerfilInexistenteException;
import br.com.safemarket.exceptions.ProdutoInexistenteException;
import br.com.safemarket.exceptions.SupermercadoInexistenteException;
import br.com.safemarket.exceptions.UnidadeMedidaInexistenteException;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.IEnderecoDAO;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
public class RNEndereco
{
	// Atributos
	private IEnderecoDAO enderecoDAO;

	Mensagens msg = new Mensagens();

	// Métodos
	public String validarCampos(Endereco endereco)
	{
		List<String> campos = new ArrayList<>();
		if (endereco.getLogradouro() == null || (endereco.getLogradouro().equals("")))
			campos.add(endereco.getLogradouro());
		if (endereco.getNumero() == 0) campos.add(String.valueOf(endereco.getNumero()));
		if (endereco.getBairro() == null || (endereco.getBairro().equals(""))) campos.add(endereco.getBairro());
		if (endereco.getCidade() == null || (endereco.getCidade().equals("")))
			campos.add(endereco.getCidade().toString());
		if (endereco.getCep() == null || (endereco.getCep().equals(""))) campos.add(endereco.getCep());
		int tam = campos.size();
		String resultado = "";
		while (tam >= 0)
		{
			resultado += " " + msg.getMsg_campo_invalido() + campos.get(tam);
			tam--;
		}
		return resultado;
	}

	public Endereco verificarEnderecoExistente(int codigo)
	{
		enderecoDAO = DAOFactory.getEnderecoDAO();
		Endereco end = null;
		try
		{
			end = enderecoDAO.consultarPorId(codigo);
		}
		catch (ClienteInexistenteException | ProdutoInexistenteException | SupermercadoInexistenteException
				| UsuarioInexistenteException | CategoriaInexistenteException | MarcaInexistenteException
				| UnidadeMedidaInexistenteException | PerfilInexistenteException e)
		{
			// e.printStackTrace();
		}
		if (end == null)
		{
			return null;
		}
		return end;
	}
}