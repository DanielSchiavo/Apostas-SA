package br.com.danielschiavo.infra.util.config;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

import lombok.Getter;
import lombok.Setter;


public class ConfigReader {

	@Getter
	@Setter
	private Config config;
	
	public ConfigReader() {
        InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("application.yml");
        Yaml yaml = new Yaml();
        PerfilAtivo perfilAtivo = yaml.loadAs(inputStream, PerfilAtivo.class);
        
        inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("application-" + perfilAtivo.getPerfilAtivo() + ".yml");
        yaml = new Yaml();
        Config config = yaml.loadAs(inputStream, Config.class);
        this.setConfig(config);
	}

}
