package com.glarimy.streams;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ObjectStreamTest {
	static List<Book> books = new ArrayList<>();
	Stream<Book> stream;

	@BeforeClass
	public static void init() {
		// have a list of books
		books.add(new Book(1970, 100, "Java 2", "Mohan"));
		books.add(new Book(1971, 150, "Java 8", "Krishna"));
		books.add(new Book(1972, 120, "Java 7", "Krishna"));
		books.add(new Book(1973, 200, "Java Rest Services", "Krishna"));
		books.add(new Book(1974, 170, "XML Web Services", "Raman"));
		books.add(new Book(1975, 120, "Unit Testing", "Raman"));
		books.add(new Book(1976, 500, "Design Patterns", "Krishna"));
		books.add(new Book(1977, 70, "Java Design Patterns", "Krishna"));
		books.add(new Book(1978, 250, "Alchemist", "Palo"));
		books.add(new Book(1979, 90, "C++", "Raman"));
	}

	@Before
	public void setup() {
		// have a stream of books
		stream = books.stream();
	}

	@Test
	public void testCount() {
		// get the number of java books written by Raman that are priced for
		// more than Rs. 100
		assertTrue((stream.filter(
				b -> b.author.equalsIgnoreCase("Raman") && b.title.toLowerCase().contains("java") && b.price > 100)
				.count()) == 0);
	}

	@Test
	public void testGrouping() {
		// create a catalog of books written by various authors
		Map<String, List<Book>> catalog = stream.collect(Collectors.groupingBy(b -> b.author));
		assertTrue(catalog.containsKey("Krishna"));
		assertTrue(catalog.containsKey("Mohan"));
		assertTrue(catalog.containsKey("Palo"));
		assertTrue(catalog.containsKey("Raman"));
		assertTrue(catalog.get("Krishna").size() == 5);
		assertTrue(catalog.get("Mohan").size() == 1);
		assertTrue(catalog.get("Raman").size() == 3);
		assertTrue(catalog.get("Palo").size() == 1);
	}

	@Test
	public void testMapping() {
		// get the list of ISBNs of the books priced for more than Rs. 100 after
		// applying 10% discount to all the books that were originally priced
		// for
		// more than Rs. 100
		List<Integer> list = stream.peek(b -> b.setPrice((b.price > 100 ? b.price * 0.9 : b.price)))
				.filter(b -> b.price > 100).map(b -> b.isbn).collect(Collectors.toList());
		assertTrue(list.size() == 7);
		assertTrue(list.stream().noneMatch(i -> i == 1970));
		assertTrue(list.stream().noneMatch(i -> i == 1977));
		assertTrue(list.stream().noneMatch(i -> i == 1979));
	}

	@Test
	public void testAverage() {
		// find the average price of books written by Raman
		assertTrue(stream.parallel().filter(b -> b.author.equalsIgnoreCase("raman")).mapToDouble(b -> b.price).average()
				.getAsDouble() == 380 / 3.0);
	}

}
