package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class MobileSiphonUnitBlueprint
    extends Blueprint
{
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IndustryBlueprintRank;
    public final static MobileSiphonUnitBlueprint.MetaGroup METAGROUP = new MobileSiphonUnitBlueprint.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/MobileSiphonUnitBlueprint.yaml";
    private static Map<String, MobileSiphonUnitBlueprint> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1955 :
            {
                return IndustryBlueprintRank;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1267;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<MobileSiphonUnitBlueprint> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, MobileSiphonUnitBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MobileSiphonUnitBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, MobileSiphonUnitBlueprint> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<MobileSiphonUnitBlueprint>
    {

        @Override
        public MetaCategory<? super MobileSiphonUnitBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "MobileSiphonUnitBlueprint";
        }

        @Override
        public Collection<MobileSiphonUnitBlueprint> items() {
            return (load().values());
        }
    }
}
