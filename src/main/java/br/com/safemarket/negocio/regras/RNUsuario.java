/**
 * 
 */
package br.com.safemarket.negocio.regras;

import java.util.ArrayList;
import java.util.List;

import br.com.safemarket.classesBasicas.Usuario;
import br.com.safemarket.dados.gererics.DAOFactory;
import br.com.safemarket.exceptions.UsuarioInexistenteException;
import br.com.safemarket.interfaces.dao.IUsuarioDAO;
import br.com.safemarket.util.Mensagens;

/**
 * @author Audry Martins
 *
 */
public class RNUsuario
{
	// Atributos
	private IUsuarioDAO usuarioDAO;

	Mensagens msg = new Mensagens();

	// Métodos
	public String validarCampos(Usuario usuario)
	{
		List<String> campos = new ArrayList<>();
		if (usuario.getEmail() == null || (usuario.getEmail().equals(""))) campos.add(usuario.getEmail());
		if (usuario.getSenha() == null || (usuario.getSenha().equals(""))) campos.add(usuario.getSenha());
		if ((usuario.getPerfil().getCodigo() == 0)) campos.add(usuario.getPerfil().getCodigo().toString());
		int tam = campos.size();
		String resultado = "";
		while (tam > 0)
		{
			resultado += " " + msg.getMsg_campo_invalido() + campos.get(tam);
			tam--;
		}
		return resultado;
	}

	// Métodos
	public boolean verificarUsuarioExistente(Usuario usuario)
	{
		usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario u = null;
		try
		{
			u = usuarioDAO.pesquisarUsuarioPorEmail(usuario.getEmail());
		}
		catch (UsuarioInexistenteException e)
		{
			// e.printStackTrace();
		}
		if (u == null)
		{
			return false;
		} else
		{
			return true;
		}
	}
}