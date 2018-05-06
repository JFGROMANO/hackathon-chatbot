package br.gov.sp.bec.hackathon.chatbot.acoes;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.objects.Message;

public class ExibeResultados<T> implements AcaoBot {

	private List<T> listaResultados;
	private int posicaoAtual;
	private String[] sugestoes;
	private ArrayList<AcaoBot> acaoBot;

	public ExibeResultados(List<T> listaResultados) {
		this.listaResultados = listaResultados;
		this.posicaoAtual = 0;
		this.sugestoes = new String[] {"avancar", "voltar", "participar", "cancelar"};
		this.acaoBot = new ArrayList<AcaoBot>();
	}
	
	@Override
	public boolean getExibirSugestoes() {
		return true;
	}

	@Override
	public String getTextoDeAcaoNaoReconhecida() {
		return "";
	}

	@Override
	public String getTextoInicial() {
		String mensagem = "";
		mensagem = "Resultado " + (posicaoAtual + 1) + " de " + listaResultados.size() + "\n";
		T op = listaResultados.get(posicaoAtual);
		mensagem += op.toString(); 
		return mensagem;
	}

	@Override
	public List<AcaoBot> getAcoes() {
		return acaoBot;
	}

	@Override
	public RetornoDaInterpretacao interpretaMensagemRecebida(Message mensagem, AcaoBot acaoSelecionada) {
		RetornoDaInterpretacao resultado = new RetornoDaInterpretacao();
		String strMenssagem = mensagem.getText();
		resultado.setAcao(this);
		
		if(strMenssagem.toLowerCase().indexOf("avancar") > -1) {
			posicaoAtual++;
		}
		
		if(strMenssagem.toLowerCase().indexOf("voltar") > -1) {
			posicaoAtual--;
		}
		
		if(strMenssagem.toLowerCase().indexOf("participar") > -1) {
			posicaoAtual++;
		}
		
		if(strMenssagem.toLowerCase().indexOf("cancelar") > -1) {
			resultado.setAcao(new BuscaOportunidade());
		}
		
		if(posicaoAtual >= listaResultados.size() || posicaoAtual <= -1) {
			posicaoAtual = 0;
		}
		resultado.setMensagemRetorno("");
		return resultado;
	}

	@Override
	public String[] getSugestoes() {
		// TODO Auto-generated method stub
		return sugestoes;
	}
}
