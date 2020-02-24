package com.jkaref.wawi.parser;

import com.jkaref.wawi.api.ProductLocation;
import com.jkaref.wawi.backend.Product;
import org.apache.commons.io.IOUtils;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Parser {
	
	private static final Logger LOG = LoggerFactory.getLogger(Parser.class);
	
	private static final String LOC = "loc";
	private static final String URL = "url";
		
	private Parser() {}
	
	public static List<ProductLocation> parseProductLocations(InputStream in) throws XMLStreamException {
		
		final List<ProductLocation> locations = new ArrayList<>();

		final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		final XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

		ProductLocation productLocation = null;

		while (eventReader.hasNext()) {

			XMLEvent event = eventReader.nextEvent();

			if (event.isStartElement()) {
				
				final StartElement startElement = event.asStartElement();

				if (startElement.getName().getLocalPart().equalsIgnoreCase(URL)) {

					productLocation = new ProductLocation();
				}

				if (event.asStartElement().getName().getLocalPart().equalsIgnoreCase(LOC)) {
					event = eventReader.nextEvent();
					productLocation.setLocation(event.asCharacters().getData());
					locations.add(productLocation);
				}

			}

		}
		
		LOG.debug("[parseProductLocations] - Found {} product{}.", 
				locations.size(), locations.size() != 1 ? "s" : "");

		return locations;
	}
	
	public static Product parseProduct(final InputStream jsonInputStream) throws IOException, ParseException {

		final String json = IOUtils.toString(
				jsonInputStream, StandardCharsets.UTF_8);

		LOG.debug("[parseProduct] - Found json content {}.",
				json.substring(0, Math.min(json.length(), 40)));

		return new Product(json);
		
	}

}
