package br.gov.sp.bec.hackathon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import regex.PRegex;


public class ConversorDeData {
	private static final String[] STOP_WORDS = new String[]{"de","a","o","que","e","do","da","em", "para","é","com","não","os","no","se","na","por","mais","as","dos","como","mas","foi","ao","ele","das","tem","à","seu","sua","ou","ser","quando","muito","há","nos","já","está","eu","também","só","pelo","pela","até","isso","ela","entre","era","depois","sem","mesmo","aos","ter","seus","quem","nas","me","esse","eles","estão","você","tinha","foram","essa","num","nem","suas","meu","às","minha","têm","numa","pelos","elas","havia","seja","qual","será","nós","tenho","lhe","deles","essas","esses","pelas","este","fosse","dele","tu","te","vocês","vos","lhes","meus","minhas","teu","tua","teus","tuas","nosso","nossa","nossos","nossas","dela","delas","esta","estes","estas","aquele","aquela","aqueles","aquelas","isto","aquilo","estou","está","estamos","estão","estive","esteve","estivemos","estiveram","estava","estávamos","estavam","estivera","estivéramos","esteja","estejamos","estejam","estivesse","estivéssemos","estivessem","estiver","estivermos","estiverem","hei","há","havemos","hão","houve","houvemos","houveram","houvera","houvéramos","haja","hajamos","hajam","houvesse","houvéssemos","houvessem","houver","houvermos","houverem","houverei","houverá","houveremos","houverão","houveria","houveríamos","houveriam","sou","somos","são","era","éramos","eram","fui","foi","fomos","foram","fora","fôramos","seja","sejamos","sejam","fosse","fôssemos","fossem","for","formos","forem","serei","será","seremos","serão","seria","seríamos","seriam","tenho","tem","temos","tém","tinha","tínhamos","tinham","tive","teve","tivemos","tiveram","tivera","tivéramos","tenha","tenhamos","tenham","tivesse","tivéssemos","tivessem","tiver","tivermos","tiverem","terei","terá","teremos","terão","teria","teríamos","teriam"};
	private static final String[] MESES_TEXTO = new String[]{"janeiro", "fevereiro", "marco", "março", "abril", "maio",
		"junho", "julho", "agosto", "setembro","outubro", "novembro", "dezembro"};
	private static final String[] MESES_NUMERO = new String[]{"01", "02", "03", "03", "04", "05",
		"06", "07", "08", "09","10", "11", "12"};
	private static final String[] NUMEROS_EXTENSO = new String[]{"um", "dois", "tres", "quatro", "cinco", "seis",
			"sete", "oito", "nove", "dez","onze", "doze", "treze", "quatorze", "catorze", "quinze", "dezesseis", "dezesete", "dezoito", "dezenove","vinte", "vinteum", "vintedois", "vintetres", "vintequatro",
			"vintecinco", "vinteseis", "vintesete", "vinteoito","vintenove", "trinta", "trintaum"};
	private static final String[] NUMEROS = new String[]{"01", "02", "03", "03", "04", "05",
			"06", "07", "08", "09","10", "11", "12", "13", "14","14", "15", "16", "17", "18","19", "20", "21", "22", "23",
			"24", "25", "26", "27","28", "29", "30", "31"};
	
	public static Date parse(String strData, String formato) {
		return stringToDate(strData, formato);
	}
	
	public static Date parse(String strData) {
		if(strData != null) {
			String dataSemPadrao = strData.toLowerCase();
			dataSemPadrao = trataTextoParaIdentificacao(dataSemPadrao);
			String dia = preencheDigitos(getTag(dataSemPadrao,"dia"),2);
			String mes = preencheDigitos(getTag(dataSemPadrao,"mes"),2);
			String ano = getTag(dataSemPadrao,"ano");
			String hora = preencheDigitos(getTag(dataSemPadrao,"hora"),2);
			String minuto = preencheDigitos(getTag(dataSemPadrao,"minuto"),2);
			
			if(ano.equals("")) {
				ano = Calendar.getInstance().get(Calendar.YEAR) + "";
			}
			
			if(minuto.equals("")) {
				minuto = "00";
			}
			String padraoAno = "yyyy";
			if(ano.length() == 2) {
				padraoAno = "yy";
			}
			String dataFormatada = dia + "/" + mes + "/" + ano + " " + hora + ":" + minuto;
			return stringToDate(dataFormatada ,"dd/MM/" + padraoAno + " hh:mm");
		}
		return null;
	}
	
	private static String getTag(String dataFormatada, String tag) {
		PRegex regex = new PRegex();
		List<String> matches = regex.Matches(dataFormatada, "<" + tag + ">(.*?)" + "</" + tag + ">").getAsList(1);
		if(matches.size() > 0) {
			return matches.get(matches.size() - 1);
		}else {
			return "";
		}
	}

