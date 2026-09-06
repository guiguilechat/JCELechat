package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.wallet.Journal;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.wallet.Transactions;

public class Wallet {
    public final EsiOauth2 security;
    public final Wallet.GET GET = new Wallet.GET();
    public Journal journal;
    public Transactions transactions;

    public Wallet(EsiOauth2 security) {
        this.security = security;
        journal = new Journal(security);
        transactions = new Transactions(security);
    }

    public class GET {
    }
}
