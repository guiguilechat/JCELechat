package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Service
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderDeletionService {

	private final OrderDeletionRepository repo;

}
