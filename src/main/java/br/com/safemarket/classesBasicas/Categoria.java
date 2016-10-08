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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Audry Martins
 *
 */
@NamedQueries(
{ @NamedQuery(name = "Categoria.findAllActives", query = "SELECT c FROM Categoria c WHERE c.status =:status"),
		@NamedQuery(name = "Categoria.findByName", query = "SELECT c FROM Categoria c WHERE c.nome LIKE :nome") })
@Entity
public class Categoria
{
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigo;

	@Column(length = 25, nullable = false)
	private String nome;

	@Column(length = 20, nullable = false)
	private String subcategoria;

	@Enumerated(EnumType.STRING)
	@Column(length = 8, nullable = false)
	private Status status;

	// Construtores
	public Categoria()
	{
		this.nome = "";
		this.subcategoria = "";
		this.status = Status.ATIVO;
	}

	/**
	 * @param codigo
	 * @param nome
	 * @param subcategoria
	 * @param status
	 */
	public Categoria(Integer codigo, String nome, String subcategoria, Status status)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.subcategoria = subcategoria;
		this.status = status;
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

	/**
	 * @return the subcategoria
	 */
	public String getSubcategoria()
	{
		return subcategoria;
	}

	/**
	 * @param subcategoria
	 *            the subcategoria to set
	 */
	public void setSubcategoria(String subcategoria)
	{
		this.subcategoria = subcategoria;
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