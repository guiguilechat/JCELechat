package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class MobileCynosuralInhibitorBlueprint
    extends Blueprint
{
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IndustryBlueprintRank;
    public final static String RESOURCE_PATH = "SDE/items/blueprint/MobileCynosuralInhibitorBlueprint.yaml";
    private static LinkedHashMap<String, MobileCynosuralInhibitorBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  1268;
    }

    @Override
    public Class<?> getGroup() {
        return MobileCynosuralInhibitorBlueprint.class;
    }

    public static synchronized LinkedHashMap<String, MobileCynosuralInhibitorBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MobileCynosuralInhibitorBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MobileCynosuralInhibitorBlueprint> items;
    }
}
