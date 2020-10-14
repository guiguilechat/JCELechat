package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled;

import fr.guiguilechat.jcelechat.jcesi.interfaces.ISwaggerCacheHelper;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected.Alliances;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected.Characters;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected.Corporation;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected.Corporations;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected.Fleets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected.Markets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected.Universe;

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
