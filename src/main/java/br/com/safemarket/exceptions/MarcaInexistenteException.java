/**
 * 
 */
package br.com.safemarket.exceptions;

/**
 * @author Audry Martins
 *
 */
public class MarcaInexistenteException extends Exception
{
	// Atributos
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		return "Marca inexistente.";
	}
}