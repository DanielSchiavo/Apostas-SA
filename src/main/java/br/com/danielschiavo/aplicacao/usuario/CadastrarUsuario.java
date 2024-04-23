package br.com.danielschiavo.aplicacao.usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.mapstruct.factory.Mappers;

import br.com.danielschiavo.aplicacao.CriptografiaSenha;
import br.com.danielschiavo.aplicacao.usuario.mapper.UsuarioMapper;
import br.com.danielschiavo.dominio.GeradorUUID;
import br.com.danielschiavo.dominio.ValidacaoException;
import br.com.danielschiavo.dominio.usuario.RepositorioDeUsuario;
import br.com.danielschiavo.dominio.usuario.Usuario;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

public class CadastrarUsuario {
	
	private RepositorioDeUsuario repositorioDeUsuario;
	
	private CriptografiaSenha criptografiaSenha;
	
	private GeradorUUID geradorUuid;
	
	private UsuarioMapper usuarioMapper;
	
	public CadastrarUsuario(RepositorioDeUsuario repositorioDeUsuario, CriptografiaSenha criptografiaSenha, GeradorUUID geradorUuid) {
		this.repositorioDeUsuario = repositorioDeUsuario;
		this.criptografiaSenha = criptografiaSenha;
		this.geradorUuid = geradorUuid;
		this.usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
	}

	public String executa(CadastrarUsuarioDTO cadastrarUsuarioDTO) throws ValidacaoException {
		Set<ConstraintViolation<CadastrarUsuarioDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(cadastrarUsuarioDTO);
        
        for (ConstraintViolation<CadastrarUsuarioDTO> violation : violations) {
        	throw new ValidacaoException(violation.getMessage());
        }
		
		String uuidString = geradorUuid.gerarUUID().toString();
		cadastrarUsuarioDTO.setId(UUID.fromString(uuidString));
		
		String senhaCriptografada = criptografiaSenha.criptografarSenha(cadastrarUsuarioDTO.getSenha());
		cadastrarUsuarioDTO.setSenha(senhaCriptografada);
		
		cadastrarUsuarioDTO.setDataCriacaoConta(LocalDateTime.now());
		cadastrarUsuarioDTO.setSaldo(BigDecimal.ZERO);
		
		Usuario usuario = usuarioMapper.formatarCadastrarUsuarioDTOParaUsuario(cadastrarUsuarioDTO);
		
		repositorioDeUsuario.cadastrarUsuario(usuario);
		
		return "Cadastrado com sucesso!";
	}
	
}
