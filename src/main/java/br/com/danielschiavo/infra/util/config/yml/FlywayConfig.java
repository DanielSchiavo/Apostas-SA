package br.com.danielschiavo.infra.util.config.yml;

public class FlywayConfig {
	private String locations;
	
	public FlywayConfig() {}

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}
}
