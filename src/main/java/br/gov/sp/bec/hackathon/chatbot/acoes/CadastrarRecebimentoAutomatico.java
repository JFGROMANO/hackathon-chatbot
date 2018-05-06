package br.gov.sp.bec.hackathon.chatbot.acoes;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.objects.Message;

public class CadastrarRecebimentoAutomatico  implements AcaoBot {

	private String[] sugestoes;
	private ArrayList<AcaoBot> acaoBot;
	private String[] tags;

	public CadastrarRecebimentoAutomatico() {
		sugestoes = new String[] {"Sim", "Não"};
		tags = new String[] {"Buscar oportunidades de licitação"};
		this.acaoBot = new ArrayList<AcaoBot>();
	}
	
	@Override
	public String[] getSugestoes() {
		// TODO Auto-generated method stub
		return sugestoes;
	}
	@Override
	public String[] getTags() {
		return tags;
	}
	@Override
	public String getTextoDeAcaoNaoReconhecida() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTextoInicial() {
		// TODO Auto-generated method stub
		return "Deseja cadastrar a palavra como criterio para receber novas oportunidades assim que elas forem disponibilizadas no portal da BEC?";
	}

	@Override
	public List<AcaoBot> getAcoes() {
		// TODO Auto-generated method stub
		return acaoBot;
	}

	@Override
	public RetornoDaInterpretacao interpretaMensagemRecebida(Message mensagem, AcaoBot acaoSelecionada) {
		RetornoDaInterpretacao resultado = new RetornoDaInterpretacao();
		resultado .setMensagemRetorno("");
		resultado.setAcao(new BuscaOportunidade());
		return resultado;
	}

}
