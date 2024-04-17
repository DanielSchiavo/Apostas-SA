package br.com.danielschiavo.infra;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactoryPostgreSQL {

	public Connection recuperarConexao() {
		try {
			return createDataSource().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private HikariDataSource createDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:postgresql://localhost:5432/apostas");
		config.setUsername("postgres");
		config.setPassword("postgres");
		config.setMaximumPoolSize(10);
		
		return new HikariDataSource(config);
	}
	
}
