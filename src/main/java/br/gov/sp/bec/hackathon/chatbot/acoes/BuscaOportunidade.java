package br.gov.sp.bec.hackathon.chatbot.acoes;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.objects.Message;

import br.gov.sp.bec.hackathon.hibernate.dao.OCPropostaDao;
import br.gov.sp.bec.hackathon.hibernate.model.OCProposta;

public class BuscaOportunidade implements AcaoBot {

	private String[] tags;
	private ArrayList<AcaoBot> acaoBot;
	private List<OCProposta> listaResultados;
	private OCPropostaDao propostaDao;

	public BuscaOportunidade() {
		this.tags = new String[] {"Buscar oportunidades de licitação", "busca oportunidade", "oportunidade"};
		this.acaoBot = new ArrayList<AcaoBot>();
		this.propostaDao = new OCPropostaDao();
	}

	@Override
	public List<AcaoBot> getAcoes() {
		return acaoBot;
	}

	@Override
	public String getTextoDeAcaoNaoReconhecida() {
		return "";
	}

	@Override
	public String[] getTags() {
		return tags;
	}

	@Override
	public String getTextoInicial() {
		return "Defina o seu produto ou serviço em poucas palavras";
	}

	@Override
	public RetornoDaInterpretacao interpretaMensagemRecebida(Message mensagem, AcaoBot possibilidadeSelecionada) {
		RetornoDaInterpretacao resultado = new RetornoDaInterpretacao();
		String palavra = mensagem.getText();
		
		if(!palavra.isEmpty()) {
			this.listaResultados = new ArrayList<>();
			listaResultados = propostaDao.findAllLike(palavra,"objeto","numeroOc");
			if(this.listaResultados.size() > 0) {
				resultado.setMensagemRetorno("");
				resultado.setAcao(new ExibeResultados<OCProposta>(this.listaResultados));
			}else {
				resultado.setMensagemRetorno("Nenhuma oportunidade encontrada!");
			}
			
		}
		if(palavra.indexOf("inicio") > -1) {
			resultado.setAcao(new MensagemInicial());
		}
		return resultado;
	}

	@Override
	public String[] getSugestoes() {
		return new String[] {};
	}
}
