package br.com.safemarket.classesBasicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Audry Martins
 *
 */
@NamedQueries(
{ @NamedQuery(name = "Produto.findAllActives", query = "SELECT p FROM Produto p WHERE p.status =:status"),
		@NamedQuery(name = "Produto.findByName", query = "SELECT p FROM Produto p WHERE p.nome LIKE :nome"),
		@NamedQuery(name = "Produto.findByMark", query = "SELECT p FROM Produto p WHERE p.marca.nome LIKE :marca"),
		@NamedQuery(name = "Produto.findByPrice", query = "SELECT p FROM Produto p WHERE p.preco =:preco")})
@Entity
public class Produto
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigo;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 150, nullable = false)
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@Fetch(FetchMode.JOIN)
	private Marca marca;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@Fetch(FetchMode.JOIN)
	private UnidadeMedida unidadeMedida;

	@Column(length = 50, nullable = false)
	private int peso;

	@Column(length = 50, nullable = false)
	private int estoque;

	@Column(length = 20, nullable = false)
	private String dataValidade;

	@Column(length = 7, nullable = true)
	private double preco;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@Fetch(FetchMode.JOIN)
	private Categoria categoria;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@Fetch(FetchMode.JOIN)
	private Supermercado supermercado;

	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private Status status;

	// Construtores
	public Produto()
	{
		super();
	}

	/**
	 * @param codigo
	 * @param nome
	 * @param descricao
	 * @param marca
	 * @param unidadeMedida
	 * @param peso
	 * @param estoque
	 * @param dataValidade
	 * @param preco
	 * @param categoria
	 * @param supermercado
	 * @param status
	 */
	public Produto(Integer codigo, String nome, String descricao, Marca marca, UnidadeMedida unidadeMedida, int peso,
			int estoque, String dataValidade, double preco, Categoria categoria, Supermercado supermercado,
			Status status)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.marca = marca;
		this.unidadeMedida = unidadeMedida;
		this.peso = peso;
		this.estoque = estoque;
		this.dataValidade = dataValidade;
		this.preco = preco;
		this.categoria = categoria;
		this.supermercado = supermercado;
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
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	/**
	 * @return the marca
	 */
	public Marca getMarca()
	{
		return marca;
	}

	/**
	 * @param marca
	 *            the marca to set
	 */
	public void setMarca(Marca marca)
	{
		this.marca = marca;
	}

	/**
	 * @return the unidadeMedida
	 */
	public UnidadeMedida getUnidadeMedida()
	{
		return unidadeMedida;
	}

	/**
	 * @param unidadeMedida
	 *            the unidadeMedida to set
	 */
	public void setUnidadeMedida(UnidadeMedida unidadeMedida)
	{
		this.unidadeMedida = unidadeMedida;
	}

	/**
	 * @return the peso
	 */
	public int getPeso()
	{
		return peso;
	}

	/**
	 * @param peso
	 *            the peso to set
	 */
	public void setPeso(int peso)
	{
		this.peso = peso;
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
	 * @return the dataValidade
	 */
	public String getDataValidade()
	{
		return dataValidade;
	}

	/**
	 * @param dataValidade
	 *            the dataValidade to set
	 */
	public void setDataValidade(String dataValidade)
	{
		this.dataValidade = dataValidade;
	}

	/**
	 * @return the preco
	 */
	public double getPreco()
	{
		return preco;
	}

	/**
	 * @param preco
	 *            the preco to set
	 */
	public void setPreco(double preco)
	{
		this.preco = preco;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria()
	{
		return categoria;
	}

	/**
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(Categoria categoria)
	{
		this.categoria = categoria;
	}

	/**
	 * @return the supermercado
	 */
	public Supermercado getSupermercado()
	{
		return supermercado;
	}

	/**
	 * @param supermercado
	 *            the supermercado to set
	 */
	public void setSupermercado(Supermercado supermercado)
	{
		this.supermercado = supermercado;
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