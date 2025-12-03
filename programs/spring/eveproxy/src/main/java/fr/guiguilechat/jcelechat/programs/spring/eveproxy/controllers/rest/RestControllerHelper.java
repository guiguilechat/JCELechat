package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.util.HexNumberFormat;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Week;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.internal.chartpart.Chart;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.aggregate.DateAggregation;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.YamlMessageConverter;
import fr.guiguilechat.tools.FormatTools;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class RestControllerHelper {

	@Getter
	@RequiredArgsConstructor
	public enum ACCEPT_TEXT {
		js(MediaType.APPLICATION_JSON),
		json(MediaType.APPLICATION_JSON),
		xml(MediaType.APPLICATION_XML),
		yaml(YamlMessageConverter.APPLICATION_YAML),
		yml(YamlMessageConverter.APPLICATION_YAML);

		private final MediaType mediaType;
	}

	/**
	 * presents data as a response depending on the accept value.
	 *
	 * @return a new responsentity with data provided, and content Type matching the
	 *           accept if provided. Default is json.
	 */
	public static <T> ResponseEntity<T> makeResponse(T data, Optional<ACCEPT_TEXT> accept) {
		if (accept == null || accept.isEmpty()) {
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(accept.get().getMediaType());
		return new ResponseEntity<>(data, responseHeaders, HttpStatus.OK);
	}

	public static void setResponseTitle(HttpServletResponse response, String title) {
		if (title != null) {
			response.setHeader("Content-Disposition", "inline; filename=" + title);
		}
	}

	public static void addResponseJFreeChart(
			HttpServletResponse response, JFreeChart chart, Optional<String> accept, int width, int height)
					throws IOException {
		switch (accept.orElse("png").toLowerCase()) {
		case "jpg":
		case "jpeg":
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			ChartUtils.writeBufferedImageAsJPEG(response.getOutputStream(),
					chart.createBufferedImage(width, height, BufferedImage.TYPE_INT_RGB, null));
			break;
		case "png":
		default:
			response.setContentType(MediaType.IMAGE_PNG_VALUE);
			ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), chart.createBufferedImage(width, height));
		}
	}

	public static void addResponseJFreeChart(
			HttpServletResponse response, JFreeChart chart, Optional<String> accept) throws IOException {
		addResponseJFreeChart(response, chart, accept, 2000, 1000);
	}

	public static void addResponseXChart(
			HttpServletResponse response, Chart<?, ?> chart, Optional<String> accept) throws IOException {
		BitmapFormat format = 	switch (accept.orElse("png").toLowerCase()) {
		case "bp", "bmp" -> BitmapFormat.BMP;
		case "gf", "gif" -> BitmapFormat.GIF;
		case "jpg", "jpeg" -> BitmapFormat.JPG;
		case "png" -> BitmapFormat.PNG;
		default ->BitmapFormat.PNG;
		};
		BitmapEncoder.saveBitmap(chart, response.getOutputStream(), format);
	}

	/**
	 * A number format that shows prices using
	 * {@link FormatTools#formatPrice(double)}. Designed to be passed as a
	 * parameter to an {@link NumberAxis#setNumberFormatOverride(NumberFormat)}, to
	 * make the chart show prices correctly
	 */
	public static final NumberFormat NUMBER_FORMAT_PRICES = new HexNumberFormat() {
		@Override
		public StringBuffer format(long number, StringBuffer toAppendTo,
				FieldPosition pos) {
			String formatted = FormatTools.formatPrice(number);
			return new StringBuffer(formatted);
		}
	};

	/**
	 * @param days        optional povided number of days
	 * @param defaultDays days to consider when they are not provided
	 * @return the instant start of X days before, with X being provided in the
	 *         optional or defaulting to the param, then being floored by 0 ;
	 */
	public static Instant sinceDefault(Optional<Integer> days, int defaultDays) {
		return since(days == null || days.isEmpty() ? defaultDays : days.get());
	}

	public static Instant since(int days) {
		if (days < 0) {
			days=0;
		}
		return Instant.now().truncatedTo(ChronoUnit.DAYS).minus(days, ChronoUnit.DAYS);

	}

	public static RegularTimePeriod convert(DateAggregation aggreg, OffsetDateTime date) {
		return switch (aggreg) {
		case hourly -> new Hour(Date.from(date.toInstant()));
		case daily -> new Day(Date.from(date.toInstant()));
		case weekly -> new Week(Date.from(date.toInstant()));
		case monthly -> new Month(Date.from(date.toInstant()));
		default ->
			throw new IllegalArgumentException("Unexpected value: " + aggreg);
		};
	}

}
