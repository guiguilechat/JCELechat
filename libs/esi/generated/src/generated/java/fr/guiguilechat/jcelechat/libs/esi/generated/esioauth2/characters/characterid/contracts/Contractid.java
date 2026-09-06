package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.contracts;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.contracts.contractid.Bids;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.contracts.contractid.Items;

public class Contractid {
    public final EsiOauth2 security;
    public Bids bids;
    public Items items;

    public Contractid(EsiOauth2 security) {
        this.security = security;
        bids = new Bids(security);
        items = new Items(security);
    }
}
