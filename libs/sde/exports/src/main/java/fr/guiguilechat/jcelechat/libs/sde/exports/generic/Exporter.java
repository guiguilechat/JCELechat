package fr.guiguilechat.jcelechat.libs.sde.exports.generic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Exporter<T> {

	private final String RESOURCE_PATH;

	protected void export(T data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(data, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting constellations to " + output.getAbsolutePath(), e);
		}
	}

	protected abstract T load();

	public void export(File folderout) {
		export(load(), folderout);
	}

}
