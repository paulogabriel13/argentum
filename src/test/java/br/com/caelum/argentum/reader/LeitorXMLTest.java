package br.com.caelum.argentum.reader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria(){
		String xmlDeTeste = "<list>" +
							"	<negociacao>" +
							"		<preco>43.5</preco>"+
							"		<quantidade>1000</quantidade>" +
							"		<data>" +
							"			<time>1322233344455</time>"+
							"		</data>" +
							"	</negociacao>" +
							"</list>";		
		
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		Assert.assertEquals(1 ,negociacoes.size());
		Assert.assertEquals(43.5, negociacoes.get(0).getPreco());
		Assert.assertEquals(1000, negociacoes.get(0).getQuantidade());
	}
	
	@Test
	public void deveRetornar3NegociacoesQuandoListaDeNegociacoesTiver3Elementos(){
		String xmlDeTeste = "<list>" +
							"	<negociacao>" +
							"		<preco>43.5</preco>"+
							"		<quantidade>1000</quantidade>" +
							"		<data>" +
							"			<time>1322233344455</time>"+
							"		</data>" +
							"	</negociacao>" +
							"	<negociacao>" +
							"		<preco>50.5</preco>"+
							"		<quantidade>320</quantidade>" +
							"		<data>" +
							"			<time>1322233344455</time>"+
							"		</data>" +
							"	</negociacao>" +
							"	<negociacao>" +
							"		<preco>60.5</preco>"+
							"		<quantidade>500</quantidade>" +
							"		<data>" +
							"			<time>1322233344455</time>"+
							"		</data>" +
							"	</negociacao>" +
							"</list>";		
		
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		Assert.assertEquals(3 ,negociacoes.size());		
	}
	
	@Test
	public void deveRetornarZeroSeNaoHouverNegociacoes(){
		String xmlDeTeste = "<list>" +							
							"</list>";		
		
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		Assert.assertEquals(0 ,negociacoes.size());
		
	}
	
	@Test
	public void deveRetornarPreco0SeNaoHouverPrecoCadastrado(){
		String xmlDeTeste = "<list>" +
							"	<negociacao>" +							
							"		<quantidade>1000</quantidade>" +
							"		<data>" +
							"			<time>1322233344455</time>"+
							"		</data>" +
							"	</negociacao>" +
							"</list>";		
		
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		Assert.assertEquals(1 ,negociacoes.size());
		Assert.assertEquals(0.0, negociacoes.get(0).getPreco());
		
	}
	
	@Test
	public void deveRetornarQuantidade0SeNaoHouverQuantidadeCadastrada(){
		String xmlDeTeste = "<list>" +
							"	<negociacao>" +	
							"		<preco>50.5</preco>"+
							"		<data>" +
							"			<time>1322233344455</time>"+
							"		</data>" +
							"	</negociacao>" +
							"</list>";		
		
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		Assert.assertEquals(1 ,negociacoes.size());
		Assert.assertEquals(0, negociacoes.get(0).getQuantidade());
		
	}
}
