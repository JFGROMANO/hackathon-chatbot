package br.gov.sp.bec.hackathon.hibernate.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.sp.bec.hackathon.util.ConversorDeData;

@Entity
@Table(name="tb_encerradas")
public class OCEncerrada {
	private static final String STATUS = "status";
	private static final String ENTE_FEDERATIVO = "nome_ente_federativo";
	private static final String ORGAO = "nome_orgao";
	private static final String CODIGO_ORGAO = "codigo_orgao";
	private static final String UNIDADE_COMPRADORA = "nome_unidade_compradora";
	private static final String CODIGO_UNIDADE_COMPRADORA = "codigo_unidade_compradora";
	private static final String DATA_ATUALIZACAO = "data_atualizacao";
	private static final String QUANTIDADE_OC = "quantidade_oc";
	private static final String MES_ANO_ENCERRAMENTO = "mes_ano_encerramento";
	private static final String VALOR_UNITARIO_NEGOCIADO = "valor_unitario_negociado";
	private static final String VALOR_TOTAL_NEGOCIADO = "valor_total_negociado";
	private static final String QUANTIDADE = "quantidade";
	private static final String MODALIDADE = "modalidade";
	private static final String TIPO_OBJETO = "tipo_objeto";
	private static final String NUMERO_ITEM = "numero_item";
	private static final String NOME_CATALOGO = "nome_catalogo";
	private static final String CODIGO_CATALOGO = "codigo_catalogo";
	private static final String NUMERO_OC = "numero_oc";
	private static final String TEXTO_DESCRICAO_ITEM = "texto_descricao_item";
	private static final String CODIGO_DESCRICAO_ITEM = "codigo_descricao_item";
	private static final String NOME_UNIDADE_FORNECIMENTO = "nome_unidade_fornecimento";
	private static final String NOME_VENCEDOR = "nome_vencedor";
	private static final String CNPJ_VENCEDOR = "cnpj_vencedor";
	
