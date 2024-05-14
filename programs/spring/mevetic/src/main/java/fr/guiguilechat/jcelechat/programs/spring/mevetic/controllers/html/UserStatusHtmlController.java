package fr.guiguilechat.jcelechat.programs.spring.mevetic.controllers.html;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.assets.CharacterAsset;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.assets.CharacterAssetService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContact;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContactService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterAffiliation;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterAffiliationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterInformationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterRoles;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterRolesService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.standings.CharacterStanding.CharacterStandingList;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.standings.CharacterStandingService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterJournal;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterJournalService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransactionService;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUserService;
import fr.guiguilechat.jcelechat.libs.spring.npc.services.CorporationService;
import fr.guiguilechat.jcelechat.libs.spring.npc.services.FactionService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserStatusHtmlController {

	@Lazy
	private final CharacterAffiliationService characterAffiliationService;

	@Lazy
	private final CharacterAssetService characterAssetService;

	@Lazy
	private final CharacterContactService characterContactService;

	@Lazy
	private final CharacterInformationService characterInformationService;

	@Lazy
	private final CharacterJournalService characterJournalService;

	@Lazy
	private final CharacterRolesService characterRolesService;

	@Lazy
	private final CharacterStandingService characterStandingService;

	@Lazy
	private final CharacterTransactionService characterTransactionService;

	@Lazy
	private final CorporationService corporationService;

	@Lazy
	private final FactionService factionService;

	@GetMapping("/")
	public String getUser(Model model, Authentication auth) {
		int charId = EsiUserService.getCharacterId(auth);
		Optional<CharacterAffiliation> oca = characterAffiliationService.getFetched(charId);
		if (oca.isPresent()) {
			model.addAttribute("affiliation", oca.get());
		}
		return "user/settings";
	}

	@GetMapping("/roles")
	public String getRoles(Model model, Authentication auth) {
		Optional<CharacterRoles> userRoles = characterRolesService.getFetched(EsiUserService.getCharacterId(auth));
		if (userRoles.isPresent()) {
			model.addAttribute("roles", userRoles.get());
		}
		return "user/roles";
	}

	@GetMapping("/contacts")
	public String getContacts(Model model, Authentication auth) {
		int charId = EsiUserService.getCharacterId(auth);
		List<CharacterContact> userContacts = characterContactService
		    .list(charId);
		if (userContacts != null) {
			model.addAttribute("contacts", userContacts.stream().filter(cc -> !cc.isBlocked()).toList());
			model.addAttribute("blocked", userContacts.stream().filter(CharacterContact::isBlocked).toList());
		}

		CharacterStandingList charFetch = characterStandingService.fetched(charId);
		if (charFetch != null && charFetch.isFetched()) {
			if (charFetch.getExpires() != null) {
				Duration remain = Duration.between(Instant.now(), charFetch.getExpires());
				model.addAttribute("expires",
				    remain.toString().substring(2).replaceAll("\\.[0-9]*", ""));
			}
			if (charFetch.getLastUpdate() != null) {
				model.addAttribute("lastupdate", charFetch.getLastUpdate());
			}
		}
		return "user/contacts";
	}

	@GetMapping("/assets")
	public String getAssets(Model model, Authentication auth) {
		List<CharacterAsset> userAssets = characterAssetService.list(EsiUserService.getCharacterId(auth));
		if (userAssets != null) {
			model.addAttribute("assets", userAssets);
		}
		return "user/assets";
	}

	@GetMapping("/wallet")
	public String getWallet(Model model, Authentication auth) {

		List<CharacterJournal> userJournals = characterJournalService.list(EsiUserService.getCharacterId(auth));
		if (userJournals != null) {
			model.addAttribute("journals", userJournals);
		}

		List<CharacterTransaction> transactions = characterTransactionService.list(EsiUserService.getCharacterId(auth));
		if (transactions != null) {
			model.addAttribute("transactions", transactions);
		}

		return "user/wallet";
	}

}
