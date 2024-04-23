package br.com.danielschiavo.aplicacao.usuario.mapper;

import br.com.danielschiavo.aplicacao.usuario.AutenticarUsuarioDTO;
import br.com.danielschiavo.aplicacao.usuario.CadastrarUsuarioDTO;
import br.com.danielschiavo.aplicacao.usuario.UsuarioAlteraSeuPerfilDeJogadorDTO;
import br.com.danielschiavo.aplicacao.usuario.UsuarioAlteraSeusDadosPessoaisDTO;
import br.com.danielschiavo.aplicacao.usuario.UsuarioAlteraSuaSenhaDTO;
import br.com.danielschiavo.aplicacao.usuario.UsuarioPaginaInicialDTO;
import br.com.danielschiavo.dominio.usuario.Usuario;
import br.com.danielschiavo.dominio.usuario.perfiljogador.PerfilJogador;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-23T18:05:31-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.3 (Azul Systems, Inc.)"
)
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioPaginaInicialDTO formatarUsuarioParaUsuarioPaginaInicialDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioPaginaInicialDTO usuarioPaginaInicialDTO = new UsuarioPaginaInicialDTO();

        usuarioPaginaInicialDTO.setFoto( usuarioPerfilJogadorFoto( usuario ) );
        usuarioPaginaInicialDTO.setNome( usuario.getNome() );
        if ( usuario.getSaldo() != null ) {
            usuarioPaginaInicialDTO.setSaldo( usuario.getSaldo().toString() );
        }

        return usuarioPaginaInicialDTO;
    }

    @Override
    public Usuario formatarAutenticarUsuarioDTOParaUsuario(AutenticarUsuarioDTO autenticarUsuarioDTO) {
        if ( autenticarUsuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setEmail( autenticarUsuarioDTO.getEmail() );
        usuario.setSenha( autenticarUsuarioDTO.getSenha() );

        return usuario;
    }

    @Override
    public Usuario formatarCadastrarUsuarioDTOParaUsuario(CadastrarUsuarioDTO cadastrarUsuarioDTO) {
        if ( cadastrarUsuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setCelular( cadastrarUsuarioDTO.getCelular() );
        usuario.setCpf( cadastrarUsuarioDTO.getCpf() );
        usuario.setDataCriacaoConta( cadastrarUsuarioDTO.getDataCriacaoConta() );
        usuario.setDataNascimento( cadastrarUsuarioDTO.getDataNascimento() );
        usuario.setEmail( cadastrarUsuarioDTO.getEmail() );
        usuario.setId( cadastrarUsuarioDTO.getId() );
        usuario.setNome( cadastrarUsuarioDTO.getNome() );
        usuario.setSaldo( cadastrarUsuarioDTO.getSaldo() );
        usuario.setSenha( cadastrarUsuarioDTO.getSenha() );
        usuario.setSobrenome( cadastrarUsuarioDTO.getSobrenome() );

        return usuario;
    }

    @Override
    public PerfilJogador formatarUsuarioAlteraSeuPerfilDeJogadorDTOParaUsuario(UsuarioAlteraSeuPerfilDeJogadorDTO usuarioAlteraSeuPerfilDeJogadorDTO) {
        if ( usuarioAlteraSeuPerfilDeJogadorDTO == null ) {
            return null;
        }

        PerfilJogador perfilJogador = new PerfilJogador();

        perfilJogador.setFacebook( usuarioAlteraSeuPerfilDeJogadorDTO.getFacebook() );
        perfilJogador.setFoto( usuarioAlteraSeuPerfilDeJogadorDTO.getFoto() );
        perfilJogador.setFrase( usuarioAlteraSeuPerfilDeJogadorDTO.getFrase() );
        perfilJogador.setInstagram( usuarioAlteraSeuPerfilDeJogadorDTO.getInstagram() );
        perfilJogador.setTwitter( usuarioAlteraSeuPerfilDeJogadorDTO.getTwitter() );

        return perfilJogador;
    }

    @Override
    public Usuario formatarUsuarioAlteraSeusDadosPessoaisDTOParaUsuario(UsuarioAlteraSeusDadosPessoaisDTO usuarioAlteraSeusDadosPessoaisDTO) {
        if ( usuarioAlteraSeusDadosPessoaisDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setCelular( usuarioAlteraSeusDadosPessoaisDTO.getCelular() );
        usuario.setCpf( usuarioAlteraSeusDadosPessoaisDTO.getCpf() );
        usuario.setDataNascimento( usuarioAlteraSeusDadosPessoaisDTO.getDataNascimento() );
        usuario.setEmail( usuarioAlteraSeusDadosPessoaisDTO.getEmail() );
        usuario.setNome( usuarioAlteraSeusDadosPessoaisDTO.getNome() );
        usuario.setRg( usuarioAlteraSeusDadosPessoaisDTO.getRg() );
        usuario.setSobrenome( usuarioAlteraSeusDadosPessoaisDTO.getSobrenome() );

        return usuario;
    }

    @Override
    public Usuario formatarUsuarioAlteraSuaSenhaDTOParaUsuario(UsuarioAlteraSuaSenhaDTO usuarioAlteraSuaSenhaDTO) {
        if ( usuarioAlteraSuaSenhaDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setSenha( usuarioAlteraSuaSenhaDTO.getSenhaAtual() );

        return usuario;
    }

    private String usuarioPerfilJogadorFoto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }
        PerfilJogador perfilJogador = usuario.getPerfilJogador();
        if ( perfilJogador == null ) {
            return null;
        }
        String foto = perfilJogador.getFoto();
        if ( foto == null ) {
            return null;
        }
        return foto;
    }
}
