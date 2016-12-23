package com.glarimy.streams;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class PrimitiveStreamTest {
	private Stream<Integer> stream;

	@Before
	public void setup() {
		// create a stream on-the-fly
		stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
	}

	@Test
	public void testPartitions() {
		// prepare a map of even and odd numbers from the stream
		Map<Boolean, List<Integer>> map = stream.collect(Collectors.partitioningBy(n -> n % 2 == 0));
		map.get(true).stream().forEach(n -> assertTrue(n % 2 == 0));
		map.get(false).stream().forEach(n -> assertTrue(n % 2 != 0));
	}

	@Test
	public void testAllMatches() {
		// verify if all the numbers are multiples of 3
		assertFalse(stream.allMatch(n -> n % 3 == 0));
	}

	@Test
	public void testAnyMatch() {
		// verify if any number is a multiplier of 3
		assertTrue(stream.anyMatch(n -> n % 3 == 0));
	}

	@Test
	public void testSorting() {
		// sort in descending order
		assertTrue(stream.sorted((f, s) -> s - f).reduce(100, (f, s) -> (f > s ? 100 : 0)) == 100);
	}

	@Test
	public void testTotal() {
		// find the total of first 4 even numbers
		assertTrue(stream.parallel().filter(n -> n % 2 == 0).limit(4).reduce(0, (f, s) -> f + s) == 20);
	}

}
