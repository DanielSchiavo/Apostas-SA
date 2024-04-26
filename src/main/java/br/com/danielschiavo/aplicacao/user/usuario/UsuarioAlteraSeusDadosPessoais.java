package br.com.danielschiavo.aplicacao.user.usuario;

import java.util.UUID;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.user.usuario.mapper.UsuarioMapper;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuarioUser;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AlterarUsuarioException;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;

public class UsuarioAlteraSeusDadosPessoais {

	private RepositorioDeUsuarioUser repositorioDeUsuario;
	
	private UsuarioMapper usuarioMapper;
	
	public UsuarioAlteraSeusDadosPessoais(RepositorioDeUsuarioUser repositorioDeUsuario) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
	}

	public void executa(UsuarioAlteraSeusDadosPessoaisDTO dadosQueSeraoAlterados, String usuarioId) throws AutenticacaoException, AlterarUsuarioException {
		boolean jaVerificou = repositorioDeUsuario.verificarSeUsuarioJaConfirmouIdentidade(usuarioId);
		if (jaVerificou && (dadosQueSeraoAlterados.getCpf() != null || dadosQueSeraoAlterados.getRg() != null || 
							dadosQueSeraoAlterados.getNome() != null || dadosQueSeraoAlterados.getSobrenome() != null || 
							dadosQueSeraoAlterados.getDataNascimento() != null)) {
			throw new AlterarUsuarioException("Você não pode alterar seu cpf, rg, nome, sobrenome, data de nascimento porque você já confirmou sua identidade");
		}
		
		Usuario usuario = usuarioMapper.formatarUsuarioAlteraSeusDadosPessoaisDTOParaUsuario(dadosQueSeraoAlterados);
		usuario.setId(UUID.fromString(usuarioId));
		
		try {
			repositorioDeUsuario.usuarioAlteraSeusDadosPessoais(usuario);
			repositorioDeUsuario.commitarTransacao();
		} catch (AlterarUsuarioException e) {
			e.printStackTrace();
			repositorioDeUsuario.rollbackTransacao();
			throw new AlterarUsuarioException(e.getMessage());
		}
	}
	
}
