package fr.jcelacht.libs.dbmaker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class DBMaker {

	public static void main(String[] args) throws IOException {
		File outRoot = new File("src/main/resources/docker/oracle");
		outRoot.mkdirs();
		File outSQL = new File(outRoot, "sql");
		if (outSQL.exists()) {
			Files.walk(outSQL.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			outSQL.delete();
		}
		outSQL.mkdirs();
		for (TableBuilder builder : TableBuilder.instances()) {
			builder.execute(outSQL);
		}
	}

}
