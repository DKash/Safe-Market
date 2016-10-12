package br.com.safemarket.classesBasicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Audry Martins
 *
 */
@NamedQueries(
{ @NamedQuery(name = "Supermercado.findAllActives", query = "SELECT s FROM Supermercado s WHERE s.usuario.status =:status"),
		@NamedQuery(name = "Supermercado.findByName", query = "SELECT s FROM Supermercado s WHERE s.nome LIKE :nome"),
		@NamedQuery(name = "Supermercado.findByCNPJ", query = "SELECT s FROM Supermercado s WHERE s.cnpj = :cnpj") })
@Entity
public class Supermercado
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;

	@Column(length = 50, nullable = true)
	private String nome;

	@Column(length = 18, nullable = false, unique = true)
	private String cnpj;

	@Column(length = 14, nullable = false)
	private String inscricaoEstadual;

	@Column(length = 14, nullable = false)
	private String telefone;

	@OneToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@Fetch(FetchMode.JOIN)
	private Endereco endereco;

	@Column(length = 50, nullable = false)
	private String email;

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
		this.inscricaoEstadual = "";
		this.telefone = "";
		this.endereco = new Endereco();
		this.email = "";
		this.usuario = new Usuario();
	}

	/**
	 * @param codigo
	 * @param nome
	 * @param cnpj
	 * @param inscricaoEstadual
	 * @param telefone
	 * @param endereco
	 * @param email
	 * @param usuario
	 */
	public Supermercado(int codigo, String nome, String cnpj, String inscricaoEstadual, String telefone,
			Endereco endereco, String email, Usuario usuario)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
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
	public String getInscricaoEstadual()
	{
		return inscricaoEstadual;
	}

	/**
	 * @param inscricaoEstadual
	 *            the inscricaoEstatdual to set
	 */
	public void setInscricaoEstadual(String inscricaoEstadual)
	{
		this.inscricaoEstadual = inscricaoEstadual;
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