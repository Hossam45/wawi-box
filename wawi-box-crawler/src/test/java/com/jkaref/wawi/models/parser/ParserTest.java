package com.jkaref.wawi.models.parser;

import com.jkaref.wawi.api.ProductLocation;
import com.jkaref.wawi.parser.Parser;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {
	
	private static final List<ProductLocation> EXPECTED_LOCATIONS = Arrays.asList(
			new ProductLocation("https://wawibox.de/preisvergleich/seralene-blau-dss-15-6-0-packung-24-stueck"),
			new ProductLocation("https://wawibox.de/preisvergleich/cavex-cream-alginat-1-beutel-a-500-g"),
			new ProductLocation("https://wawibox.de/preisvergleich/einmalhandschuhe-absogel-latex-unsteril-puderfrei-gr-7-5-packung-25-paar"));

	@Test
	public void testParseProductLocations() throws FileNotFoundException, XMLStreamException {
		
		// Setup inital state of system under test (SUT)
		final InputStream in = new FileInputStream("src/test/resources/sample-product-locations.xml");
		// Change state of system under test
		final List<ProductLocation> locations = Parser.parseProductLocations(in);
	
		// Assert that state changed as expected 
		assertThat(locations)
			.isNotNull()
			.isNotEmpty()
			.hasSameSizeAs(EXPECTED_LOCATIONS)
			.hasSameElementsAs(EXPECTED_LOCATIONS);
		
		// Cleanup (optional)
	}


}
