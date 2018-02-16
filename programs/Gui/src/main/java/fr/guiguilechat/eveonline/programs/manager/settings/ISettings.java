package fr.guiguilechat.eveonline.programs.manager.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.eveonline.model.sde.yaml.CleanRepresenter;
import fr.guiguilechat.eveonline.model.sde.yaml.Tools;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;


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
	public default File getStorageFile() {
		if (useTempDir()) {
			try {
				return new File(File.createTempFile("___", null).getParentFile(), getAppName() + "_settings.yml");
			} catch (IOException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return new File(getStorageDir(), "settings.yml");
	}

	/**
	 * default to false.
	 *
	 * @return true iff files must be created using the system's temporary file
	 *         system
	 *
	 */
	public default boolean useTempDir() {
		return false;
	}

	/**
	 * store this settings locally, overriding previous stored settings
	 */
	public default void store() {
		File f = getStorageFile();
		f.getParentFile().mkdirs();
		try {
			makeDumpYaml().dump(this, new FileWriter(f));
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	/**
	 * request to store this after a delay. This aims at reducing disk overhead.
	 */
	public void storeLater();

	/**
	 * delete the file used to store this.
	 */
	public default void erase() {
		File f = getStorageFile();
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
		File f = inst.getStorageFile();
		if (f.exists()) {
			try {
				return inst.makeLoadYaml().loadAs(new FileReader(f), clazz);
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return inst;
	}

	/**
	 * attach listeners to the observable elements and collections that call
	 * {@link #storeLater()} on modification.<br />
	 * collections also trigger the attachement of such a listener
	 *
	 *
	 * @param sets
	 */
	public static void attachStoreListeners(ISettings sets) {
		for (Field f : sets.getClass().getDeclaredFields()) {
			Class<?> cl = f.getType();
			if (Observable.class.isAssignableFrom(cl)) {
				f.setAccessible(true);
				try {
					Observable ob = (Observable) f.get(sets);
					if (ob != null) {
						attachStoreListener(ob, sets::storeLater);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new UnsupportedOperationException("catch this", e);
				}
			}
		}
	}

	public static void attachStoreListener(Observable ob, Runnable store) {
		if (ob == null || store == null) {
			return;
		}
		if (ob instanceof ObservableMap<?, ?>) {
			ObservableMap<?, ?> om = (ObservableMap<?, ?>) ob;
			om.addListener((MapChangeListener<Object, Object>) change -> {
				store.run();
				if (change.wasAdded()) {
					// we added a new key
					if (!change.wasRemoved()) {
						Object key = change.getKey();
						if (key != null && key instanceof Observable) {
							attachStoreListener((Observable) key, store);
						}
					}
					if (change.wasAdded()) {
						Object value = change.getValueAdded();
						if (value != null && value instanceof Observable) {
							attachStoreListener((Observable) value, store);
						}
					}
				}
			});
		} else if (ob instanceof ObservableSet<?>) {
			ObservableSet<?> os = (ObservableSet<?>) ob;
			os.addListener((SetChangeListener<Object>) change -> {
				store.run();
				if (change.wasAdded()) {
					Object value = change.getElementAdded();
					if (value != null && value instanceof Observable) {
						attachStoreListener((Observable) value, store);
					}
				}
			});
		} else if (ob instanceof ObservableList<?>) {
			ObservableList<?> os = (ObservableList<?>) ob;
			os.addListener((ListChangeListener<Object>) change -> {
				store.run();
				if (change.wasAdded()) {
					for (Object value : change.getAddedSubList()) {
						if (value != null && value instanceof Observable) {
							attachStoreListener((Observable) value, store);
						}
					}
				}
			});
		} else {
			ob.addListener(ev -> store.run());
		}
	}

	/**
	 * make yaml for dumping
	 *
	 * @return
	 */
	public default Yaml makeDumpYaml() {
		Yaml ret = new Yaml(makeYamlConstructor(true), makeYamlRepresenter(true), makeYamlOptions(true));
		return ret;
	}

	public default Yaml makeLoadYaml() {
		Yaml ret = new Yaml(makeYamlConstructor(false), makeYamlRepresenter(false), makeYamlOptions(false));
		return ret;
	}

	public default Constructor makeYamlConstructor(boolean dump) {
		return new Constructor(getClass());
	}

	public default Representer makeYamlRepresenter(boolean dump) {
		CleanRepresenter ret = new CleanRepresenter();
		if (dump) {
			// ret.getPropertyUtils().setBeanAccess(BeanAccess.FIELD);
		} else {
			ret.getPropertyUtils().setSkipMissingProperties(true);
		}
		return ret;
	}

	public default DumperOptions makeYamlOptions(boolean dump) {
		return Tools.blockDumper();
	}

}
