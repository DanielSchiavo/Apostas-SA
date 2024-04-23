package br.com.danielschiavo.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class TokenJWT implements Token {

	private String secret = "Segredo";

	@Override
	public String gerarToken(Usuario usuario) throws AutenticacaoException {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("API Shop")
		        .withSubject(usuario.getId().toString())
		        .withClaim("email", usuario.getEmail())
		        .withExpiresAt(expirationDate())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
			throw new AutenticacaoException("Erro ao gerar token de autenticacao");
		}
	}

	@Override
	public void verificarToken(String token) throws AutenticacaoException {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWT.require(algorithm)
					.withIssuer("API Shop")
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
	
	private DecodedJWT decodificarToken(String token) throws AutenticacaoException {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("API Shop")
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
