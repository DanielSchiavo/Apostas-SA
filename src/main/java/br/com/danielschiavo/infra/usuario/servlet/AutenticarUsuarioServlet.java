package br.com.danielschiavo.infra.usuario.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.danielschiavo.aplicacao.usuario.AutenticarUsuario;
import br.com.danielschiavo.aplicacao.usuario.AutenticarUsuarioDTO;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import br.com.danielschiavo.infra.security.CriptografiaSenhaComBCrypt;
import br.com.danielschiavo.infra.security.TokenJWT;
import br.com.danielschiavo.infra.usuario.RepositorioDeUsuarioComJdbcPostgres;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AutenticarUsuarioServlet extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
	    StringBuilder requestBody = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        requestBody.append(line);
	    }
		
		try {
			AutenticarUsuario autenticarUsuario = new AutenticarUsuario(repositorio, new CriptografiaSenhaComBCrypt(), new TokenJWT());
			AutenticarUsuarioDTO autenticarUsuarioDTO = deserializarAutenticarUsuarioDTO(requestBody.toString());
			String tokenGerado = autenticarUsuario.executa(autenticarUsuarioDTO);
			
	        Map<String, String> jsonMap = new HashMap<>();
	        jsonMap.put("token", tokenGerado);
	        
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(jsonMap);
			
			resp.getWriter().write(json);
			resp.setStatus(HttpServletResponse.SC_OK);
			
			Cookie cookie = new Cookie("token", tokenGerado);
			int dezDiasEmSegundos = 86400 * 5; // 86400 segundos em um dia
			cookie.setMaxAge(dezDiasEmSegundos);
			resp.addCookie(cookie);
		} catch (AutenticacaoException | ValidacaoException e) {
			resp.getWriter().write(e.getMessage());
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao gerar json de resposta");
		}
	}

	private AutenticarUsuarioDTO deserializarAutenticarUsuarioDTO(String requestBody) throws ValidacaoException {
		try {
		    ObjectMapper objectMapper = new ObjectMapper();
		    objectMapper.registerModule(new JavaTimeModule());
	    
			return objectMapper.readValue(requestBody, AutenticarUsuarioDTO.class);
		} catch (JsonMappingException e) {
			throw new ValidacaoException(e.getCause().getMessage());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao processar json de resposta");
		}
	}
}
