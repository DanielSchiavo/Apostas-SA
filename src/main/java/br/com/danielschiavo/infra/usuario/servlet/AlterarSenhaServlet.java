package br.com.danielschiavo.infra.usuario.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danielschiavo.aplicacao.usuario.UsuarioAlteraSuaSenha;
import br.com.danielschiavo.aplicacao.usuario.UsuarioAlteraSuaSenhaDTO;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.exceptions.AlterarUsuarioException;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import br.com.danielschiavo.infra.security.CriptografiaSenhaComBCrypt;
import br.com.danielschiavo.infra.security.TokenJWT;
import br.com.danielschiavo.infra.usuario.RepositorioDeUsuarioComJdbcPostgres;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AlterarSenhaServlet extends HttpServlet {

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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
	    StringBuilder requestBody = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        requestBody.append(line);
	    }
    	
    	try {
    		String stringToken = (String) req.getAttribute("token");
    		UsuarioAlteraSuaSenhaDTO usuarioAlteraSuaSenhaDTO = deserializarUsuarioAlteraSuaSenhaDTO(requestBody.toString());
    		
    		UsuarioAlteraSuaSenha usuarioAlteraSuaSenha = new UsuarioAlteraSuaSenha(repositorio, new CriptografiaSenhaComBCrypt(), new TokenJWT());
    		usuarioAlteraSuaSenha.executa(usuarioAlteraSuaSenhaDTO, stringToken);
    		
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().write("Senha alterada com sucesso!");
		} catch (AlterarUsuarioException | AutenticacaoException | ValidacaoException e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write(e.getMessage());
			return;
		}
    }

	private UsuarioAlteraSuaSenhaDTO deserializarUsuarioAlteraSuaSenhaDTO(String requestBody) {
		try {
		    ObjectMapper objectMapper = new ObjectMapper();
		    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    
			return objectMapper.readValue(requestBody, UsuarioAlteraSuaSenhaDTO.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao mapear json de resposta");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao processar json de resposta");
		}
	}
}
