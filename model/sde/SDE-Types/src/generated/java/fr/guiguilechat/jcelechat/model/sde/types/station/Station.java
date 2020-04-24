package fr.guiguilechat.jcelechat.model.sde.types.station;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import org.yaml.snakeyaml.Yaml;

public class Station
    extends fr.guiguilechat.jcelechat.model.sde.types.Station
{
    /**
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorHP;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ArmorUniformity;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * Whether a station type is player ownable.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IsPlayerOwnable;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCapacity;
    /**
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldRechargeRate;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldUniformity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    public static final Station.MetaGroup METAGROUP = new Station.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  265 :
            {
                return ArmorHP;
            }
            case  524 :
            {
                return ArmorUniformity;
            }
            case  9 :
            {
                return Hp;
            }
            case  589 :
            {
                return IsPlayerOwnable;
            }
            case  263 :
            {
                return ShieldCapacity;
            }
            case  479 :
            {
                return ShieldRechargeRate;
            }
            case  484 :
            {
                return ShieldUniformity;
            }
            case  552 :
            {
                return SignatureRadius;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<Station> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Station>
    {
        public static final String RESOURCE_PATH = "SDE/types/station/Station.yaml";
        private Map<String, Station> cache = (null);

        @Override
        public IMetaCategory<? super Station> category() {
            return fr.guiguilechat.jcelechat.model.sde.types.Station.METACAT;
        }

        @Override
        public int getGroupId() {
            return  15;
        }

        @Override
        public String getName() {
            return "Station";
        }

        @Override
        public synchronized Map<String, Station> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Station.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Station> types;
        }
    }
}
