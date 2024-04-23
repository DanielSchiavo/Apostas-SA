package br.com.danielschiavo;

import org.flywaydb.core.Flyway;

import br.com.danielschiavo.infra.util.config.Config;
import br.com.danielschiavo.infra.util.config.ConfigReader;

public class Main {
	
	public static void main(String[] args) {
		
		Config config = new ConfigReader().getConfig();
				
		String url = config.getDatabase().getUrl();
		String username = config.getDatabase().getUsername();
		String password = config.getDatabase().getPassword();
		
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		
        Flyway flyway = Flyway.configure()
                .dataSource(url, username, password)
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();
//		
//		RepositorioDeUsuarioComJdbcPostgres repositorioDeUsuarioComJdbcPostgres = 
//					new RepositorioDeUsuarioComJdbcPostgres(new ConnectionFactoryPostgreSQL().getConexao());
//		CriptografiaSenhaBCryptService criptografiaSenhaBCryptService = new CriptografiaSenhaBCryptService();
//		GeradorUUIDImpl geradorUUIDImpl = new GeradorUUIDImpl();
//		
//		
//		CadastrarUsuario cadastrarUsuario = new CadastrarUsuario(repositorioDeUsuarioComJdbcPostgres, criptografiaSenhaBCryptService, geradorUUIDImpl);
//		CadastrarUsuarioDTO cadastrarUsuarioDTO = new CadastrarUsuarioDTO();
//		cadastrarUsuarioDTO.setCpf(new CPF("143.302.811-94"));
//		cadastrarUsuarioDTO.setNome("Daniel");
//		cadastrarUsuarioDTO.setSobrenome("Schiavo rosseto");
//		cadastrarUsuarioDTO.setEmail(new Email("daniel.schiavo35@gmail.com"));
//		cadastrarUsuarioDTO.setTelefone(new Telefone("27", "996101055"));
//		cadastrarUsuarioDTO.setSenha("123456");
//		cadastrarUsuarioDTO.setDataNascimento(LocalDate.now());
//		cadastrarUsuario.executa(cadastrarUsuarioDTO);
		
//		RepositorioDeUsuarioComJdbcPostgres repositorioDeUsuarioComJdbcPostgres2 = 
//				new RepositorioDeUsuarioComJdbcPostgres(new ConnectionFactoryPostgreSQL().getConexao());
//		
//		UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
//		PegarDadosDoUsuarioPaginaInicial pegarDadosDoUsuarioPaginaInicial = new PegarDadosDoUsuarioPaginaInicial(repositorioDeUsuarioComJdbcPostgres2, usuarioMapper);
//		UsuarioPaginaInicialDTO usuarioPaginaInicialDTO = pegarDadosDoUsuarioPaginaInicial.executa("a6c27397-1056-4b3f-90d3-b2ea4f76c1f4");
//		
//		System.out.println("Teste " + usuarioPaginaInicialDTO);
	}

}
