package br.com.safemarket.classesBasicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigo;

	@Column(length = 30, nullable = false)
	private String logradouro;

	@Column(length = 5, nullable = false)
	private int numero;

	@Column(length = 30, nullable = true)
	private String complemento;

	@Column(length = 30, nullable = false)
	private String bairro;

	@Column(length = 30, nullable = false)
	private String cidade;

	@Enumerated(EnumType.STRING)
	@Column(length = 2, nullable = false)
	private Estado estado;

	@Column(length = 9, nullable = false)
	private String cep;

	// Construtores
	public Endereco()
	{
		this.logradouro = "";
		this.complemento = "";
		this.bairro = "";
		this.cidade = "";
		this.estado = null;
		this.cep = "";
	}

	/**
	 * @param codigo
	 * @param logradouro
	 * @param numero
	 * @param complemento
	 * @param bairro
	 * @param cidade
	 * @param estado
	 * @param cep
	 * @param cliente
	 */
	public Endereco(Integer codigo, String logradouro, int numero, String complemento, String bairro, String cidade,
			Estado estado, String cep)
	{
		super();
		this.codigo = codigo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
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
	 * @return the logradouro
	 */
	public String getLogradouro()
	{
		return logradouro;
	}

	/**
	 * @param logradouro
	 *            the logradouro to set
	 */
	public void setLogradouro(String logradouro)
	{
		this.logradouro = logradouro;
	}

	/**
	 * @return the numero
	 */
	public int getNumero()
	{
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(int numero)
	{
		this.numero = numero;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento()
	{
		return complemento;
	}

	/**
	 * @param complemento
	 *            the complemento to set
	 */
	public void setComplemento(String complemento)
	{
		this.complemento = complemento;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro()
	{
		return bairro;
	}

	/**
	 * @param bairro
	 *            the bairro to set
	 */
	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade()
	{
		return cidade;
	}

	/**
	 * @param cidade
	 *            the cidade to set
	 */
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado()
	{
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}

	/**
	 * @return the cep
	 */
	public String getCep()
	{
		return cep;
	}

	/**
	 * @param cep
	 *            the cep to set
	 */
	public void setCep(String cep)
	{
		this.cep = cep;
	}
}