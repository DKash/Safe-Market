/**
 * 
 */
package br.com.safemarket.util;

/**
 * @author Audry Martins
 *
 */
public class Mensagens
{
	private String msg_cliente_cadastrado_com_sucesso;

	private String msg_cliente_alterado_com_sucesso;

	private String msg_cliente_excluido_com_sucesso;

	private String msg_produto_cadastrado_com_sucesso;

	private String msg_produto_alterado_com_sucesso;

	private String msg_produto_excluido_com_sucesso;

	private String msg_perfil_cadastrado_com_sucesso;

	private String msg_perfil_alterado_com_sucesso;

	private String msg_perfil_excluido_com_sucesso;

	private String msg_categoria_cadastrada_com_sucesso;

	private String msg_categoria_alterada_com_sucesso;

	private String msg_categoria_excluida_com_sucesso;

	private String msg_marca_cadastrada_com_sucesso;

	private String msg_marca_alterada_com_sucesso;

	private String msg_marca_excluida_com_sucesso;

	private String msg_unidadeMedida_cadastrada_com_sucesso;

	private String msg_unidadeMedida_alterada_com_sucesso;

	private String msg_unidadeMedida_excluida_com_sucesso;

	private String msg_supermercado_cadastrado_com_sucesso;

	private String msg_supermercado_alterado_com_sucesso;

	private String msg_supermercado_excluido_com_sucesso;

	private String msg_usuario_cadastrado_com_sucesso;

	private String msg_usuario_alterado_com_sucesso;

	private String msg_usuario_excluido_com_sucesso;

	// Validação de campos
	private String msg_campo_invalido;

	/**
	 * 
	 */
	public Mensagens()
	{
		super();
		this.msg_cliente_cadastrado_com_sucesso = "O cliente foi cadastrado com sucesso.";
		this.msg_cliente_alterado_com_sucesso = "O cliente foi cadastrado com sucesso.";
		this.msg_cliente_excluido_com_sucesso = "O cliente foi cadastrado com sucesso.";
		this.msg_produto_cadastrado_com_sucesso = "O produto foi cadastrado com sucesso.";
		this.msg_produto_alterado_com_sucesso = "O produto foi alterado com sucesso.";
		this.msg_produto_excluido_com_sucesso = "O produto foi excluido com sucesso.";
		this.msg_perfil_cadastrado_com_sucesso = "O perfil foi cadastrado com sucesso.";
		this.msg_perfil_alterado_com_sucesso = "O perfil foi alterado com sucesso.";
		this.msg_perfil_excluido_com_sucesso = "O perfil foi excluido com sucesso.";
		this.msg_categoria_cadastrada_com_sucesso = "A categoria foi cadastrada com sucesso.";
		this.msg_categoria_alterada_com_sucesso = "A categoria foi alterada com sucesso.";
		this.msg_categoria_excluida_com_sucesso = "A categoria foi excluida com sucesso.";
		this.msg_marca_cadastrada_com_sucesso = "A marca foi cadastrada com sucesso.";
		this.msg_marca_alterada_com_sucesso = "A marca foi alterada com sucesso.";
		this.msg_marca_excluida_com_sucesso = "A marca foi excluida com sucesso.";
		this.msg_unidadeMedida_cadastrada_com_sucesso = "A unidade de medida foi cadastrada com sucesso.";
		this.msg_unidadeMedida_alterada_com_sucesso = "A unidade de medida foi alterada com sucesso.";
		this.msg_unidadeMedida_excluida_com_sucesso = "A unidade de medida foi excluida com sucesso.";
		this.msg_supermercado_cadastrado_com_sucesso = "O supermercado foi cadastrado com sucesso.";
		this.msg_supermercado_alterado_com_sucesso = "O supermercado foi alterado com sucesso.";
		this.msg_supermercado_excluido_com_sucesso = "O supermercado foi excluido com sucesso.";
		this.msg_usuario_cadastrado_com_sucesso = "O usuario foi cadastrado com sucesso.";
		this.msg_usuario_alterado_com_sucesso = "O uusuario foi alterado com sucesso.";
		this.msg_usuario_excluido_com_sucesso = "O usuario foi excluido com sucesso.";
		this.msg_campo_invalido = "O(s) campo(s) a seguir não está(ão) preenchido(s): ";
	}

