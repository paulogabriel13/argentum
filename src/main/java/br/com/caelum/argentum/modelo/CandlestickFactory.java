package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CandlestickFactory {

	public Candlestick constroiCandleParaData(Calendar data,
			List<Negociacao> negociacoes) {

		double maximo = negociacoes.isEmpty() ? 0 : Double.MIN_VALUE;
		double minimo = negociacoes.isEmpty() ? 0 : Double.MAX_VALUE;
		double volume = 0;

		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();

			if (negociacao.getPreco() > maximo) {
				maximo = negociacao.getPreco();
			}

			if (negociacao.getPreco() < minimo) {
				minimo = negociacao.getPreco();
			}

		}

		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0)
				.getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(
				negociacoes.size() - 1).getPreco();

		return new CandlestickBuilder().comAbertura(abertura)
				.comFechamento(fechamento).comMinimo(minimo).comMaximo(maximo)
				.comVolume(volume).comData(data).geraCandlestick();

	}

	public List<Candlestick> criaCandles(List<Negociacao> negociacoes)
			throws IllegalArgumentException {

		if (negociacoes == null || negociacoes.isEmpty()) {
			throw new IllegalArgumentException("nao existe negociacoes");
		}

		Collections.sort(negociacoes);

		List<Candlestick> listaCandle = new ArrayList<Candlestick>();
		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();

		Calendar dataAtual = negociacoes.get(0).getData();

		for (Negociacao negociacao : negociacoes) {

			if (negociacao.getData().before(dataAtual)) {
				throw new IllegalArgumentException(
						"negociacoes em ordem errada");
			}

			if (!negociacao.isMesmoDia(dataAtual)) {
				Candlestick candle = constroiCandleParaData(dataAtual,
						negociacoesDoDia);
				listaCandle.add(candle);
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacoesDoDia.add(negociacao);

		}

		Candlestick candle = constroiCandleParaData(dataAtual, negociacoesDoDia);
		listaCandle.add(candle);

		return listaCandle;
	}

}
