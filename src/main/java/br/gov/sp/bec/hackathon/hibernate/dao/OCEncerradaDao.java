package br.gov.sp.bec.hackathon.hibernate.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;

import br.gov.sp.bec.hackathon.hibernate.model.OCEncerrada;
import br.gov.sp.bec.hackathon.hibernate.query.EmpresaVencedora;

public class OCEncerradaDao extends GenericDao<OCEncerrada> {

	;

	public List<EmpresaVencedora> pegaConcorrentesValorGlobal(String palavra) {
			Query query = getSession().createQuery("SELECT nomeVencedor, SUM(valorTotalNegociado) as total FROM " + this.classe.getSimpleName() + " where descricaoItem like '%" + palavra + "%' and nomeVencedor is not null GROUP BY nomeVencedor order by total desc");
			List<EmpresaVencedora> list = new ArrayList<EmpresaVencedora>();
			for(final Object o : query.list()) {
				Object[] lo = (Object[]) o;
				EmpresaVencedora emp = new EmpresaVencedora();
				emp.setNome((String) lo[0]);
				emp.setValor((BigDecimal) lo[1]);
			    list.add(emp);
			}
			return list;
	}
}
