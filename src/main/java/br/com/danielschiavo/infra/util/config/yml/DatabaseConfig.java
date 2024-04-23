package br.com.danielschiavo.infra.util.config.yml;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DatabaseConfig {
	
    private String url;
    
    private String username;
    
    private String password;
    
    private String driver;

}