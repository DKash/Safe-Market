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
{ @NamedQuery(name = "Cliente.findAllActives", query = "SELECT c FROM Cliente c WHERE c.usuario.status =:status"),
		@NamedQuery(name = "Cliente.findByName", query = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome"),
		@NamedQuery(name = "Cliente.findByCPF", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf") })
@Entity
public class Cliente
{
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;

	@Column(length = 80, nullable = false)
	private String nome;

	@Column(length = 14, nullable = false, unique = true)
	private String cpf;

	@Column(length = 14, nullable = true)
	private String telefone;

	@Column(length = 16, nullable = false)
	private String celular;

	@OneToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@Fetch(FetchMode.JOIN)
	private Usuario usuario;

	@OneToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@Fetch(FetchMode.JOIN)
	private Endereco endereco;

	// Construtores
	public Cliente()
	{
		this.nome = "";
		this.cpf = "";
		this.telefone = "";
		this.celular = "";
		this.usuario = new Usuario();
		this.endereco = new Endereco();
	}

	public Cliente(int codigo, String nome, String cpf, String telefone, String celular, Usuario usuario,
			Endereco endereco)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.celular = celular;
		this.usuario = usuario;
		this.endereco = endereco;
	}

	// Gets e Sets
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

	public String getCpf()
	{
		return cpf;
	}

	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}

	public String getTelefone()
	{
		return telefone;
	}

	public void setTelefone(String telefone)
	{
		this.telefone = telefone;
	}

	public String getCelular()
	{
		return celular;
	}

	public void setCelular(String celular)
	{
		this.celular = celular;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public Endereco getEndereco()
	{
		return endereco;
	}

	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}
}