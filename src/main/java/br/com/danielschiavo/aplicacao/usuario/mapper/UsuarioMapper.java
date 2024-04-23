package br.com.danielschiavo.aplicacao.usuario.mapper;


import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import br.com.danielschiavo.aplicacao.usuario.AutenticarUsuarioDTO;
import br.com.danielschiavo.aplicacao.usuario.CadastrarUsuarioDTO;
import br.com.danielschiavo.aplicacao.usuario.UsuarioAlteraSeuPerfilDeJogadorDTO;
import br.com.danielschiavo.aplicacao.usuario.UsuarioAlteraSeusDadosPessoaisDTO;
import br.com.danielschiavo.aplicacao.usuario.UsuarioAlteraSuaSenhaDTO;
import br.com.danielschiavo.aplicacao.usuario.UsuarioPaginaInicialDTO;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.perfiljogador.PerfilJogador;

@Mapper(builder = @Builder(disableBuilder = true), unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
	
	@Mapping(source = "perfilJogador.foto", target = "foto")
	UsuarioPaginaInicialDTO formatarUsuarioParaUsuarioPaginaInicialDTO(Usuario usuario);
	
	Usuario formatarAutenticarUsuarioDTOParaUsuario(AutenticarUsuarioDTO autenticarUsuarioDTO);
	
	Usuario formatarCadastrarUsuarioDTOParaUsuario(CadastrarUsuarioDTO cadastrarUsuarioDTO);
	
	PerfilJogador formatarUsuarioAlteraSeuPerfilDeJogadorDTOParaUsuario(UsuarioAlteraSeuPerfilDeJogadorDTO usuarioAlteraSeuPerfilDeJogadorDTO);
	
	Usuario formatarUsuarioAlteraSeusDadosPessoaisDTOParaUsuario(UsuarioAlteraSeusDadosPessoaisDTO usuarioAlteraSeusDadosPessoaisDTO);
	
	@Mapping(source = "senhaAtual", target = "senha")
	Usuario formatarUsuarioAlteraSuaSenhaDTOParaUsuario(UsuarioAlteraSuaSenhaDTO usuarioAlteraSuaSenhaDTO);
	
}
