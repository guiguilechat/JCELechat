package fr.guiguilechat.jcelechat.jcesi.tools;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;

/**
 * Tools to use the {@link RegionalMarket}
 */
public class MarketHelpers {

	public static final DecimalFormat formatPriceToEve = new DecimalFormat("#.##",
			DecimalFormatSymbols.getInstance(Locale.ENGLISH));

	public static String formatPriceAbove(double price) {
		// log base 10 of the max step
		double stepDec = -2;
		for (double price2 = price; price2 >= 100; price2 /= 10) {
			stepDec++;
		}
		double step = Math.pow(10, stepDec);
		int significant = (int) Math.ceil(price / step);
		double res=significant * step;
		// System.err.println("from=" + price + " stepdec=" + stepDec + " step=" +
		// step + " significant=" + significant+" res="+res);
		return formatPriceToEve.format(res);
	}
}
