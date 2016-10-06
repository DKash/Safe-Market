/**
 * 
 */
package br.com.safemarket.exceptions;

/**
 * @author Audry Martins
 *
 */
public class ClienteInexistenteException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage()
	{
		return "Cliente inexistente";
	}
}