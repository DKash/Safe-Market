/**
 * 
 */
package br.com.safemarket.exceptions;

/**
 * @author Audry Martins
 *
 */
public class CategoriaExistenteException extends Exception
{
	// Atributos
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Métodos
	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		return "Categoria já existe";
	}
}