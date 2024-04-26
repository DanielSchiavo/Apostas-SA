package br.com.danielschiavo.dominio.usuario;

import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.exceptions.AlterarUsuarioException;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import br.com.danielschiavo.dominio.usuario.exceptions.UsuarioNaoEncontradoException;
import br.com.danielschiavo.dominio.usuario.perfiljogador.PerfilJogador;

public interface RepositorioDeUsuarioUser {
	
	Usuario autenticarUsuario(Usuario usuario) throws AutenticacaoException, ValidacaoException ;
	
	
	
	void cadastrarUsuario(Usuario usuario) throws ValidacaoException;
	
	
	
	Usuario pegarDadosDoUsuarioPaginaInicial(String usuarioId) throws ValidacaoException;
	
	PerfilJogador pegarDadosDoUsuarioPerfilDeJogador(String usuarioId);
	
	Usuario pegarDadosDoUsuarioPessoais(String usuarioId) throws ValidacaoException;
	
	
	
	void usuarioAlteraSeusDadosPessoais(Usuario usuario) throws AlterarUsuarioException;
	
	void usuarioAlteraSeuPerfilDeJogador(Usuario usuario) throws AlterarUsuarioException;
	
	void usuarioAlteraSuaSenha(Usuario usuario) throws AlterarUsuarioException;
	
	
	
	boolean verificarSeUsuarioJaConfirmouIdentidade(String usuarioId) throws UsuarioNaoEncontradoException;
	
	
	
    void commitarTransacao();
    
    void rollbackTransacao();
	
}
