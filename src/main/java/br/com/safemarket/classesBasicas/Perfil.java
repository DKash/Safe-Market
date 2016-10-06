package br.com.safemarket.classesBasicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Audry Martins
 *
 */
@Entity
public class Perfil
{
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigo;

	@Column(length = 25, nullable = false)
	private String nome;

	// Construtores
	public Perfil()
	{
		this.nome = "";
	}

	/**
	 * @param codigo
	 * @param nome
	 */
	public Perfil(Integer codigo, String nome)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
	}

	// Gets e Sets
	/**
	 * @return the codigo
	 */
	public Integer getCodigo()
	{
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Integer codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}
}