package fr.guiguilechat.jcelechat.model.sde.types.charge;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeCloudSizeBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeVelocityBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosionDelayBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileVelocityBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;

public class MissileGuidanceScript
    extends Charge
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1399)
    public int aoecloudsizebonusbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1399)
    public int aoevelocitybonusbonus;
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
    @DefaultIntValue(1399)
    public int explosiondelaybonusbonus;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1399)
    public int missilevelocitybonusbonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, TechLevel.INSTANCE, AoeCloudSizeBonusBonus.INSTANCE, AoeVelocityBonusBonus.INSTANCE, LauncherGroup.INSTANCE, MissileVelocityBonusBonus.INSTANCE, ExplosionDelayBonusBonus.INSTANCE, MainColor.INSTANCE })));
    public static final MissileGuidanceScript.MetaGroup METAGROUP = new MissileGuidanceScript.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2023 :
            {
                return aoecloudsizebonusbonus;
            }
            case  2024 :
            {
                return aoevelocitybonusbonus;
            }
            case  128 :
            {
                return chargesize;
            }
            case  2026 :
            {
                return explosiondelaybonusbonus;
            }
            case  137 :
            {
                return launchergroup;
            }
            case  124 :
            {
                return maincolor;
            }
            case  2025 :
            {
                return missilevelocitybonusbonus;
            }
            case  422 :
            {
                return techlevel;
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
    public IMetaGroup<MissileGuidanceScript> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissileGuidanceScript>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/MissileGuidanceScript.yaml";
        private Map<Integer, MissileGuidanceScript> cache = (null);

        @Override
        public IMetaCategory<? super MissileGuidanceScript> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1400;
        }

        @Override
        public String getName() {
            return "MissileGuidanceScript";
        }

        @Override
        public synchronized Map<Integer, MissileGuidanceScript> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissileGuidanceScript.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, MissileGuidanceScript> types;
        }
    }
}
