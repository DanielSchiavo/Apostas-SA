package br.com.danielschiavo.dominio.usuario;

public interface RepositorioDeUsuario {
	
	void cadastrarUsuario(Usuario usuario);
	
	Usuario buscarPorCPF(CPF cpf);

}
