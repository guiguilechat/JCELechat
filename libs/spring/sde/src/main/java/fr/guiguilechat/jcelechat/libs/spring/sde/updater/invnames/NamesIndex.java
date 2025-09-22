package fr.guiguilechat.jcelechat.libs.spring.sde.updater.invnames;

import java.io.InputStream;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateListener;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EinvNames;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class NamesIndex implements SdeUpdateListener {

	@Getter
	private Map<Integer, String> index = null;

	@Override
	public void beforeSdeUpdate() {
		index = null;
	}

	static final Pattern ENTRYNAME_INVNAMES_PATTERN = Pattern.compile(
			"bsd/invNames\\.yaml");

	@Override
	public void onSdeFile(String name, Supplier<InputStream> fileContent) {
		if (ENTRYNAME_INVNAMES_PATTERN.matcher(name).matches()) {
			index = EinvNames.LOADER.from(fileContent.get())
					.stream().collect(Collectors.toMap(e -> e.itemID, e -> e.itemName));
			return;
		}
	}

	@Override
	public void afterSdeUpdate() {
		if (index == null) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file for matcher "
					+ ENTRYNAME_INVNAMES_PATTERN);
		}
	}

}
