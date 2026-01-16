package fr.guiguilechat.jcelechat.model.sde.types.charge;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResistanceBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResistanceBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResistanceBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResistanceBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class FlexArmorHardenerScript
    extends Charge
{
    /**
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargesize;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int emdamageresistancebonusbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int explosivedamageresistancebonusbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int kineticdamageresistancebonusbonus;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int thermaldamageresistancebonusbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, EmDamageResistanceBonusBonus.INSTANCE, ExplosiveDamageResistanceBonusBonus.INSTANCE, KineticDamageResistanceBonusBonus.INSTANCE, ThermalDamageResistanceBonusBonus.INSTANCE, TechLevel.INSTANCE, LauncherGroup.INSTANCE })));
    public static final FlexArmorHardenerScript.MetaGroup METAGROUP = new FlexArmorHardenerScript.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  128 :
            {
                return chargesize;
            }
            case  2402 :
            {
                return emdamageresistancebonusbonus;
            }
            case  2403 :
            {
                return explosivedamageresistancebonusbonus;
            }
            case  2404 :
            {
                return kineticdamageresistancebonusbonus;
            }
            case  137 :
            {
                return launchergroup;
            }
            case  422 :
            {
                return techlevel;
            }
            case  2405 :
            {
                return thermaldamageresistancebonusbonus;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<FlexArmorHardenerScript> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<FlexArmorHardenerScript>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/FlexArmorHardenerScript.yaml";
        private Map<Integer, FlexArmorHardenerScript> cache = (null);

        @Override
        public IMetaCategory<? super FlexArmorHardenerScript> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1701;
        }

        @Override
        public String getName() {
            return "FlexArmorHardenerScript";
        }

        @Override
        public synchronized Map<Integer, FlexArmorHardenerScript> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(FlexArmorHardenerScript.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, FlexArmorHardenerScript> types;
        }
    }
}
