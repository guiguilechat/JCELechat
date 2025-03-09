package fr.guiguilechat.jcelechat.libs.everaw.parsers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLiteParser {

	public static Statement loadFile(File file) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
			Statement st = conn.createStatement();
			st.execute("restore from " + file.getAbsolutePath());
			return st;
		} catch (Throwable e) {
			e.printStackTrace(System.err);
			return null;
		}
	}

}
