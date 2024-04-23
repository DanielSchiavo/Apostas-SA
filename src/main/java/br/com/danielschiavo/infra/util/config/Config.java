package br.com.danielschiavo.infra.util.config;

import br.com.danielschiavo.infra.util.config.yml.DatabaseConfig;
import br.com.danielschiavo.infra.util.config.yml.FlywayConfig;

public class Config {
	
    private DatabaseConfig database;
    
    private FlywayConfig flyway;

    public DatabaseConfig getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseConfig database) {
        this.database = database;
    }

	public FlywayConfig getFlyway() {
		return flyway;
	}

	public void setFlyway(FlywayConfig flyway) {
		this.flyway = flyway;
	}
}