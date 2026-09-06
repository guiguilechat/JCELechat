package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.wallets;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.wallets.division.Journal;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.wallets.division.Transactions;

public class Division {
    public final EsiOauth2 security;
    public Journal journal;
    public Transactions transactions;

    public Division(EsiOauth2 security) {
        this.security = security;
        journal = new Journal(security);
        transactions = new Transactions(security);
    }
}
