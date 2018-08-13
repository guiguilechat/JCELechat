package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class SupportFighterBlueprint
    extends Blueprint
{
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public final static SupportFighterBlueprint.MetaGroup METAGROUP = new SupportFighterBlueprint.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/SupportFighterBlueprint.yaml";
    private static Map<String, SupportFighterBlueprint> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  422 :
            {
                return TechLevel;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1679;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SupportFighterBlueprint> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SupportFighterBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SupportFighterBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SupportFighterBlueprint> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SupportFighterBlueprint>
    {

        @Override
        public MetaCategory<? super SupportFighterBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "SupportFighterBlueprint";
        }

        @Override
        public Collection<SupportFighterBlueprint> items() {
            return (load().values());
        }
    }
}
