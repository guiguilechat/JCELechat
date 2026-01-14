package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.transaction;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterTransactionService {

	@Accessors(fluent = true)
	private final CharacterTransactionRepository recordRepo;

	public List<CharacterTransaction> list(int Id) {
		return recordRepo.findAllByFetchResourceId(Id);
	}

}
