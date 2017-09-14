package fr.guiguilechat.eveonline.programs.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

/**
 * data stored locally for any platform.
 * <p>
 * The data stored are this class fields. those fields must be primitive or
 * collections.
 * </p>
 *
 */
public abstract class ASettings {

	/**
	 *
	 * @return the name of the application, defaults to
	 *         this.getClass().getcannonicalName()
	 */
	public String getAppName() {
		return getClass().getCanonicalName();
	}

	/**
	 *
	 * @return the directory to store this in.
	 */
	public File getStorageDir() {
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
	public File getFile() {
		return new File(getStorageDir(), "settings.yml");
	}

	/**
	 * store this settings locally, overriding previous stored settings
	 */
	public void store() {
		File f = getFile();
		f.getParentFile().mkdirs();
		try {
			makeYaml().dump(this, new FileWriter(f));
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	public void erase() {
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
	public static <T extends ASettings> T load(Class<T> clazz) {
		T inst = null;
		try {
			inst = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			throw new UnsupportedOperationException("catch this", e1);
		}
		File f = inst.getFile();
		if (f.exists()) {
			try {
				return makeYaml().loadAs(new FileReader(f), clazz);
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
	public static Yaml makeYaml() {
		Yaml ret = new Yaml(YamlDatabase.makeRepresenter(), YamlDatabase.makeOptions());
		return ret;
	}

	public class DelaySaver {

		public DelaySaver() {
			new Thread(this::saveLoop).start();
		}

		public final long SAVE_DELAY_MS = 5000;

		/**
		 * request a save to be done after a delay. if another save request is
		 * performed before that delay, the save is delayed.
		 */
		public void requestSave() {
			nextSave = System.currentTimeMillis() +SAVE_DELAY_MS;
			lock.notify();
		}

		protected void saveLoop() {
			while (true) {
				synchronized (lock) {
					try {
						lock.wait(nextSave - System.currentTimeMillis());
					} catch (InterruptedException e) {
						throw new UnsupportedOperationException("catch this", e);
					}
				}
				if (nextSave <= System.currentTimeMillis()) {
					store();
					nextSave = Long.MAX_VALUE;
				}
			}
		}

		protected Object lock = new Object();

		protected long nextSave = Long.MAX_VALUE;

	}

}
