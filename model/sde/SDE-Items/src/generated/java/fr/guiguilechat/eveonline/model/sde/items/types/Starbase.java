
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;

public abstract class Starbase
    extends Item
{

    /**
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Hp;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double StructureUniformity;
    /**
     * How long it takes to anchor or unanchor this object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(60000.0D)
    public double AnchoringDelay;
    /**
     * If this module is in use and this attribute is 1, then offensive modules cannot be used on the ship if they apply modifiers for the duration of their effect. If this is put on a ship or NPC with value of 1, then the ship or NPC are immune to offensive modifiers (target jamming, tracking disruption etc.)
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DisallowOffensiveModifiers;
    /**
     * How long it takes to unanchor this object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(60000.0D)
    public double UnanchoringDelay;
    /**
     * How long it takes to bring this object online.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(60000.0D)
    public double OnliningDelay;

    @Override
    public int getCategoryId() {
        return  23;
    }

    @Override
    public Class<?> getCategory() {
        return Starbase.class;
    }

}
