package br.com.danielschiavo.infra.security;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(servletNames = {
						//USUARIO
						"AlterarSenhaServlet",
						"DadosPessoaisServlet", 
						"PaginaInicialServlet", 
						"PerfilJogadorServlet",
						
						//CATEGORIA
						"CategoriaServlet",
						
						//SUB - CATEGORIA
						"SubCategoriaServlet"})
public class MonitoramentoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		long inicio = System.currentTimeMillis();
		
		chain.doFilter(req, resp);
		
		long fim = System.currentTimeMillis();
		
		System.out.println("Tempo de execução: " + (fim - inicio) + " ms");
	}

}
