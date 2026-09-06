package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.ui;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.ui.openwindow.Contract;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.ui.openwindow.Information;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.ui.openwindow.Marketdetails;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.ui.openwindow.Newmail;

public class Openwindow {
    public final EsiOauth2 security;
    public Contract contract;
    public Information information;
    public Marketdetails marketdetails;
    public Newmail newmail;

    public Openwindow(EsiOauth2 security) {
        this.security = security;
        contract = new Contract(security);
        information = new Information(security);
        marketdetails = new Marketdetails(security);
        newmail = new Newmail(security);
    }
}
