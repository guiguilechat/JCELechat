package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.YamlMessageConverter;
import jakarta.servlet.http.HttpServletResponse;

public class RestControllerHelper {

	/**
	 * presents data as a response depending on the accept value.
	 *
	 * @return a new responsentity with data provided, and content Type matching the
	 *           accept if provided. Default is json.
	 */
	public static <T> ResponseEntity<T> makeResponse(T data, Optional<String> accept) {
		if (accept.isEmpty()) {
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		switch (accept.orElse("json").toLowerCase()) {
			case "xml":
				responseHeaders.setContentType(MediaType.APPLICATION_XML);
			break;
			case "yaml":
			case "yml":
				responseHeaders.setContentType(YamlMessageConverter.APPLICATION_YAML);
// responseHeaders.setContentDisposition(ContentDisposition.inline().build());
			break;
			case "jason":
			case "js":
			case "json":
			default:
				responseHeaders.setContentType(MediaType.APPLICATION_JSON);
			break;
		}
		return new ResponseEntity<>(data, responseHeaders, HttpStatus.OK);
	}

	public static void addResponseChart(
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
				ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), chart.createBufferedImage(2000, 1000));
		}
	}

	public static void addResponseChart(
			HttpServletResponse response, JFreeChart chart, Optional<String> accept) throws IOException {
		addResponseChart(response, chart, accept, 2000, 1000);
	}

}
