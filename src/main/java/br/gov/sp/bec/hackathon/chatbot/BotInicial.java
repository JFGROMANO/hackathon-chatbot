package br.gov.sp.bec.hackathon.chatbot;

import org.telegram.telegrambots.api.methods.ActionType;
import org.telegram.telegrambots.api.methods.send.SendChatAction;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.gov.sp.bec.hackathon.chatbot.acoes.MensagemInicial;
import br.gov.sp.bec.hackathon.chatbot.acoes.AcaoBot;
import br.gov.sp.bec.hackathon.chatbot.acoes.RetornoDaInterpretacao;

public class BotInicial extends TelegramLongPollingBot {

	
	private AcaoBot possibilidadeSelecionada;
	
	public BotInicial() {
		this.possibilidadeSelecionada = new MensagemInicial();
	}
	
	public String getBotUsername() {
		return "hackathon_bec_2018";
	}

	public void onUpdateReceived(Update update) {
		Message mensagem = update.getMessage();
		if (!mensagem.hasText()) {
			return;
		}
		RetornoDaInterpretacao resultado = possibilidadeSelecionada.interpretaMensagemRecebida(mensagem, possibilidadeSelecionada);
		AcaoBot acao = resultado.getAcao();
		setDigitando(mensagem, true);
		if(mensagem.getText().toLowerCase().equals("inicio")) {
			acao = new MensagemInicial();
		}
		if(acao == null) {
			sendMessage(mensagem, resultado.getMensagemRetorno());
			sendMessage(mensagem, possibilidadeSelecionada.getTextoDeAcaoNaoReconhecida());
		}else {
			possibilidadeSelecionada = acao;
			sendMessage(mensagem, resultado.getMensagemRetorno());
			sendMessage(mensagem, possibilidadeSelecionada.getTextoInicial());
		}
	}

	private void setDigitando(Message mensagem, boolean digitando) {
		SendChatAction ca = new SendChatAction();
		if(digitando) {
			ca.setAction(ActionType.TYPING);
		}else {
			ca.setAction(ActionType.FINDLOCATION);
		}
    	
    	ca.setChatId(mensagem.getChatId());
        
    	try {
			this.sendChatAction(ca);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getBotToken() {
		return "538976024:AAF6pkplnRt_lpwGprJ-G1BTV8szHW1xAeY";
	}
	
	private void sendMessage(Message mensagem, String texto) {
		if(!texto.isEmpty()) {
			SendMessage sendMessage = new SendMessage();
	        sendMessage.enableMarkdown(true);
	        sendMessage.setChatId(mensagem.getChatId());
	        sendMessage.setText(texto);
	        sendMessage.enableMarkdown(true);
	        if(possibilidadeSelecionada.getExibirSugestoes()) {
	        	ReplyKeyboard markup = possibilidadeSelecionada.pegaPossibilidadesDeResposta();
	        	sendMessage.setReplyMarkup(markup);
	        }
	        setDigitando(mensagem, false);
	        try {
	        	System.out.println(sendMessage);
	            sendMessage(sendMessage);
	        } catch (TelegramApiException e) {
	        	e.printStackTrace();
	        }
		}
    }
}
