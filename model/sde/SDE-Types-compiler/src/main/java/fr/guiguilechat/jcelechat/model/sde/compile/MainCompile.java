package fr.guiguilechat.jcelechat.model.sde.compile;

import java.io.File;
import java.io.IOException;

import com.helger.jcodemodel.writer.JCMWriter;
import com.helger.jcodemodel.writer.ProgressCodeWriter.IProgressTracker;

import fr.guiguilechat.jcelechat.model.FileTools;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeHierarchy;
import fr.guiguilechat.jcelechat.model.sde.loaders.SDELoader;
import fr.guiguilechat.jcelechat.model.sde.translate.TypesTranslater;

public class MainCompile {

	/**
	 *
	 * @param args
	 *          classGenerationFolder resourcePath classPathResourcePath ;
	 *          Typically, src/generated/java src/generated/resources/SDE SDE/
	 * @throws IOException
	 */
	public static void main(String... args) throws IOException {

		File srcTarget = new File(args[0]);
		FileTools.delDir(srcTarget);
		srcTarget.mkdirs();
		File resTarget = new File(args[1]);
		FileTools.delDir(resTarget);
		resTarget.mkdirs();
		TypeHierarchy hierarchy = SDELoader.load();
		CompilationData compiled = new SDECompiler2().compile(hierarchy);
		new TypesTranslater().translate(hierarchy, compiled, resTarget, args[2]);
		new JCMWriter(compiled.model).build(srcTarget, (IProgressTracker) null);
	}
}
