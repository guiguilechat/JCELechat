package fr.guiguilechat.jcelechat.model.sde.compile;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jcodemodel.writer.JCMWriter;
import com.helger.jcodemodel.writer.ProgressCodeWriter.IProgressTracker;

import fr.guiguilechat.jcelechat.model.FileTools;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeHierarchy;
import fr.guiguilechat.jcelechat.model.sde.loaders.ESILoader;
import fr.guiguilechat.jcelechat.model.sde.loaders.SDELoader;
import fr.guiguilechat.jcelechat.model.sde.translate.TypesTranslater;

public class MainCompile {

	private static final Logger logger = LoggerFactory.getLogger(MainCompile.class);

	public static enum LOADER {
		ESI, SDE

	}

	/**
	 *
	 * @param args
	 *          classGenerationFolder resourcePath classPathResourcePath ;
	 *          Typically, src/generated/java src/generated/resources/SDE SDE/
	 * @throws IOException
	 */
	public static void main(String... args) throws IOException {
		LOADER loader = LOADER.ESI;
		boolean specifictests = false;
		long startTime = System.currentTimeMillis();
		File srcTarget = new File(args[0]);
		FileTools.delDir(srcTarget);
		srcTarget.mkdirs();
		File resTarget = new File(args[1]);
		FileTools.delDir(resTarget);
		resTarget.mkdirs();
		logger.info("cleaned dirs in " + (System.currentTimeMillis() - startTime) / 1000 + "s");
		startTime = System.currentTimeMillis();
		TypeHierarchy hierarchy = loader == LOADER.SDE ? SDELoader.load() : ESILoader.load();
		logger.info("loaded types in " + (System.currentTimeMillis() - startTime) / 1000 + "s");
		startTime = System.currentTimeMillis();
		CompilationData compiled = new SDECompiler().compile(hierarchy);
		logger.info("compiled types in " + (System.currentTimeMillis() - startTime) / 1000 + "s");
		if (specifictests) {
			startTime = System.currentTimeMillis();
			// writing must be done after translation because specific changes are
			// made pre-writting, typically indexing the items that have been
			// translated
			new JCMWriter(compiled.model).build(srcTarget, (IProgressTracker) null);
			logger.info("made test export in " + (System.currentTimeMillis() - startTime) / 1000 + "s");
		}
		startTime = System.currentTimeMillis();
		new TypesTranslater().translate(hierarchy, compiled, resTarget, args[2]);
		logger.info("translated types in " + (System.currentTimeMillis() - startTime) / 1000 + "s");
		startTime = System.currentTimeMillis();
		new JCMWriter(compiled.model).build(srcTarget, (IProgressTracker) null);
		logger.info("wrote structure in " + (System.currentTimeMillis() - startTime) / 1000 + "s");
	}
}
