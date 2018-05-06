package br.gov.sp.bec.hackathon.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.sp.bec.hackathon.util.ConversorDeData;

@Entity
@Table(name="tb_proposta")
public class OCProposta {
	private static final String CODIGO_DESCRICAO_ITEM = "codigo_descricao_item";

	private static final String OBJETO = "objeto";

	private static final String DATA_INICIO_PROPOSTA = "data_inicio_proposta";

	private static final String NUMERO_OC = "oc";

	private static final String DATA_FIM_PROPOSTA = "data_fim_proposta";
	
	private long id;
	private Date dataFimProposta;
	private Date dataInicioProposta;
	private String objeto;
	private long codigoMaterial;
	private String numeroOc;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = DATA_FIM_PROPOSTA,nullable=false)
	public Date getDataFimProposta() {
		return dataFimProposta;
	}
	public void setDataFimProposta(Date dataFimProposta) {
		this.dataFimProposta = dataFimProposta;
	}

	@Column(name = OBJETO, nullable=false)
	@Lob
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	@Column(name = CODIGO_DESCRICAO_ITEM, nullable=false)
	public long getCodigoMaterial() {
		return codigoMaterial;
	}
	public void setCodigoMaterial(long codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = DATA_INICIO_PROPOSTA, nullable=false)
	public Date getDataInicioProposta() {
		return dataInicioProposta;
	}
	public void setDataInicioProposta(Date dataInicioProposta) {
		this.dataInicioProposta = dataInicioProposta;
	}
	@Column(name = NUMERO_OC, nullable=false)
	public String getNumeroOc() {
		return numeroOc;
	}
	public void setNumeroOc(String oc) {
		this.numeroOc = oc;
	}
	
	@Override
	public String toString() {
		return "*OC:* " + this.getNumeroOc() + "\n*Objeto:*" + this.getObjeto() +"\n*Data fim de proposta:*" + ConversorDeData.dateToString(this.getDataFimProposta(), "dd/MM/yyyy hh:mm");
	}
}
