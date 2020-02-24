package com.jkaref.wawi;

import com.jkaref.wawi.api.ProductLocation;
import com.jkaref.wawi.backend.Product;
import com.jkaref.wawi.backend.ProductRepository;
import com.jkaref.wawi.crawler.Crawler;
import com.jkaref.wawi.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;



@SpringBootApplication
@EnableMongoRepositories
public class Application implements CommandLineRunner {

	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);


	@Autowired
	private WawiProperties properties;

	@Autowired
	private ProductRepository productRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		LOG.info("[run] - Running crawler application ...");
		LOG.info("[run] - CONFIG - Product api -> {}", properties.apiurl);
		LOG.info("[run] - CONFIG - Product prefix -> {}", properties.prefix);

		LOG.info("[run] - CONFIG - Heartbeat interval -> {}", properties.heartbeat);
		
		final long start = System.currentTimeMillis();

		if(properties.enable) {

			LOG.info("starting crawler with rest api");

			final InputStream xmlInputStream = Crawler.crawlProductLocations(properties.sitemap);
			final List<ProductLocation> locations = Parser.parseProductLocations(xmlInputStream);

			LOG.info("[run] - Crawling {} locations, this may take some time ...",
					locations != null ? locations.size() : "0");

			int counter = 0;
			LOG.info("[run] - ... beep {} ... ", counter);

			for (ProductLocation location : locations) {

				try {
					final InputStream jsonInputStream = Crawler.crawlProduct(location,
							properties.apiurl, properties.prefix);

					final Product product = Parser.parseProduct(jsonInputStream);

					productRepository.save(product);

				} catch (IOException e) {
					LOG.error("[run] - ... failed to crawl location {}, skipping ...", location);
					LOG.debug("[run] - IOException {}", e);
				}

				counter++;
				if (counter % properties.heartbeat == 0) {

					final long heartBeat = System.currentTimeMillis();
					final long rate = counter / ((heartBeat - start) / 1000);

					LOG.info("[run] - ... beep {} ({} products/s) ... ", counter, rate);
				}
			}

			final long end = System.currentTimeMillis();

			LOG.info("[run] - Finished crawling in {} ms.", end - start);
		}else{
			LOG.info("starting only rest api");
		}
	}
	
}
