package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderUpdateService {

	private final OrderUpdateRepository repository;

}