	public static String preencheDigitos(String textoOriginal, int numeroDeDigitos){
		String sufixo = "";
		if(textoOriginal.length() < numeroDeDigitos) {
			
			for(int i = numeroDeDigitos - textoOriginal.length(); i < numeroDeDigitos; i++) {
				sufixo += "0";
			}
		}
		return sufixo + textoOriginal;
	}

	public static String trataTextoParaIdentificacao(String dataSemPadrao) {
		dataSemPadrao = removeStopWords(dataSemPadrao);
		dataSemPadrao = substituiMesesPorExtenso(dataSemPadrao);
		dataSemPadrao = extensoParaNumerico(dataSemPadrao);
		
		//remove duplicacao por extenso
		dataSemPadrao = dataSemPadrao.replaceAll("(\\d{1,2})\\s{0,}\\(\\w+\\)", "$1");
		dataSemPadrao = dataSemPadrao.replaceAll("(\\d{1,2})\\s{0,}\\([\\d\\s]{0,}\\)\\s{0,}", "$1");
		//padroes hora
		dataSemPadrao = dataSemPadrao.replaceAll("(\\d{1,2}):(\\d{1,2})", "<hora>" + "$1" + "</hora>" + "<minuto>" + "$2" + "</minuto>");
		dataSemPadrao = dataSemPadrao.replaceAll("(\\d{1,2})\\s{0,}h\\s{0,}(\\d{1,2})", "<hora>" + "$1" + "</hora>" + "<minuto>" + "$2" + "</minuto>");
		dataSemPadrao = dataSemPadrao.replaceAll("(\\d{1,2})\\s{0,}horas", "<hora>" + "$1" + "</hora>");
		dataSemPadrao = dataSemPadrao.replaceAll("(\\d{1,2})h", "<hora>" + "$1" + "</hora>");
		dataSemPadrao = dataSemPadrao.replaceAll("(\\d{1,2})\\s{0,}hs", "<hora>" + "$1" + "</hora>");
		//padroes data
		
				dataSemPadrao = dataSemPadrao.replaceAll("(\\d{1,2})[-/\\.](\\d{1,2})[-/\\.](\\d{2,4})", "<dia>" + "$1" + "</dia>" + "<mes>" + "$2" + "</mes>" + "<ano>" + "$3" + "</ano>");
				dataSemPadrao = dataSemPadrao.replaceAll("dia\\s{0,}(\\d{1,2})", "<dia>" + "$1" + "</dia>");
				dataSemPadrao = dataSemPadrao.replaceAll("(\\b\\s{0,}\\d{1,2})\\s{0,}<mes>", "<dia>$1</dia><mes>");
				dataSemPadrao = dataSemPadrao.replaceAll("</mes>\\s{0,}(\\d{2,4})\\s", "</mes><ano>" + "$1" + "</ano>");
		return dataSemPadrao;
	}

//	private static String removeCaracteresInvalidos(String dataFormatada) {
//		String nTexto = dataFormatada.replaceAll("[^\\d:/ ]","");
//		return nTexto;
//	}
	
	

	private static String extensoParaNumerico(String dataOriginal) {
		String dataSemPadrao = dataOriginal;
		for (int m = 0; m < NUMEROS_EXTENSO.length; m++) {
			dataSemPadrao  = dataSemPadrao.replaceAll("\\b" + NUMEROS_EXTENSO[m] + "\\b" , NUMEROS[m]);
		}
		return dataSemPadrao;
	}
	
	private static String substituiMesesPorExtenso(String dataSemPadrao) {
		for (int m = 0; m < MESES_TEXTO.length; m++) {
			dataSemPadrao = dataSemPadrao.replace(MESES_TEXTO[m], "<mes>" + MESES_NUMERO[m] + "</mes>");
			dataSemPadrao = dataSemPadrao.replaceAll("/(<mes>|</mes>)", "<mes>");
		}
		return dataSemPadrao;
	}

	private static String removeStopWords(String dataSemPadrao) {
		for (String stopword : STOP_WORDS) {
			dataSemPadrao = dataSemPadrao.replaceAll("\\b" + stopword + "\\b", "");
		}
		return dataSemPadrao;
	}
	
	public static Date stringToDate(String strData, String formato) {
		Date data = null;
		if(strData != null) {
			SimpleDateFormat inFormat = new SimpleDateFormat(formato);
			try {
				data = inFormat.parse(strData);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return data;
	}
	
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd'T'HH:mm");
	}
	
	public static String dateToString(Date date, String formato) {
		;
		String dataFormatada = null;
		if(date != null) {
			SimpleDateFormat rfc3339 = new SimpleDateFormat(formato);
			dataFormatada = rfc3339.format(date);
		}
		
		return dataFormatada;
	}
}
