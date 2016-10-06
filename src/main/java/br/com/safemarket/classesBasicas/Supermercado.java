package br.com.safemarket.classesBasicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Supermercado
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigo;

	@Column(length = 50, nullable = true)
	private String nome;

	@Column(length = 20, nullable = false, unique = true)
	private String cnpj;

	@Column(length = 50, nullable = false)
	private String inscricaoEstatdual;

	@Column(length = 14, nullable = false)
	private String telefone;

	@OneToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Endereco endereco;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 5, nullable = true)
	private int estoque;

	@OneToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@Fetch(FetchMode.JOIN)
	private Usuario usuario;

	// Construtores
	public Supermercado()
	{
		super();
		this.nome = "";
		this.cnpj = "";
		this.inscricaoEstatdual = "";
		this.telefone = "";
		this.endereco = new Endereco();
		this.email = "";
		this.usuario = new Usuario();
	}

	/**
	 * @param codigo
	 * @param nome
	 * @param cnpj
	 * @param inscricaoEstatdual
	 * @param telefone
	 * @param endereco
	 * @param email
	 * @param estoque
	 * @param usuario
	 */
	public Supermercado(int codigo, String nome, String cnpj, String inscricaoEstatdual, String telefone,
			Endereco endereco, String email, int estoque, Usuario usuario)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cnpj = cnpj;
		this.inscricaoEstatdual = inscricaoEstatdual;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.estoque = estoque;
		this.usuario = usuario;
	}

	// Gets e Sets
	/**
	 * @return the codigo
	 */
	public int getCodigo()
	{
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo)
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
	 * @return the cnpj
	 */
	public String getCnpj()
	{
		return cnpj;
	}

	/**
	 * @param cnpj
	 *            the cnpj to set
	 */
	public void setCnpj(String cnpj)
	{
		this.cnpj = cnpj;
	}

	/**
	 * @return the inscricaoEstatdual
	 */
	public String getInscricaoEstatdual()
	{
		return inscricaoEstatdual;
	}

	/**
	 * @param inscricaoEstatdual
	 *            the inscricaoEstatdual to set
	 */
	public void setInscricaoEstatdual(String inscricaoEstatdual)
	{
		this.inscricaoEstatdual = inscricaoEstatdual;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone()
	{
		return telefone;
	}

	/**
	 * @param telefone
	 *            the telefone to set
	 */
	public void setTelefone(String telefone)
	{
		this.telefone = telefone;
	}

	/**
	 * @return the endereco
	 */
	public Endereco getEndereco()
	{
		return endereco;
	}

	/**
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the estoque
	 */
	public int getEstoque()
	{
		return estoque;
	}

	/**
	 * @param estoque
	 *            the estoque to set
	 */
	public void setEstoque(int estoque)
	{
		this.estoque = estoque;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario()
	{
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}
}