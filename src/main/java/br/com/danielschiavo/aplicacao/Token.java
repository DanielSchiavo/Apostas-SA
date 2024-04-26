package br.com.danielschiavo.aplicacao;

import java.util.Map;

import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public interface Token {
	
	String gerarToken(Usuario usuario) throws AutenticacaoException;

	void verificarToken(String token) throws AutenticacaoException;
	
	String getClaimEmail(String token) throws AutenticacaoException;
	
	String getSubject(String token) throws AutenticacaoException;

	String getRoles(String token) throws AutenticacaoException;

	Map<String, String> pegarTodosAtributos(String token) throws AutenticacaoException;
}
