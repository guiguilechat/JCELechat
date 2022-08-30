package fr.jcelacht.libs.dbmaker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.jcelacht.libs.dbmaker.builders.TypeAttributesBuilder;

public class DBMaker {

	public static void main(String[] args) throws IOException {
		File outRoot = new File("src/main/resources/docker/oracle");
		outRoot.mkdirs();
		File outSQL = new File(outRoot, "sql");
		new DBMaker().make(outSQL, List.of(TypeAttributesBuilder.INSTANCE));
	}

	public void make(File outDir, List<TableBuilder> builders) {
		if (outDir.exists()) {
			try {
				Files.walk(outDir.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			} catch (IOException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
			outDir.delete();
		}
		outDir.mkdirs();

		Set<TableBuilder> withDeps = new HashSet<>();
		for (TableBuilder builder : builders) {
			addDeps(builder, withDeps, new HashSet<>());
		}
		List<TableBuilder> sorted = new ArrayList<>(withDeps);
		sorted.sort(Comparator.comparing(TableBuilder::computePriority));
		for (TableBuilder builder : sorted) {
			System.out.println("building " + builder);
			builder.execute(outDir);
		}

	}

	protected void addDeps(TableBuilder builder, Set<TableBuilder> fullDeps, Set<TableBuilder> parents) {
		if (parents.contains(builder)) {
			throw new UnsupportedOperationException("dependancy cycle for builder " + builder);
		}
		if (fullDeps.contains(builder)) {
			return;
		}

		fullDeps.add(builder);
		Set<TableBuilder> newparents = new HashSet<>(parents);
		newparents.add(builder);
		for (TableBuilder dep : builder.dependsOn()) {
			addDeps(dep, fullDeps, newparents);
		}
	}

}
