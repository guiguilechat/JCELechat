package fr.guiguilechat.jcelechat.libs.spring.anon.industry.indices;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SystemCostIndicesService {

	private final SystemCostIndicesRepository itemRepository;

}
