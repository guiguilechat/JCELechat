package fr.guiguilechat.jcelechat.programs.spring.noscrapeve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.manager.RemoteResourceUpdaterService.IRemoteResourceUpdateListener;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "jcelechat.noscrape.oncomplete")
public class ShutdownOnFetchComplete implements IRemoteResourceUpdateListener {

	private final ApplicationContext context;

	@Setter
	private boolean stop = false;

	@Override
	public void onNoUpdateRemain() {
		if (!stop) {
			return;
		}
		log.info("DB up to date, exiting context " + context);
		SpringApplication.exit(context, () -> 0);
		// this should not be here but app does not stop otherwise
		System.exit(0);
	}

}
