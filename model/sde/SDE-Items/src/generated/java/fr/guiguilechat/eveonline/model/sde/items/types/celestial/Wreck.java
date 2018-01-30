package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class Wreck
    extends Celestial
{
    /**
     * Resistance against Stasis Webifiers
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StasisWebifierResistance;
    /**
     * The difficulty in opening this object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double AccessDifficulty;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * The amount of milliseconds before the wreck dissapears. Note: this only applies to NPC wrecks or empty player wrecks.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(7200000)
    public int ExplosionDelayWreck;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    public final static String RESOURCE_PATH = "SDE/items/celestial/Wreck.yaml";
    private static LinkedHashMap<String, Wreck> cache = (null);

    @Override
    public int getGroupId() {
        return  186;
    }

    @Override
    public Class<?> getGroup() {
        return Wreck.class;
    }

    public static LinkedHashMap<String, Wreck> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Wreck.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Wreck> items;
    }
}