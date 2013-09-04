package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestaCandlestickFactory {
	public static void main(String[] args) {

		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(80.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(75.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(69.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(53.3, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
				negociacao3, negociacao4);

		CandlestickFactory fabricaCandlestick = new CandlestickFactory();
		Candlestick candlestick = fabricaCandlestick.constroiCandleParaData(
				hoje, negociacoes);

		System.out.println(candlestick);

	}

}
