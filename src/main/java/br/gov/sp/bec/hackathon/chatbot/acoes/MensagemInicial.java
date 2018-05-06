package br.gov.sp.bec.hackathon.chatbot.acoes;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.objects.Message;

public class MensagemInicial implements AcaoBot {
	private List<AcaoBot> acoes;
	private String[] sugestoes;
	
	public MensagemInicial() {
		this.sugestoes = new String[] {"Buscar oportunidades de licitação", "Buscar concorrentes por produto/servico"};
		this.acoes = new ArrayList<AcaoBot>();
		this.acoes.add(new CadastrarRecebimentoAutomatico());
		this.acoes.add(new BuscaConcorrentes());
	}
	
	public String executa() {
		return null;
	}
	
	@Override
	public List<AcaoBot> getAcoes() {
		return this.acoes;
	}

	@Override
	public String getTextoDeAcaoNaoReconhecida() {
		return "Seja bem vindo ao sistema de consultas da BEC, escolha uma das ações abaixo:\n";
	}

	@Override
	public String getTextoInicial() {
		return "Seja bem vindo ao sistema de consultas da BEC, escolha uma das ações abaixo:\n";
	}

	@Override
	public RetornoDaInterpretacao interpretaMensagemRecebida(Message mensagem, AcaoBot acaoSelecionada) {
		String mensagemRecebida = mensagem.getText();
		RetornoDaInterpretacao retorno = new RetornoDaInterpretacao();
		retorno.setMensagemRetorno("");
		for (AcaoBot possibilidade : acaoSelecionada.getAcoes()) {
			if(possibilidade.matches(mensagemRecebida)) {
				retorno.setAcao(possibilidade);
				return retorno;
			}
		}
		return retorno;
	}

	@Override
	public String[] getSugestoes() {
		return this.sugestoes;
	}
}
