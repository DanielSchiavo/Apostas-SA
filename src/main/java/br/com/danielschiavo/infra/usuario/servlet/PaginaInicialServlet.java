package br.com.danielschiavo.infra.usuario.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import br.com.danielschiavo.aplicacao.usuario.PegarDadosDoUsuarioPaginaInicial;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import br.com.danielschiavo.infra.security.TokenJWT;
import br.com.danielschiavo.infra.usuario.RepositorioDeUsuarioComJdbcPostgres;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PaginaInicialServlet extends HttpServlet {

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
		String stringToken = (String) req.getAttribute("token");
		
		try {
			PegarDadosDoUsuarioPaginaInicial pegarDadosPaginaInicial = new PegarDadosDoUsuarioPaginaInicial(repositorio, new TokenJWT());
			String json = pegarDadosPaginaInicial.executa(stringToken);
			resp.getWriter().write(json);
			resp.setStatus(HttpServletResponse.SC_OK);
			return;
		} catch (ValidacaoException | AutenticacaoException e) {
			resp.getWriter().write(e.getMessage());
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
}