	/**
	 * @param msg_cliente_cadastrado_com_sucesso
	 * @param msg_cliente_alterado_com_sucesso
	 * @param msg_cliente_excluido_com_sucesso
	 * @param msg_produto_cadastrado_com_sucesso
	 * @param msg_produto_alterado_com_sucesso
	 * @param msg_produto_excluido_com_sucesso
	 * @param msg_perfil_cadastrado_com_sucesso
	 * @param msg_perfil_alterado_com_sucesso
	 * @param msg_perfil_excluido_com_sucesso
	 * @param msg_categoria_cadastrada_com_sucesso
	 * @param msg_categoria_alterada_com_sucesso
	 * @param msg_categoria_excluida_com_sucesso
	 * @param msg_marca_cadastrada_com_sucesso
	 * @param msg_marca_alterada_com_sucesso
	 * @param msg_marca_excluida_com_sucesso
	 * @param msg_unidadeMedida_cadastrada_com_sucesso
	 * @param msg_unidadeMedida_alterada_com_sucesso
	 * @param msg_unidadeMedida_excluida_com_sucesso
	 * @param msg_supermercado_cadastrado_com_sucesso
	 * @param msg_supermercado_alterado_com_sucesso
	 * @param msg_supermercado_excluido_com_sucesso
	 * @param msg_usuario_cadastrado_com_sucesso
	 * @param msg_usuario_alterado_com_sucesso
	 * @param msg_usuario_excluido_com_sucesso
	 * @param msg_campo_invalido
	 */
	public Mensagens(String msg_cliente_cadastrado_com_sucesso, String msg_cliente_alterado_com_sucesso,
			String msg_cliente_excluido_com_sucesso, String msg_produto_cadastrado_com_sucesso,
			String msg_produto_alterado_com_sucesso, String msg_produto_excluido_com_sucesso,
			String msg_perfil_cadastrado_com_sucesso, String msg_perfil_alterado_com_sucesso,
			String msg_perfil_excluido_com_sucesso, String msg_categoria_cadastrada_com_sucesso,
			String msg_categoria_alterada_com_sucesso, String msg_categoria_excluida_com_sucesso,
			String msg_marca_cadastrada_com_sucesso, String msg_marca_alterada_com_sucesso,
			String msg_marca_excluida_com_sucesso, String msg_unidadeMedida_cadastrada_com_sucesso,
			String msg_unidadeMedida_alterada_com_sucesso, String msg_unidadeMedida_excluida_com_sucesso,
			String msg_supermercado_cadastrado_com_sucesso, String msg_supermercado_alterado_com_sucesso,
			String msg_supermercado_excluido_com_sucesso, String msg_usuario_cadastrado_com_sucesso,
			String msg_usuario_alterado_com_sucesso, String msg_usuario_excluido_com_sucesso, String msg_campo_invalido)
	{
		super();
		this.msg_cliente_cadastrado_com_sucesso = msg_cliente_cadastrado_com_sucesso;
		this.msg_cliente_alterado_com_sucesso = msg_cliente_alterado_com_sucesso;
		this.msg_cliente_excluido_com_sucesso = msg_cliente_excluido_com_sucesso;
		this.msg_produto_cadastrado_com_sucesso = msg_produto_cadastrado_com_sucesso;
		this.msg_produto_alterado_com_sucesso = msg_produto_alterado_com_sucesso;
		this.msg_produto_excluido_com_sucesso = msg_produto_excluido_com_sucesso;
		this.msg_perfil_cadastrado_com_sucesso = msg_perfil_cadastrado_com_sucesso;
		this.msg_perfil_alterado_com_sucesso = msg_perfil_alterado_com_sucesso;
		this.msg_perfil_excluido_com_sucesso = msg_perfil_excluido_com_sucesso;
		this.msg_categoria_cadastrada_com_sucesso = msg_categoria_cadastrada_com_sucesso;
		this.msg_categoria_alterada_com_sucesso = msg_categoria_alterada_com_sucesso;
		this.msg_categoria_excluida_com_sucesso = msg_categoria_excluida_com_sucesso;
		this.msg_marca_cadastrada_com_sucesso = msg_marca_cadastrada_com_sucesso;
		this.msg_marca_alterada_com_sucesso = msg_marca_alterada_com_sucesso;
		this.msg_marca_excluida_com_sucesso = msg_marca_excluida_com_sucesso;
		this.msg_unidadeMedida_cadastrada_com_sucesso = msg_unidadeMedida_cadastrada_com_sucesso;
		this.msg_unidadeMedida_alterada_com_sucesso = msg_unidadeMedida_alterada_com_sucesso;
		this.msg_unidadeMedida_excluida_com_sucesso = msg_unidadeMedida_excluida_com_sucesso;
		this.msg_supermercado_cadastrado_com_sucesso = msg_supermercado_cadastrado_com_sucesso;
		this.msg_supermercado_alterado_com_sucesso = msg_supermercado_alterado_com_sucesso;
		this.msg_supermercado_excluido_com_sucesso = msg_supermercado_excluido_com_sucesso;
		this.msg_usuario_cadastrado_com_sucesso = msg_usuario_cadastrado_com_sucesso;
		this.msg_usuario_alterado_com_sucesso = msg_usuario_alterado_com_sucesso;
		this.msg_usuario_excluido_com_sucesso = msg_usuario_excluido_com_sucesso;
		this.msg_campo_invalido = msg_campo_invalido;
	}

	// Gets e Sets
	/**
	 * @return the msg_cliente_cadastrado_com_sucesso
	 */
	public String getMsg_cliente_cadastrado_com_sucesso()
	{
		return msg_cliente_cadastrado_com_sucesso;
	}

