import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * https://github.com/brettwooldridge/HikariCP
 */

public class HikariCpDemo {

	public static void main(String[] args) {
		DataSource ds = getDbDataSource();

		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT ?;");
			ps.setInt(1, 12);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("The result:" + rs.getInt(1));
			}
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	private static DataSource getDbDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF8&autoReconnect=true&allowMultiQueries=true&autoCommit=true");
		config.setUsername("testuser");
		config.setPassword("testPassword");

		/**
		These setting are optional, check https://github.com/brettwooldridge/HikariCP for more detail
		 */
		config.setAutoCommit(true);
		config.setMaximumPoolSize(10);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		return new HikariDataSource(config);
	}
}
