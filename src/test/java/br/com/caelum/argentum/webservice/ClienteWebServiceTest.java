package br.com.caelum.argentum.webservice;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class ClienteWebServiceTest {

	private static final String URL_WEBSERVICE = "http://argentumws.caelum.com.br/negociacoes";

	@Test
	public void testaSeRetornaUmaListaDeNegociacoes() {

		List<Negociacao> lista = new ClienteWebService().getNecociacoes();

		Assert.assertEquals(248, lista.size());

	}
}
