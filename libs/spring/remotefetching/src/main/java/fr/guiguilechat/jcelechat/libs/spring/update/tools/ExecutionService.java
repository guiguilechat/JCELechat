package fr.guiguilechat.jcelechat.libs.spring.update.tools;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.executor")
public class ExecutionService {
	
	@Setter
	private int core = 100;

	@Getter(lazy=true)
	private final ExecutorService executor = makeExecutor();

	private ExecutorService makeExecutor() {
		return Executors.newFixedThreadPool(core);
	}

	public <T> Future<T> submit(Callable<T> task) {
		return getExecutor().submit(task);
	}



}
