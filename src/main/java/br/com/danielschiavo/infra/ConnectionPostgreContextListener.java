package br.com.danielschiavo.infra;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ConnectionPostgreContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		DataSource pool = (DataSource) servletContext.getAttribute("my-pool");

		ConnectionFactoryPostgreSQL.initialize();
		pool = ConnectionFactoryPostgreSQL.getConnectionPool();
		servletContext.setAttribute("my-pool", pool);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		HikariDataSource pool = (HikariDataSource) event.getServletContext().getAttribute("my-pool");
		if (pool != null) {
			pool.close();
		}
	}
}