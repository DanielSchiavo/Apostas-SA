package br.com.danielschiavo.aplicacao.usuario;

import java.util.UUID;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.aplicacao.usuario.mapper.UsuarioMapper;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AlterarUsuarioException;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class UsuarioAlteraSeusDadosPessoais {

	private RepositorioDeUsuario repositorioDeUsuario;
	
	private UsuarioMapper usuarioMapper;
	
	private Token token;
	
	public UsuarioAlteraSeusDadosPessoais(RepositorioDeUsuario repositorioDeUsuario, Token token) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.token = token;
		this.usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
	}

	public void executa(UsuarioAlteraSeusDadosPessoaisDTO dadosQueSeraoAlterados, String stringToken) throws AlterarUsuarioException, AutenticacaoException {
		String usuarioId = token.getSubject(stringToken);
		
		repositorioDeUsuario.comecarTransacao();
		boolean jaVerificou = repositorioDeUsuario.verificarSeUsuarioJaConfirmouIdentidade(usuarioId);
		if (jaVerificou && (dadosQueSeraoAlterados.getCpf() != null || dadosQueSeraoAlterados.getRg() != null || 
							dadosQueSeraoAlterados.getNome() != null || dadosQueSeraoAlterados.getSobrenome() != null || 
							dadosQueSeraoAlterados.getDataNascimento() != null)) {
			repositorioDeUsuario.rollbackTransacao();
			throw new AlterarUsuarioException("Você não pode alterar seu cpf, rg, nome, sobrenome, data de nascimento porque você já confirmou sua identidade");
		}
		
		Usuario usuario = usuarioMapper.formatarUsuarioAlteraSeusDadosPessoaisDTOParaUsuario(dadosQueSeraoAlterados);
		usuario.setId(UUID.fromString(usuarioId));
		
		repositorioDeUsuario.usuarioAlteraSeusDadosPessoais(usuario);
		repositorioDeUsuario.commitarTransacao();
	}
	
}
