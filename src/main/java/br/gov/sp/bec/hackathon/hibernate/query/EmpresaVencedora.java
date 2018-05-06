package br.gov.sp.bec.hackathon.hibernate.query;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import br.gov.sp.bec.hackathon.util.ConversorDeData;

public class EmpresaVencedora {
	BigDecimal valor;
	String nome;
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();  
		String formatado = nf.format (valor);
		return "*Razao social:* " + nome + "\n*Valor global:*" + formatado;
	}
}
