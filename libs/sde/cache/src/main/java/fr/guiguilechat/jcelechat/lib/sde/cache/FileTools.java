package fr.guiguilechat.jcelechat.lib.sde.cache;

import java.io.File;

public class FileTools {

	public static void delDir(File delete) {
		if (delete.exists()) {
			if (delete.isDirectory()) {
				for (File child : delete.listFiles()) {
					delDir(child);
				}
			}
			delete.delete();
		}
	}

}
