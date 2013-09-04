package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestaCandlestickFactorySemNegociacoes {
	public static void main(String[] args) {

		Calendar hoje = Calendar.getInstance();

		List<Negociacao> negociacoes = Arrays.asList();

		CandlestickFactory fabricaCandlestick = new CandlestickFactory();
		Candlestick candlestick = fabricaCandlestick.constroiCandleParaData(
				hoje, negociacoes);

		System.out.println(candlestick);

	}

}
