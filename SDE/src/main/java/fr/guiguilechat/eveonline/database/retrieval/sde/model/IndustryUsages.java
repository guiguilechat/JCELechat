package fr.guiguilechat.eveonline.database.retrieval.sde.model;

import java.util.HashSet;

import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.Eblueprints;

/**
 * usages of an item in industry
 *
 */
public class IndustryUsages {

	public HashSet<Eblueprints> asMaterial = new HashSet<>();
	public HashSet<Eblueprints> asProduct = new HashSet<>();

}
