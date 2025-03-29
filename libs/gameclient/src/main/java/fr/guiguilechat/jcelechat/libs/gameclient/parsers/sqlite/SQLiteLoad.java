package fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * tool to load a sqlite file into a memory db
 */
public class SQLiteLoad {

	public static Statement loadFile(File file) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
		Statement st = conn.createStatement();
		st.execute("restore from " + file.getAbsolutePath());
		return st;
	}

}
