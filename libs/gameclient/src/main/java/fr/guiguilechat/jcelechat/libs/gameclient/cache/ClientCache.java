package fr.guiguilechat.jcelechat.libs.gameclient.cache;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ResourceIndex;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ResourceMetaData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
	private final File versionDir = new File(rootPath, clientInfo.build());

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

	public void extractOn(Predicate<String> resourceNameFilter) {
		getVersionDir().mkdirs();
		getAllIndexes().parallelStream().forEach(i -> {
			i.getMap().entrySet().parallelStream().forEach(e -> {
				String resName = e.getKey();
				ResourceMetaData md = e.getValue();
				if (resourceNameFilter.test(resName)) {
					System.out.println(resName);
					try {
						md.dump(getVersionDir());
					} catch (IOException io) {
						System.err.println(io.getClass().getSimpleName() + " : " + io.getMessage());
					}
				}
			});
		});
	}

	public File file(String resName) {
		File target = new File(getVersionDir(), resName);
		if (target.exists()) {
			return target;
		}
		for (ResourceIndex i : getAllIndexes()) {
			ResourceMetaData md = i.getMap().get(resName);
			if (md != null) {
				try {
					return md.dump(getVersionDir());
				} catch (IOException e) {
					System.err.println(" for resource " + resName + " : " + e.getMessage());
				}
			}
		}
		return null;
	}

}
