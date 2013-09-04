package br.com.caelum.argentum.modelo;

import java.util.Calendar;

public class CandlestickBuilder {

	private double abertura;
	private double fechamento;
	private double minimo;
	private double maximo;
	private double volume;
	private Calendar data;

	public CandlestickBuilder comAbertura(double abertura) {
		this.abertura = abertura;
		return this;
	}

	public CandlestickBuilder comFechamento(double fechamento) {
		this.fechamento = fechamento;
		return this;
	}

	public CandlestickBuilder comMinimo(double minimo) {
		this.minimo = minimo;
		return this;
	}

	public CandlestickBuilder comMaximo(double maximo) {
		this.maximo = maximo;
		return this;
	}

	public CandlestickBuilder comVolume(double volume) {
		this.volume = volume;
		return this;
	}

	public CandlestickBuilder comData(Calendar data) {
		this.data = data;
		return this;
	}

	public Candlestick geraCandlestick() {
		return new Candlestick(abertura, fechamento, minimo, maximo, volume,
				data);
	}

}
