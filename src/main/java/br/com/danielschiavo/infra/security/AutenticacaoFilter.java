package br.com.danielschiavo.infra.security;

import java.io.IOException;

import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(servletNames = {
					"AlterarSenhaServlet",
					"DadosPessoaisServlet", 
					"PaginaInicialServlet", 
					"PerfilJogadorServlet"})
public class AutenticacaoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletReq, ServletResponse servletResp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletReq;
		HttpServletResponse resp = (HttpServletResponse) servletResp;
		
		Cookie[] cookies = req.getCookies();
		boolean tokenEncontrado = false;
		
		if (cookies != null) {
			for(Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) {
					try {
						String stringToken = cookie.getValue();
						new TokenJWT().verificarToken(stringToken);
						req.setAttribute("token", stringToken);
					} catch (AutenticacaoException e) {
						resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						resp.getWriter().write(e.getMessage());
						removerCookie(resp);
						return;
					}
					tokenEncontrado = true;
					break;
				}
			}
		}
		
		if (!tokenEncontrado) {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			resp.getWriter().write("Você precisa estar autenticado para acessar essa página");
			return;
		}
		
		chain.doFilter(servletReq, servletResp);
		
	}

	private void removerCookie(HttpServletResponse resp) {
		Cookie cookie = new Cookie("token", "");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
	}

}