	private long id;
	private String status; 
	private String enteFederativo;
	private String orgao;
	private long codigoDoOrgao;
	private String unidadeCompradora;
	private long codigoDaUnidadeCompradora;
	private Date dataDeAtualizacao;
	private long quantidadeOC;
	private Date mesEAnoEncerramento;
	private BigDecimal valorUnitarioNegociado;
	private BigDecimal valorTotalNegociado;
	private long quantidade;
	private String modalidade;
	private String tipoDoObjeto;
	private int numeroDoItem;
	private String catalogo;
	private long codigoCatalogo;
	private String oc;
	private String descricaoItem;
	private long codigoDescricaoItem;
	private String unidadeDeFornecimento;
	private String nomeVencedor;
	private String cnpjVencedor;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = STATUS, nullable=false)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = ENTE_FEDERATIVO, nullable=false)
	public String getEnteFederativo() {
		return enteFederativo;
	}
	public void setEnteFederativo(String enteFederativo) {
		this.enteFederativo = enteFederativo;
	}
	@Column(name = ORGAO, nullable=false)
	public String getOrgao() {
		return orgao;
	}
	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	@Column(name = CODIGO_ORGAO, nullable=false)
	public long getCodigoDoOrgao() {
		return codigoDoOrgao;
	}
	public void setCodigoDoOrgao(long codigoDoOrgao) {
		this.codigoDoOrgao = codigoDoOrgao;
	}
	@Column(name = UNIDADE_COMPRADORA, nullable=false)
	public String getUnidadeCompradora() {
		return unidadeCompradora;
	}
	public void setUnidadeCompradora(String unidadeCompradora) {
		this.unidadeCompradora = unidadeCompradora;
	}
	@Column(name = CODIGO_UNIDADE_COMPRADORA, nullable=false)
	public long getCodigoDaUnidadeCompradora() {
		return codigoDaUnidadeCompradora;
	}
	public void setCodigoDaUnidadeCompradora(long codigoDaUnidadeCompradora) {
		this.codigoDaUnidadeCompradora = codigoDaUnidadeCompradora;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = DATA_ATUALIZACAO, nullable=false)
	public Date getDataDeAtualizacao() {
		return dataDeAtualizacao;
	}
	public void setDataDeAtualizacao(Date dataDeAtualizacao) {
		this.dataDeAtualizacao = dataDeAtualizacao;
	}
	@Column(name = QUANTIDADE_OC, nullable=false)
	public long getQuantidadeOC() {
		return quantidadeOC;
	}
	public void setQuantidadeOC(long quantidadeOC) {
		this.quantidadeOC = quantidadeOC;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = MES_ANO_ENCERRAMENTO, nullable=false)
	public Date getMesEAnoEncerramento() {
		return mesEAnoEncerramento;
	}
	public void setMesEAnoEncerramento(Date mesEAnoEncerramento) {
		this.mesEAnoEncerramento = mesEAnoEncerramento;
	}
	@Column(name = VALOR_UNITARIO_NEGOCIADO, nullable=false)
	public BigDecimal getValorUnitarioNegociado() {
		return valorUnitarioNegociado;
	}
	public void setValorUnitarioNegociado(BigDecimal valorUnitarioNegociado) {
		this.valorUnitarioNegociado = valorUnitarioNegociado;
	}
	@Column(name = VALOR_TOTAL_NEGOCIADO, nullable=false)
	public BigDecimal getValorTotalNegociado() {
		return valorTotalNegociado;
	}
	public void setValorTotalNegociado(BigDecimal valorTotalNegociado) {
		this.valorTotalNegociado = valorTotalNegociado;
	}
	@Column(name = QUANTIDADE, nullable=false)
	public long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
	@Column(name = MODALIDADE, nullable=false)
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	@Column(name = TIPO_OBJETO, nullable=false)
	public String getTipoDoObjeto() {
		return tipoDoObjeto;
	}
	public void setTipoDoObjeto(String tipoDoObjeto) {
		this.tipoDoObjeto = tipoDoObjeto;
	}
	@Column(name = NUMERO_ITEM, nullable=false)
	public int getNumeroDoItem() {
		return numeroDoItem;
	}
	public void setNumeroDoItem(int numeroDoItem) {
		this.numeroDoItem = numeroDoItem;
	}
	@Column(name = NOME_CATALOGO, nullable=false)
	public String getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(String catalogo) {
		this.catalogo = catalogo;
	}
	@Column(name = CODIGO_CATALOGO, nullable=false)
	public long getCodigoCatalogo() {
		return codigoCatalogo;
	}
	public void setCodigoCatalogo(long codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}
	@Column(name = NUMERO_OC, nullable=false)
	public String getOc() {
		return oc;
	}
	public void setOc(String oc) {
		this.oc = oc;
	}
	@Column(name = TEXTO_DESCRICAO_ITEM, nullable=false)
	public String getDescricaoItem() {
		return descricaoItem;
	}
	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}
	@Column(name = CODIGO_DESCRICAO_ITEM, nullable=false)
	public long getCodigoDescricaoItem() {
		return codigoDescricaoItem;
	}
	public void setCodigoDescricaoItem(long codigoDescricaoItem) {
		this.codigoDescricaoItem = codigoDescricaoItem;
	}
	@Column(name = NOME_UNIDADE_FORNECIMENTO, nullable=false)
	public String getUnidadeDeFornecimento() {
		return unidadeDeFornecimento;
	}
	public void setUnidadeDeFornecimento(String unidadeDeFornecimento) {
		this.unidadeDeFornecimento = unidadeDeFornecimento;
	}
	@Column(name = NOME_VENCEDOR, nullable=true)
	public String getNomeVencedor() {
		return nomeVencedor;
	}
	public void setNomeVencedor(String nomeVencedor) {
		this.nomeVencedor = nomeVencedor;
	}
	@Column(name = CNPJ_VENCEDOR, nullable=true)
	public String getCnpjVencedor() {
		return cnpjVencedor;
	}
	public void setCnpjVencedor(String cnpjVencedor) {
		this.cnpjVencedor = cnpjVencedor;
	}
}



