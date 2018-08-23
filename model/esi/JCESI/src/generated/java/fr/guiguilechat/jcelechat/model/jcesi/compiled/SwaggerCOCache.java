package fr.guiguilechat.jcelechat.model.jcesi.compiled;

import fr.guiguilechat.jcelechat.model.jcesi.compiled.connected.Alliances;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.connected.Characters;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.connected.Corporation;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.connected.Corporations;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.connected.Fleets;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.connected.Markets;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.connected.Universe;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ISwaggerCacheHelper;

public abstract class SwaggerCOCache<T extends G_ICOAccess>
    implements ISwaggerCacheHelper
{
    public final T swagger;
    public final Alliances alliances = new Alliances(this);
    public final Characters characters = new Characters(this);
    public final Corporation corporation = new Corporation(this);
    public final Corporations corporations = new Corporations(this);
    public final Fleets fleets = new Fleets(this);
    public final Markets markets = new Markets(this);
    public final Universe universe = new Universe(this);

    public SwaggerCOCache(T swag) {
        swagger = swag;
    }
}
