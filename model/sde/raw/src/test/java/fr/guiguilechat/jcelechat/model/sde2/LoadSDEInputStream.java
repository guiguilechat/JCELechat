package fr.guiguilechat.jcelechat.model.sde2;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import fr.guiguilechat.jcelechat.model.sde2.parsers.Eblueprints;
import fr.guiguilechat.jcelechat.model.sde2.parsers.EmapMoons;
import fr.guiguilechat.jcelechat.model.sde2.parsers.Eraces;
import fr.guiguilechat.jcelechat.model.sde2.parsers.Etypes;
import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SDEDownload;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SDEDownload.Success;
import fr.guiguilechat.jcelechat.model.sde2.yaml.YamlCache;

public class LoadSDEInputStream {

	public static void main(String[] args) throws IOException {
		// no last release
		SDEDownload d = YamlCache.INSTANCE.getSDE();
		if (d instanceof Success s) {
			System.out.println("fetch SDE released=" + s.meta().releaseDate + " build=" + s.meta().buildNumber);
			ZipInputStream zip = s.zipputSteam();
			ZipEntry ze;
			while ((ze = zip.getNextEntry()) != null) {
				applyEntry(ze.getName(), zip);
			}
		} else {
			System.out.println("could not received SDE : " + d);
		}
	}

	public static void applyEntry(String entryName, InputStream is) {
		var loader = loader(entryName);
		if (loader != null) {
			System.out.println("parsing " + entryName + " using " + loader.getClass().getName());
			long start = System.currentTimeMillis();
			var loaded = loader.from(is);
			System.out.println(" first value is " + loaded.values().iterator().next().getClass().getSimpleName());
			System.out.println(
					"  parsed " + loaded.size() + " in " + (System.currentTimeMillis() - start) + "ms");
		}
	}

	public static JacksonYamlLHMLoader<?> loader(String entryName) {
		return switch (entryName) {
		case Eblueprints.SDE_FILE_YAML -> Eblueprints.LOADER;
		case EmapMoons.SDE_FILE_YAML -> EmapMoons.LOADER;
		case Eraces.SDE_FILE_YAML -> Eraces.LOADER;
		case Etypes.SDE_FILE_YAML -> Etypes.LOADER;
		default -> null;
		};
	}

}
