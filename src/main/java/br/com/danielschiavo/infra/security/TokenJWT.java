package br.com.danielschiavo.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.dominio.usuario.Role;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class TokenJWT implements Token {

	private String secret = "Segredo";
	
	private String issuer = "Apostas";
	
	private Algorithm algorithm = Algorithm.HMAC256(secret);

	@Override
	public String gerarToken(Usuario usuario) throws AutenticacaoException {
		try {
			String role = null;
			if (usuario.getRoles().size() == 1) {
				Role roleObj = usuario.getRoles().stream().findFirst().get();
				
				role = roleObj.getRole().toString();
			}
			
		    String token = JWT.create()
						        .withIssuer(issuer)
						        .withSubject(usuario.getId().toString())
						        .withClaim("email", usuario.getEmail())
						        .withClaim("role", role)
						        .withExpiresAt(expirationDate())
						        .sign(algorithm);
		    
		     return token;
		        
		} catch (JWTCreationException exception){
			exception.printStackTrace();
			throw new AutenticacaoException("Erro ao gerar token de autenticacao");
		} 
	}

	@Override
	public void verificarToken(String token) throws AutenticacaoException {
		try {
			JWT.require(algorithm)
					.withIssuer(issuer)
					.build()
					.verify(token);
		} catch (JWTVerificationException exception){
			throw new AutenticacaoException("Token inválido ou expirado!");
		}
	}

	@Override
	public String getClaimEmail(String token) throws AutenticacaoException {
		return decodificarToken(token).getClaim("email").asString();
	}

	@Override
	public String getSubject(String token) throws AutenticacaoException {
		return decodificarToken(token).getSubject();
	}
	
	@Override
	public String getRoles(String token) throws AutenticacaoException {
		return decodificarToken(token).getClaim("role").asString();
	}
	
	@Override
	public Map<String, String> pegarTodosAtributos(String token) throws AutenticacaoException {
		DecodedJWT decodedJWT = decodificarToken(token);
		
		Map<String, String> map = new HashMap<>();
		
		String roles = decodedJWT.getClaim("role").toString().replace("\"", "");
		map.put("role", roles);
		String email = decodedJWT.getClaim("email").toString().replace("\"", "");
		map.put("email", email);
        String subject = decodedJWT.getSubject();
        map.put("subject", subject);
		
		return map;
	}
	
	private DecodedJWT decodificarToken(String token) throws AutenticacaoException {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer(issuer)
					.build()
					.verify(token);
		} catch (JWTVerificationException exception){
			exception.printStackTrace();
			throw new AutenticacaoException("Token inválido ou expirado!");
		}
	}
	
	private Instant expirationDate() {
		return LocalDateTime.now().plusDays(10).toInstant(ZoneOffset.of("-03:00"));
	}


}
