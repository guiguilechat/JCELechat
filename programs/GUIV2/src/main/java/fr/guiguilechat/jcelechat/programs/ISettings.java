package fr.guiguilechat.jcelechat.programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.jcelechat.model.sde.yaml.CleanRepresenter;
import fr.guiguilechat.jcelechat.model.sde.yaml.YAMLTools;


/**
 * data stored locally for any platform.
 * <p>
 * The data stored are this class fields. those fields must be primitive or
 * collections.
 * </p>
 *
 */
public interface ISettings {

	/**
	 *
	 * @return the name of the application, defaults to
	 *         this.getClass().getcannonicalName()
	 */
	public default String getAppName() {
		return getClass().getCanonicalName();
	}

	/**
	 *
	 * @return the directory to store this in.
	 */
	public default File getStorageDir() {
		String folderName = System.getenv("LOCALAPPDATA");
		if (folderName != null) {
			return new File(folderName);
		}
		return new File(new File(System.getProperty("user.home")), getAppName());
	}

	/**
	 *
	 * @return the file used to store this
	 */
	public default File getFile() {
		if (useTempDir()) {
			try {
				return new File(File.createTempFile("___", null).getParentFile(), getAppName() + "_settings.yml");
			} catch (IOException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return new File(getStorageDir(), "settings.yml");
	}

	public default boolean useTempDir() {
		return false;
	}

	/**
	 * store this settings locally, overriding previous stored settings
	 */
	public default void store() {
		File f = getFile();
		f.getParentFile().mkdirs();
		try {
			makeYaml().dump(this, new FileWriter(f));
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	public default void erase() {
		File f = getFile();
		if (f.exists()) {
			f.delete();
		}
	}

	/**
	 * load stored settings if exists, or default settings
	 *
	 * @return
	 * @throws FileNotFoundException
	 */
	public static <T extends ISettings> T load(Class<T> clazz) {
		T inst = null;
		try {
			inst = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			throw new UnsupportedOperationException("catch this", e1);
		}
		File f = inst.getFile();
		if (f.exists()) {
			try {
				return inst.makeYaml().loadAs(new FileReader(f), clazz);
			} catch (FileNotFoundException e) {
			}
		}
		return inst;
	}

	/**
	 * make yaml for parsing/unparsing
	 *
	 * @return
	 */
	public default Yaml makeYaml() {
		Yaml ret = new Yaml(makeYamlConstructor(), makeYamlRepresenter(), makeYamlOptions());
		return ret;
	}

	public default Constructor makeYamlConstructor() {
		return new Constructor(getClass());
	}

	public default Representer makeYamlRepresenter() {
		return new CleanRepresenter();
	}

	public default DumperOptions makeYamlOptions() {
		return YAMLTools.blockDumper();
	}

}
