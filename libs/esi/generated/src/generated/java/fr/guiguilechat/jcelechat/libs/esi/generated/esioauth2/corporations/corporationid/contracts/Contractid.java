package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.contracts;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.contracts.contractid.Bids;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.contracts.contractid.Items;

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
