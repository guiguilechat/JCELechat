package fr.guiguilechat.jcelechat.libs.gameclient.cache;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.PythonLoadedLib;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ResourceIndex;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ResourceMetaData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public class ClientCache {

	private final File rootPath;

	private final ClientInfo clientInfo;

	// create a cache on a temporary directory
	public ClientCache(ClientInfo clientInfo) throws IOException {
		this(Files.createTempDirectory("evegameclient").toFile(), clientInfo);
	}

	@Getter(lazy = true)
	private final File versionDir = new File(rootPath, clientInfo.getBuild());

	@Getter(lazy = true)
	private final ResourceIndex rootIndex = clientInfo.rootIndex();

	@Getter(lazy = true)
	private final List<ResourceIndex> allIndexes = makeAllIndexes();

	protected List<ResourceIndex> makeAllIndexes() {
		ResourceIndex root = getRootIndex();
		return Stream.concat(Stream.of(root),
				root.getIndexNames().stream()
						.map(resName -> new ResourceIndex(root.getMap().get(resName).relPath())))
				.toList();
	}

	protected void failOnProtected() {
		if (getClientInfo().is_protected()) {
			throw new RuntimeException("eve client is protected, can't touch it");
		}
	}

	public int extractOn(Predicate<String> resourceNameFilter, PrintStream out, PrintStream err) {
		failOnProtected();
		AtomicInteger successes = new AtomicInteger(0);
		getVersionDir().mkdirs();
		getAllIndexes().parallelStream()
				.flatMap(i -> i.getMap().entrySet().parallelStream())
				.filter(e -> resourceNameFilter.test(e.getKey()))
				.forEach(e -> {
					if (out != null) {
						out.println(e.getKey());
					}
					try {
						e.getValue().dump(getVersionDir());
						successes.addAndGet(1);
					} catch (IOException io) {
						if (err != null) {
							err.println(io.getClass().getSimpleName() + " : " + io.getMessage());
						}
						log.error("while opening " + e.getKey(), io);
					}
				});
		return successes.get();
	}

	public int extractOn(Predicate<String> resourceNameFilter) {
		return extractOn(resourceNameFilter, null, null);
	}

	public ResourceMetaData metaData(String resName) {
		failOnProtected();
		for (ResourceIndex i : getAllIndexes()) {
			ResourceMetaData md = i.getMap().get(resName);
			if (md != null) {
				return md;
			}

		}

		return null;
	}

	public File file(String resName) {
		File target = new File(getVersionDir(), resName);
		if (target.exists()) {
			return target;
		}
		ResourceMetaData md = metaData(resName);
		if (md != null) {
			try {
				return md.dump(getVersionDir());
			} catch (IOException e) {
				log.error(" for resource " + resName, e);
			}
		}
		return null;
	}

	public File file(ResourceMetaData rmd) {
		return file(rmd.resName());
	}

	//
	// specific python loaders
	//

	public Stream<PythonLoadedLib> streamPyLibs() {
		return getAllIndexes().stream()
				.flatMap(idx -> idx.getLines().stream())
				.filter(md -> md.resName().endsWith("Loader.pyd"))
				.map(this::makePyLib)
				.filter(o -> o != null);
	}

	PythonLoadedLib makePyLib(ResourceMetaData loaderLine) {
		String name = loaderLine.resName()
				.replaceAll("Loader.pyd$", "")
				.replaceAll("^.*/", "")
				.toLowerCase();
		String fsdBinaryName = "staticdata/" + name + ".fsdbinary";
		ResourceMetaData fsdBinary = metaData(fsdBinaryName);
		if (fsdBinary == null) {
			System.err.println("can't find fsdbinary " + fsdBinaryName + " for loader " + loaderLine.resName());
			return null;
		}
		return new PythonLoadedLib(name, loaderLine, fsdBinary);
	}

	@Getter(lazy = true)
	private final Map<String, PythonLoadedLib> pyLibs = streamPyLibs()
			.collect(Collectors.toMap(
					pl -> pl.getResourceName(),
					pl -> pl));

	/**
	 * cache the files of a python loader
	 *
	 * @param pll the meta data of the python loader
	 * @return true of both files could be cached.
	 */
	public boolean cache(PythonLoadedLib pll) {
		return Stream.of(pll.getLoader(), pll.getFsdbinary()).parallel()
				.map(this::file)
				.filter(f -> f == null)
				.findAny().isEmpty();
	}

}
