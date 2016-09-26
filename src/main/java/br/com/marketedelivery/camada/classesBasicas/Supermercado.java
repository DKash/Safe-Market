package br.com.marketedelivery.camada.classesBasicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Supermercado
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigo;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 20, nullable = false, unique = true)
	private String cnpj;

	@Column(length = 50, nullable = false)
	private String inscricaoEstatdual;

	@Column(length = 11, nullable = false)
	private String telefone;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 5, nullable = true)
	private int estoque;

	@OneToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Usuario usuario;

	public Supermercado()
	{
		super();
		this.nome = "";
		this.cnpj = "";
		this.inscricaoEstatdual = "";
		this.telefone = "";
		this.email = "";
		this.usuario = new Usuario();
	}

	/**
	 * @param codigo
	 * @param nome
	 * @param cnpj
	 * @param inscricaoEstatdual
	 * @param telefone
	 * @param email
	 * @param estoque
	 */
	public Supermercado(int codigo, String nome, String cnpj, String inscricaoEstatdual, String telefone, String email,
			int estoque, Usuario usuario)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cnpj = cnpj;
		this.inscricaoEstatdual = inscricaoEstatdual;
		this.telefone = telefone;
		this.email = email;
		this.estoque = estoque;
		this.usuario = usuario;
	}

	public int getCodigo()
	{
		return codigo;
	}

	public void setCodigo(int codigo)
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

	public String getCnpj()
	{
		return cnpj;
	}

	public void setCnpj(String cnpj)
	{
		this.cnpj = cnpj;
	}

	public String getInscricaoEstatdual()
	{
		return inscricaoEstatdual;
	}

	public void setInscricaoEstatdual(String inscricaoEstatdual)
	{
		this.inscricaoEstatdual = inscricaoEstatdual;
	}

	public String getTelefone()
	{
		return telefone;
	}

	public void setTelefone(String telefone)
	{
		this.telefone = telefone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public int getEstoque()
	{
		return estoque;
	}

	public void setEstoque(int estoque)
	{
		this.estoque = estoque;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}
}