	/**
	 * @return the msg_cliente_alterado_com_sucesso
	 */
	public String getMsg_cliente_alterado_com_sucesso()
	{
		return msg_cliente_alterado_com_sucesso;
	}

	/**
	 * @return the msg_cliente_excluido_com_sucesso
	 */
	public String getMsg_cliente_excluido_com_sucesso()
	{
		return msg_cliente_excluido_com_sucesso;
	}

	/**
	 * @return the msg_produto_cadastrado_com_sucesso
	 */
	public String getMsg_produto_cadastrado_com_sucesso()
	{
		return msg_produto_cadastrado_com_sucesso;
	}

	/**
	 * @return the msg_produto_alterado_com_sucesso
	 */
	public String getMsg_produto_alterado_com_sucesso()
	{
		return msg_produto_alterado_com_sucesso;
	}

	/**
	 * @return the msg_produto_excluido_com_sucesso
	 */
	public String getMsg_produto_excluido_com_sucesso()
	{
		return msg_produto_excluido_com_sucesso;
	}

	/**
	 * @return the msg_perfil_cadastrado_com_sucesso
	 */
	public String getMsg_perfil_cadastrado_com_sucesso()
	{
		return msg_perfil_cadastrado_com_sucesso;
	}

	/**
	 * @return the msg_perfil_alterado_com_sucesso
	 */
	public String getMsg_perfil_alterado_com_sucesso()
	{
		return msg_perfil_alterado_com_sucesso;
	}

	/**
	 * @return the msg_perfil_excluido_com_sucesso
	 */
	public String getMsg_perfil_excluido_com_sucesso()
	{
		return msg_perfil_excluido_com_sucesso;
	}

	/**
	 * @return the msg_categoria_cadastrada_com_sucesso
	 */
	public String getMsg_categoria_cadastrada_com_sucesso()
	{
		return msg_categoria_cadastrada_com_sucesso;
	}

	/**
	 * @return the msg_categoria_alterada_com_sucesso
	 */
	public String getMsg_categoria_alterada_com_sucesso()
	{
		return msg_categoria_alterada_com_sucesso;
	}

	/**
	 * @return the msg_categoria_excluida_com_sucesso
	 */
	public String getMsg_categoria_excluida_com_sucesso()
	{
		return msg_categoria_excluida_com_sucesso;
	}

	/**
	 * @return the msg_marca_cadastrada_com_sucesso
	 */
	public String getMsg_marca_cadastrada_com_sucesso()
	{
		return msg_marca_cadastrada_com_sucesso;
	}

	/**
	 * @return the msg_marca_alterada_com_sucesso
	 */
	public String getMsg_marca_alterada_com_sucesso()
	{
		return msg_marca_alterada_com_sucesso;
	}

	/**
	 * @return the msg_marca_excluida_com_sucesso
	 */
	public String getMsg_marca_excluida_com_sucesso()
	{
		return msg_marca_excluida_com_sucesso;
	}

	/**
	 * @return the msg_unidadeMedida_cadastrada_com_sucesso
	 */
	public String getMsg_unidadeMedida_cadastrada_com_sucesso()
	{
		return msg_unidadeMedida_cadastrada_com_sucesso;
	}

	/**
	 * @return the msg_unidadeMedida_alterada_com_sucesso
	 */
	public String getMsg_unidadeMedida_alterada_com_sucesso()
	{
		return msg_unidadeMedida_alterada_com_sucesso;
	}

	/**
	 * @return the msg_unidadeMedida_excluida_com_sucesso
	 */
	public String getMsg_unidadeMedida_excluida_com_sucesso()
	{
		return msg_unidadeMedida_excluida_com_sucesso;
	}

	/**
	 * @return the msg_supermercado_cadastrado_com_sucesso
	 */
	public String getMsg_supermercado_cadastrado_com_sucesso()
	{
		return msg_supermercado_cadastrado_com_sucesso;
	}

	/**
	 * @return the msg_supermercado_alterado_com_sucesso
	 */
	public String getMsg_supermercado_alterado_com_sucesso()
	{
		return msg_supermercado_alterado_com_sucesso;
	}

	/**
	 * @return the msg_supermercado_excluido_com_sucesso
	 */
	public String getMsg_supermercado_excluido_com_sucesso()
	{
		return msg_supermercado_excluido_com_sucesso;
	}

	/**
	 * @return the msg_usuario_cadastrado_com_sucesso
	 */
	public String getMsg_usuario_cadastrado_com_sucesso()
	{
		return msg_usuario_cadastrado_com_sucesso;
	}

	/**
	 * @return the msg_uauario_alterado_com_sucesso
	 */
	public String getMsg_usuario_alterado_com_sucesso()
	{
		return msg_usuario_alterado_com_sucesso;
	}

	/**
	 * @return the msg_usuario_excluido_com_sucesso
	 */
	public String getMsg_usuario_excluido_com_sucesso()
	{
		return msg_usuario_excluido_com_sucesso;
	}

	/**
	 * @return the msg_campo_invalido
	 */
	public String getMsg_campo_invalido()
	{
		return msg_campo_invalido;
	}
}