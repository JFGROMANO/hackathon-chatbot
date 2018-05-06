package br.gov.sp.bec.hackathon.chatbot.acoes;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.objects.Message;

import br.gov.sp.bec.hackathon.hibernate.dao.OCEncerradaDao;
import br.gov.sp.bec.hackathon.hibernate.query.EmpresaVencedora;

public class BuscaConcorrentes implements AcaoBot {

	private String[] tags;
	private ArrayList<AcaoBot> acaoBot;
	private OCEncerradaDao encerradasDao;
	private List<EmpresaVencedora> listaResultados;

	public BuscaConcorrentes(){
		this.tags = new String[] {"Buscar concorrentes por produto/servico", "concorrente"};
		this.acaoBot = new ArrayList<AcaoBot>();
		this.encerradasDao = new OCEncerradaDao();
	}
	@Override
	public String[] getSugestoes() {
		// TODO Auto-generated method stub
		return new String[]{};
	}

	@Override
	public String getTextoDeAcaoNaoReconhecida() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTextoInicial() {
		return "Defina o seu produto ou serviço em poucas palavras";
	}

	@Override
	public List<AcaoBot> getAcoes() {
		// TODO Auto-generated method stub
		return acaoBot;
	}

	@Override
	public RetornoDaInterpretacao interpretaMensagemRecebida(Message mensagem, AcaoBot acaoSelecionada) {
		RetornoDaInterpretacao resultado = new RetornoDaInterpretacao();
		String palavra = mensagem.getText();
		
		if(!palavra.isEmpty()) {
			this.listaResultados = new ArrayList<>();
			listaResultados = encerradasDao.pegaConcorrentesValorGlobal(palavra);
			if(this.listaResultados.size() > 0) {
				resultado.setMensagemRetorno("");
				resultado.setAcao(new ExibeResultados<EmpresaVencedora>(this.listaResultados));
			}else {
				resultado.setMensagemRetorno("Nenhum resultado encontrado!");
			}
		}
		if(palavra.indexOf("inicio") > -1) {
			resultado.setAcao(new MensagemInicial());
		}
		return resultado;
	}
	@Override
	public String[] getTags() {
		return this.tags;
	}
}
