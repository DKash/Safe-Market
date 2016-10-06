/**
 * 
 */
package br.com.safemarket.classesBasicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Audry Martins
 *
 */
@Entity
public class Marca
{
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigo;

	@Column(length = 20, nullable = false)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private Status status;

	// Construtores
	public Marca()
	{
		super();
		this.nome = "";
		this.status = Status.ATIVO;
	}

	/**
	 * @param codigo
	 * @param nome
	 */
	public Marca(Integer codigo, String nome, Status status)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.status = status;
	}
	// Gets e Sets

	public Integer getCodigo()
	{
		return codigo;
	}

	public void setCodigo(Integer codigo)
	{
		this.codigo = codigo;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * @return the status
	 */
	public Status getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status)
	{
		this.status = status;
	}
}