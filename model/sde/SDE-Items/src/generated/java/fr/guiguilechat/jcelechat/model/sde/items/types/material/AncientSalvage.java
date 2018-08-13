package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class AncientSalvage
    extends Material
{
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    public final static AncientSalvage.MetaGroup METAGROUP = new AncientSalvage.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/material/AncientSalvage.yaml";
    private static Map<String, AncientSalvage> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  9 :
            {
                return Hp;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  966;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<AncientSalvage> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, AncientSalvage> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AncientSalvage.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, AncientSalvage> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<AncientSalvage>
    {

        @Override
        public MetaCategory<? super AncientSalvage> category() {
            return Material.METACAT;
        }

        @Override
        public String getName() {
            return "AncientSalvage";
        }

        @Override
        public Collection<AncientSalvage> items() {
            return (load().values());
        }
    }
}
