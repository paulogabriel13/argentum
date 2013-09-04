package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class CandlestickFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {

		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
				negociacao3, negociacao4);

		CandlestickFactory fabricaCandlestick = new CandlestickFactory();
		Candlestick candlestick = fabricaCandlestick.constroiCandleParaData(
				hoje, negociacoes);

		Assert.assertEquals(40.5, candlestick.getAbertura(), 0.00001);
		Assert.assertEquals(42.3, candlestick.getFechamento(), 0.00001);
		Assert.assertEquals(39.8, candlestick.getMinimo(), 0.00001);
		Assert.assertEquals(45.0, candlestick.getMaximo(), 0.00001);
		Assert.assertEquals(16760.0, candlestick.getVolume(), 0.00001);

	}

	@Test
	public void semNegociacoesGeraCandlestickcomZeros() {

		Calendar hoje = Calendar.getInstance();

		List<Negociacao> negociacoes = Arrays.asList();

		CandlestickFactory fabricaCandlestick = new CandlestickFactory();
		Candlestick candlestick = fabricaCandlestick.constroiCandleParaData(
				hoje, negociacoes);

		Assert.assertEquals(0.0, candlestick.getVolume(), 0.00001);
		Assert.assertEquals(0.0, candlestick.getAbertura(), 0.00001);
		Assert.assertEquals(0.0, candlestick.getFechamento(), 0.00001);
		Assert.assertEquals(0.0, candlestick.getMinimo(), 0.00001);
		Assert.assertEquals(0.0, candlestick.getMaximo(), 0.00001);

	}

	@Test
	public void apenasUmaNecogiacaoGeraCandlestickComValoresIguais() {

		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1);

		CandlestickFactory fabricaCandlestick = new CandlestickFactory();
		Candlestick candlestick = fabricaCandlestick.constroiCandleParaData(
				hoje, negociacoes);

		Assert.assertEquals(40.5, candlestick.getAbertura(), 0.00001);
		Assert.assertEquals(40.5, candlestick.getFechamento(), 0.00001);
		Assert.assertEquals(40.5, candlestick.getMinimo(), 0.00001);
		Assert.assertEquals(40.5, candlestick.getMaximo(), 0.00001);
		Assert.assertEquals(4050.0, candlestick.getVolume(), 0.00001);

	}

	@Test
	public void sequenciaCrescenteDeNegociacoes() {

		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(59.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(62.3, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
				negociacao3, negociacao4);

		CandlestickFactory fabricaCandlestick = new CandlestickFactory();
		Candlestick candlestick = fabricaCandlestick.constroiCandleParaData(
				hoje, negociacoes);

		Assert.assertEquals(40.5, candlestick.getAbertura(), 0.00001);
		Assert.assertEquals(62.3, candlestick.getFechamento(), 0.00001);
		Assert.assertEquals(40.5, candlestick.getMinimo(), 0.00001);
		Assert.assertEquals(62.3, candlestick.getMaximo(), 0.00001);
		Assert.assertEquals(20760.0, candlestick.getVolume(), 0.00001);

	}

	@Test
	public void sequenciaDecrescenteDeNegociacoes() {

		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(80.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(75.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(69.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(52.3, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
				negociacao3, negociacao4);

		CandlestickFactory fabricaCandlestick = new CandlestickFactory();
		Candlestick candlestick = fabricaCandlestick.constroiCandleParaData(
				hoje, negociacoes);

		Assert.assertEquals(80.5, candlestick.getAbertura(), 0.00001);
		Assert.assertEquals(52.3, candlestick.getFechamento(), 0.00001);
		Assert.assertEquals(52.3, candlestick.getMinimo(), 0.00001);
		Assert.assertEquals(80.5, candlestick.getMaximo(), 0.00001);
		Assert.assertEquals(27760.0, candlestick.getVolume(), 0.00001);

	}

	@Test
	public void dataDaNegociacaoEhImutavel() {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao n = new Negociacao(10, 15, c);

		n.getData().set(Calendar.DAY_OF_MONTH, 20);

		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));

	}

	@Test(expected = IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {

		Negociacao n = new Negociacao(10, 15, null);

	}

	@Test
	public void deveGerar3CandlesParaNegociacoesEm3DatasDistintas() {

		Calendar hoje = Calendar.getInstance();
		Calendar aniversarioPaulo = Calendar.getInstance();
		Calendar aniversarioPalmeiras = Calendar.getInstance();
		aniversarioPaulo.set(1987, 2, 13);
		aniversarioPalmeiras.set(1914, 7, 26);

		Negociacao negociacao1 = new Negociacao(20.5, 100, aniversarioPalmeiras);
		Negociacao negociacao2 = new Negociacao(10.0, 50, aniversarioPalmeiras);
		Negociacao negociacao3 = new Negociacao(12.8, 110, aniversarioPalmeiras);
		Negociacao negociacao4 = new Negociacao(33.3, 30, aniversarioPaulo);
		Negociacao negociacao5 = new Negociacao(42.3, 1050, hoje);
		Negociacao negociacao6 = new Negociacao(21.3, 1004, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
				negociacao3, negociacao4, negociacao5, negociacao6);

		CandlestickFactory fabrica = new CandlestickFactory();

		List<Candlestick> listaCandle = fabrica.criaCandles(negociacoes);

		Assert.assertEquals(3, listaCandle.size());
		Assert.assertEquals(20.5, listaCandle.get(0).getAbertura(), 0.00001);
		Assert.assertEquals(12.8, listaCandle.get(0).getFechamento(), 0.00001);
		Assert.assertEquals(33.3, listaCandle.get(1).getAbertura(), 0.00001);
		Assert.assertEquals(33.3, listaCandle.get(1).getFechamento(), 0.00001);
		Assert.assertEquals(42.3, listaCandle.get(2).getAbertura(), 0.00001);
		Assert.assertEquals(21.3, listaCandle.get(2).getFechamento(), 0.00001);
	}

	@Test
	public void testaSeAListaDeNegociacoesEstaOrdenada() {

		Calendar hoje = Calendar.getInstance();
		Calendar aniversarioPaulo = Calendar.getInstance();
		Calendar aniversarioPalmeiras = Calendar.getInstance();
		aniversarioPaulo.set(1987, 2, 13);
		aniversarioPalmeiras.set(1914, 7, 26);

		Negociacao negociacao1 = new Negociacao(33.3, 30, aniversarioPaulo);
		Negociacao negociacao2 = new Negociacao(20.5, 100, hoje);
		Negociacao negociacao3 = new Negociacao(10.0, 50, hoje);
		Negociacao negociacao4 = new Negociacao(21.3, 1004,
				aniversarioPalmeiras);
		Negociacao negociacao5 = new Negociacao(12.8, 110, hoje);
		Negociacao negociacao6 = new Negociacao(33.3, 30, aniversarioPaulo);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
				negociacao3, negociacao4, negociacao5, negociacao6);

		CandlestickFactory fabrica = new CandlestickFactory();

		List<Candlestick> listaCandle = fabrica.criaCandles(negociacoes);

		Assert.assertEquals(listaCandle.get(0).getData(), aniversarioPalmeiras);
		Assert.assertEquals(listaCandle.get(2).getData(), hoje);

	}

}
