package fr.guiguilechat.jcelechat.model.formula.industry;

public class Research {

	protected static final int[] LEVEL_MULT = { 0, 105, 250, 595, 1414, 3360, 8000, 19000, 45255, 107700, 256000 };

	protected static final int MULT_BASE = 105;

	/**
	 * base for research is 2% of EIV
	 */
	protected static final double EIV_MULT = 2 * 0.01;

	protected static final int EIV_DIV = 50;

	static long ptv(long eiv, int endLevel) {
		return eiv * LEVEL_MULT[endLevel] / MULT_BASE / EIV_DIV;
	}

	public static long ptv(long eiv, int startlevel, int endlevel) {
		if (startlevel < 0 || endlevel > 10 || startlevel >= endlevel) {
			return 0l;
		}
		return ptv(eiv, endlevel) - ptv(eiv, startlevel);
	}

	/**
	 * @param eiv    BP eiv
	 * @param me     ME rank (from 0 to 10)
	 * @param te     TE rank (from 0 to 20)
	 * @param totTax total taxes sum, base 1 (so a 5% tax is 0.05)
	 * @return total installation fee to research BP at given ME and TE
	 */
	public static long researchTax(long eiv, int me, int te, double totTax) {
		return (long) (ptv(eiv, 0, me) + ptv(eiv, 0, te / 2) * totTax);
	}

	/**
	 * level for TE is /2 . so a 10/20 BP actually has 10 levels in mat and 10 in
	 * time
	 * 
	 * @param basetime   base research_time of the bp activity
	 * @param startlevel level the BP is
	 * @param endlevel   level the BP ends
	 * @return time to research given level, without modifiers
	 */
	public static long time(long basetime, int startlevel, int endlevel) {
		if (startlevel < 0 || endlevel > 10 || startlevel >= endlevel) {
			return 0l;
		}
		return (LEVEL_MULT[endlevel] - LEVEL_MULT[startlevel]) / MULT_BASE * basetime;
	}

}
