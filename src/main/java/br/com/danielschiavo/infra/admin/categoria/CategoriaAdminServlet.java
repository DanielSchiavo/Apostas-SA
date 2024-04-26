package br.com.danielschiavo.infra.admin.categoria;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danielschiavo.aplicacao.admin.categoria.AlterarCategoria;
import br.com.danielschiavo.aplicacao.admin.categoria.AlterarCategoriaDTO;
import br.com.danielschiavo.aplicacao.admin.categoria.CadastrarCategoria;
import br.com.danielschiavo.aplicacao.admin.categoria.CadastrarCategoriaDTO;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.categoria.AlterarCategoriaException;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import br.com.danielschiavo.infra.util.GeradorUUIDImpl;
import br.com.danielschiavo.infra.util.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CategoriaAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private RepositorioDeCategoriaAdminComJdbcPostgres repositorio;
	
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		DataSource pool = (DataSource) getServletContext().getAttribute("my-pool");
            this.repositorio = new RepositorioDeCategoriaAdminComJdbcPostgres(pool.getConnection());
            super.service(request, response);
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new ServletException("Erro ao inicializar implementação do repositorio de usuario");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String requestBody = Util.pegarJsonCorpoDaRequisicao(req);
	    
	    try {
	    	String usuarioId = (String) req.getAttribute("usuarioId");
			CadastrarCategoria cadastrarCategoria = new CadastrarCategoria(repositorio, new GeradorUUIDImpl());
			CadastrarCategoriaDTO cadastrarCategoriaDTO = deserializarCadastrarCategoriaDTO(requestBody);
			
			String categoriaId = cadastrarCategoria.executa(cadastrarCategoriaDTO, usuarioId);
	    	
	    	StringBuffer urlAtual = req.getRequestURL();
	    	urlAtual.append("/" + categoriaId);
	    	
	    	resp.setHeader("Location", urlAtual.toString());
			resp.getWriter().write("Categoria cadastrada com sucesso!");
			resp.setStatus(HttpServletResponse.SC_CREATED);
			return;
		} catch (AutenticacaoException | ValidacaoException e) {
			e.printStackTrace();
			resp.getWriter().write(e.getMessage());
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestBody = Util.pegarJsonCorpoDaRequisicao(req);
	    
	    try {
	    	String usuarioId = (String) req.getAttribute("usuarioId");
	    	AlterarCategoria alterarCategoria = new AlterarCategoria(repositorio);
			AlterarCategoriaDTO alterarCategoriaDTO = deserializarAlterarCategoriaDTO(requestBody);
			
			alterarCategoria.executa(alterarCategoriaDTO, usuarioId);
	    	
			resp.getWriter().write("Categoria alterada com sucesso!");
			resp.setStatus(HttpServletResponse.SC_OK);
			return;
		} catch (AutenticacaoException | ValidacaoException | AlterarCategoriaException e) {
			e.printStackTrace();
			resp.getWriter().write(e.getMessage());
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
    }

	private AlterarCategoriaDTO deserializarAlterarCategoriaDTO(String requestBody) throws ValidacaoException {
		try {
		    ObjectMapper objectMapper = new ObjectMapper();
		    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    
			return objectMapper.readValue(requestBody, AlterarCategoriaDTO.class);
		} catch (JsonMappingException e) {
			throw new ValidacaoException(e.getCause().getMessage());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao processar json de resposta");
		}
	}

	private CadastrarCategoriaDTO deserializarCadastrarCategoriaDTO(String requestBody) throws ValidacaoException {
		try {
		    ObjectMapper objectMapper = new ObjectMapper();
		    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    
			return objectMapper.readValue(requestBody, CadastrarCategoriaDTO.class);
		} catch (JsonMappingException e) {
			throw new ValidacaoException(e.getCause().getMessage());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao processar json de resposta");
		}
	}
	
}
