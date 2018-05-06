package br.gov.sp.bec.hackathon.chatbot.acoes;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

public interface AcaoBot {
	
	String[] getSugestoes();
	String getTextoDeAcaoNaoReconhecida();
	String getTextoInicial();
	List<AcaoBot> getAcoes();
	RetornoDaInterpretacao interpretaMensagemRecebida(Message mensagem, AcaoBot acaoSelecionada);
	
	public default ReplyKeyboardMarkup pegaPossibilidadesDeResposta() {
		List<KeyboardRow> commandos = new ArrayList<KeyboardRow>();
		for (String sugestao : getSugestoes()) {
			KeyboardRow comando = new KeyboardRow();		
			comando.add(sugestao);
			commandos.add(comando);
		}
		
		ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
		replyMarkup.setResizeKeyboard(true);
		replyMarkup.setOneTimeKeyboad(false);
		replyMarkup.setKeyboard(commandos);
		replyMarkup.setSelective(false);
		
        return replyMarkup;
    }
	
	public default boolean getExibirSugestoes() {
		return true;
	}
	public default boolean matches(String texto) {
		for (String tag : getTags()) {
			if(texto.toLowerCase().contains(tag.toLowerCase().trim())) {
				return true;
			}
		}
		return false;
	}
	
	public default String[] getTags() {
		// TODO Auto-generated method stub
		return getSugestoes();
	}
}
