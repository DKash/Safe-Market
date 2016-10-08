package br.com.safemarket.classesBasicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Audry Martins
 *
 */
@NamedQueries(
{ @NamedQuery(name = "Usuario.findAllActives", query = "SELECT u FROM Usuario u WHERE u.status =:status"),
		@NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email =:email"),
		@NamedQuery(name = "Usuario.signIn", query = "SELECT u FROM Usuario u WHERE u.email =:email AND u.senha = :senha") })
@Entity
public class Usuario
{
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 5)
	private Integer codigo;

	@Column(length = 40, nullable = false)
	private String email;

	@Column(length = 20, nullable = false)
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private Status status;

	@OneToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private Perfil perfil;

	// Construtores
	public Usuario()
	{
		this.email = "";
		this.senha = "";
		this.status = Status.ATIVO;
		this.perfil = new Perfil();
	}

	/**
	 * @param codigo
	 * @param email
	 * @param senha
	 * @param status
	 * @param perfil
	 */
	public Usuario(Integer codigo, String email, String senha, Status status, Perfil perfil)
	{
		super();
		this.codigo = codigo;
		this.email = email;
		this.senha = senha;
		this.status = status;
		this.perfil = perfil;
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
	 * @return the senha
	 */
	public String getSenha()
	{
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha)
	{
		this.senha = senha;
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

	/**
	 * @return the perfil
	 */
	public Perfil getPerfil()
	{
		return perfil;
	}

	/**
	 * @param perfil
	 *            the perfil to set
	 */
	public void setPerfil(Perfil perfil)
	{
		this.perfil = perfil;
	}
}