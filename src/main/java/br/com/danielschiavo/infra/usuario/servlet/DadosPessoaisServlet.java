package br.com.danielschiavo.infra.usuario.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.aplicacao.usuario.PegarDadosDoUsuarioPessoais;
import br.com.danielschiavo.aplicacao.usuario.UsuarioAlteraSeusDadosPessoais;
import br.com.danielschiavo.aplicacao.usuario.UsuarioAlteraSeusDadosPessoaisDTO;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.exceptions.AlterarUsuarioException;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import br.com.danielschiavo.infra.security.TokenJWT;
import br.com.danielschiavo.infra.usuario.RepositorioDeUsuarioComJdbcPostgres;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DadosPessoaisServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    private RepositorioDeUsuarioComJdbcPostgres repositorio;
 
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		DataSource pool = (DataSource) getServletContext().getAttribute("my-pool");
            this.repositorio = new RepositorioDeUsuarioComJdbcPostgres(pool.getConnection());
            super.service(request, response);
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new ServletException("Erro ao inicializar implementação do repositorio de usuario");
        }
    }
    
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Token token = new TokenJWT();
		PegarDadosDoUsuarioPessoais pegarDadosPessoais = new PegarDadosDoUsuarioPessoais(repositorio, token);
		try {
			String stringToken = (String) req.getAttribute("token");
			String json = pegarDadosPessoais.executa(stringToken);
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().write(json);
			return;
		} catch (AutenticacaoException | ValidacaoException e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write(e.getMessage());
			return;
		}
	}
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
	    StringBuilder requestBody = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        requestBody.append(line);
	    }
    	
    	Token token = new TokenJWT();
    	try {
    		String stringToken = (String) req.getAttribute("token");
    		UsuarioAlteraSeusDadosPessoaisDTO usuarioAlteraSeusDadosPessoaisDTO = deserializarUsuarioAlteraSeusDadosPessoaisDTO(requestBody.toString());
    		
    		UsuarioAlteraSeusDadosPessoais usuarioAlteraSeusDadosPessoais = new UsuarioAlteraSeusDadosPessoais(repositorio, token);
    		usuarioAlteraSeusDadosPessoais.executa(usuarioAlteraSeusDadosPessoaisDTO, stringToken);
    		
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().write("Dados pessoais alterado com sucesso!");
		} catch (ValidacaoException | AlterarUsuarioException | AutenticacaoException e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write(e.getMessage());
			return;
		}
    }

	private UsuarioAlteraSeusDadosPessoaisDTO deserializarUsuarioAlteraSeusDadosPessoaisDTO(String requestBody) throws ValidacaoException {
		try {
		    ObjectMapper objectMapper = new ObjectMapper();
		    objectMapper.registerModule(new JavaTimeModule());
		    objectMapper.configOverride(LocalDate.class).setFormat(JsonFormat.Value.forPattern("dd/MM/yyyy"));
		    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    
			return objectMapper.readValue(requestBody, UsuarioAlteraSeusDadosPessoaisDTO.class);
		} catch (JsonMappingException e) {
			throw new ValidacaoException(e.getCause().getMessage());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao processar json de resposta");
		}
	}
}
