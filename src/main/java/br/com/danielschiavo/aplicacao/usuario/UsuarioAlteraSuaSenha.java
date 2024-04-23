package br.com.danielschiavo.aplicacao.usuario;

import java.util.Set;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.CriptografiaSenha;
import br.com.danielschiavo.aplicacao.Token;
import br.com.danielschiavo.aplicacao.usuario.mapper.UsuarioMapper;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.Email;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.exceptions.AlterarUsuarioException;
import br.com.danielschiavo.dominio.usuario.exceptions.AutenticacaoException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

public class UsuarioAlteraSuaSenha {

	private RepositorioDeUsuario repositorioDeUsuario;
	
	private CriptografiaSenha criptografiaSenha;
	
	private Token token;
	
	private UsuarioMapper usuarioMapper;
	
	public UsuarioAlteraSuaSenha(RepositorioDeUsuario repositorioDeUsuario, CriptografiaSenha criptografiaSenha, Token token) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.criptografiaSenha = criptografiaSenha;
		this.token = token;
		this.usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
	}

	public void executa(UsuarioAlteraSuaSenhaDTO usuarioAlteraSuaSenhaDTO, String stringToken) throws AutenticacaoException, AlterarUsuarioException, ValidacaoException {
		Set<ConstraintViolation<UsuarioAlteraSuaSenhaDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(usuarioAlteraSuaSenhaDTO);
        
        for (ConstraintViolation<UsuarioAlteraSuaSenhaDTO> violation : violations) {
        	throw new ValidacaoException(violation.getMessage());
        }
		
		String emailUsuario = token.getClaimEmail(stringToken);
		
		Usuario usuario = usuarioMapper.formatarUsuarioAlteraSuaSenhaDTOParaUsuario(usuarioAlteraSuaSenhaDTO);
		usuario.setEmail(new Email(emailUsuario));
		try {
			repositorioDeUsuario.comecarTransacao();
			Usuario usuarioAutenticado = repositorioDeUsuario.autenticarUsuario(usuario);
			
			criptografiaSenha.verificarSenha(usuarioAutenticado.getSenha(), usuarioAlteraSuaSenhaDTO.getSenhaAtual());
			String senhaNovaCriptografada = criptografiaSenha.criptografarSenha(usuarioAlteraSuaSenhaDTO.getSenhaNova());
			usuario.setSenha(senhaNovaCriptografada);
			repositorioDeUsuario.usuarioAlteraSuaSenha(usuario);
			repositorioDeUsuario.commitarTransacao();
		} catch (AlterarUsuarioException e) {
			repositorioDeUsuario.rollbackTransacao();
			e.printStackTrace();
			throw new AlterarUsuarioException(e.getMessage());
		} catch (AutenticacaoException e) {
			e.printStackTrace();
			repositorioDeUsuario.rollbackTransacao();
			throw new AutenticacaoException(e.getMessage());
		} catch (ValidacaoException e) {
			e.printStackTrace();
			repositorioDeUsuario.rollbackTransacao();
			throw new ValidacaoException(e.getMessage());
		}
	}
	
}
