/**   
 * Copyright 2011 The Buzz Media, LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thebuzzmedia.common.util;

import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Naming of test cases (BaB) needs to be flipped to match the refactored
// method sigs, otherwise the world will end.
public class ArrayUtilsTest {
	public static final byte[] BARRAY = { -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 };

	public static final byte[] BVALUE_N5 = { -5 };
	public static final byte[] BVALUE_0 = { 0 };
	public static final byte[] BVALUE_5 = { 5 };

	public static final byte[] BVALUES_NEG = { -5, -4, -3, -2, -1 };
	public static final byte[] BVALUES_ZERO = { 0 };
	public static final byte[] BVALUES_POS = { 1, 2, 3, 4, 5 };

	public static final byte[] BREPEAT = { -3, -3, -3, 0, 0, 5, 5, 5 };
	public static final byte[] BREPEAT_NEG = { -3, -3, -3 };
	public static final byte[] BREPEAT_ZERO = { 0, 0 };
	public static final byte[] BREPEAT_POS = { 5, 5, 5 };
	public static final byte[] BREPEAT_ANY = { -3, 0, 5 };

	public static final char[] CARRAY = { 'a', 'b', 'c', 'd', 'e', '0', '1',
			'2', '3', '4', '5' };

	public static final char[] CVALUE_N5 = { 'a' };
	public static final char[] CVALUE_0 = { '0' };
	public static final char[] CVALUE_5 = { '5' };

	public static final char[] CVALUES_NEG = { 'a', 'b', 'c', 'd', 'e' };
	public static final char[] CVALUES_ZERO = { '0' };
	public static final char[] CVALUES_POS = { '1', '2', '3', '4', '5' };

	public static final char[] CREPEAT = { 'a', 'a', 'a', 'h', 'h', 'z', 'z',
			'z' };
	public static final char[] CREPEAT_NEG = { 'a', 'a', 'a' };
	public static final char[] CREPEAT_ZERO = { 'h', 'h' };
	public static final char[] CREPEAT_POS = { 'z', 'z', 'z' };
	public static final char[] CREPEAT_ANY = { 'a', 'h', 'z' };

	@Test
	public void testAppendBaBa() {
		try {
			ArrayUtils.append((byte[]) null, (byte[]) null);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.append((byte[]) null, BARRAY);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.append(BARRAY, (byte[]) null);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		byte[] array = new byte[0];

		array = ArrayUtils.append(BVALUES_NEG, array);
		array = ArrayUtils.append(BVALUES_ZERO, array);
		array = ArrayUtils.append(BVALUES_POS, array);

		assertEquals(BARRAY.length, array.length);
		assertTrue(ArrayUtils.equals(BARRAY, array));
	}

	@Test
	public void testInsertBaBaI() {
		try {
			ArrayUtils.insert((byte[]) null, (byte[]) null, 0);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.insert((byte[]) null, BARRAY, 0);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.insert(BARRAY, (byte[]) null, 0);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		byte[] array = new byte[0];

		array = ArrayUtils.insert(BVALUES_NEG, array, 0);
		array = ArrayUtils.insert(BVALUES_ZERO, array, 0);
		array = ArrayUtils.insert(BVALUES_POS, array, 0);

		assertEquals(BARRAY.length, array.length);

		assertTrue(ArrayUtils.equals(BVALUES_POS, 0, array, 0,
				BVALUES_POS.length));
		assertTrue(ArrayUtils.equals(BVALUES_ZERO, 0, array,
				BVALUES_POS.length, BVALUES_ZERO.length));
		assertTrue(ArrayUtils.equals(BVALUES_NEG, 0, array, BVALUES_POS.length
				+ BVALUES_ZERO.length, BVALUES_NEG.length));
	}

	@Test
	public void testEnsureCapacityIBa() {
		assertEquals(BARRAY,
				ArrayUtils.ensureCapacity(BARRAY, BARRAY.length - 1));
		assertNotSame(BARRAY, ArrayUtils.ensureCapacity(BARRAY, BARRAY.length));

		byte[] array = ArrayUtils.ensureCapacity(BARRAY, BARRAY.length);
		assertEquals(BARRAY.length, array.length);
		assertTrue(ArrayUtils.equals(BARRAY, 0, array, 0, BARRAY.length));
	}

	@Test
	public void testEnsureCapacityIFBa() {
		assertEquals(BARRAY,
				ArrayUtils.ensureCapacity(BARRAY, BARRAY.length - 1, 2));
		assertNotSame(BARRAY,
				ArrayUtils.ensureCapacity(BARRAY, BARRAY.length, 2));

		byte[] array = ArrayUtils.ensureCapacity(BARRAY, BARRAY.length, 2);
		assertEquals(BARRAY.length * 2, array.length);
		assertTrue(ArrayUtils.equals(BARRAY, 0, array, 0, BARRAY.length));
	}

	/*
	 * ========================================================================
	 * byte[] array, byte value
	 * ========================================================================
	 */

	@Test
	public void testEqualsBaBa() {
		assertFalse(ArrayUtils.equals(BARRAY, (byte[]) null));
		assertFalse(ArrayUtils.equals((byte[]) null, BARRAY));
		assertFalse(ArrayUtils.equals(BARRAY, BVALUES_POS));

		assertTrue(ArrayUtils.equals((byte[]) null, (byte[]) null));
		assertTrue(ArrayUtils.equals(BARRAY, BARRAY));

		byte[] copy = new byte[BARRAY.length];
		System.arraycopy(BARRAY, 0, copy, 0, BARRAY.length);

		assertTrue(ArrayUtils.equals(copy, BARRAY));
	}

	@Test
	public void testEqualsIBaIBaI() {
		assertFalse(ArrayUtils.equals(null, 0, BARRAY, 0, BARRAY.length));
		assertFalse(ArrayUtils.equals(BARRAY, 0, null, 0, BARRAY.length));

		assertTrue(ArrayUtils.equals(BARRAY, 0, BARRAY, 0, BARRAY.length));
		assertTrue(ArrayUtils.equals(BVALUES_NEG, 0, BARRAY, 0,
				BVALUES_NEG.length));
		assertTrue(ArrayUtils.equals(BVALUES_POS, 0, BARRAY,
				BVALUES_NEG.length + 1, BVALUES_POS.length));
	}

	@Test
	public void testIndexOfBaB() {
		try {
			ArrayUtils.indexOf((byte) 0, null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOf((byte) -5, BARRAY));
		assertEquals(5, ArrayUtils.indexOf((byte) 0, BARRAY));
		assertEquals(10, ArrayUtils.indexOf((byte) 5, BARRAY));
	}

	@Test
	public void testIndexOfIBaB() {
		try {
			ArrayUtils.indexOf((byte) 0, null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf((byte) 0, BARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOf((byte) -5, BARRAY, 0));
		assertEquals(5, ArrayUtils.indexOf((byte) 0, BARRAY, 0));
		assertEquals(10, ArrayUtils.indexOf((byte) 5, BARRAY, 0));

		assertEquals(-1, ArrayUtils.indexOf((byte) -5, BARRAY, 5));
		assertEquals(5, ArrayUtils.indexOf((byte) 0, BARRAY, 5));
		assertEquals(10, ArrayUtils.indexOf((byte) 5, BARRAY, 5));

		assertEquals(-1, ArrayUtils.indexOf((byte) -5, BARRAY, 10));
		assertEquals(-1, ArrayUtils.indexOf((byte) 0, BARRAY, 10));
		assertEquals(10, ArrayUtils.indexOf((byte) 5, BARRAY, 10));
	}

	@Test
	public void testIndexOfIIBaB() {
		try {
			ArrayUtils.indexOf((byte) 0, null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf((byte) 0, BARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf((byte) 0, BARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf((byte) 0, BARRAY, 0, BARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOf((byte) -5, BARRAY, 0, BARRAY.length));
		assertEquals(5, ArrayUtils.indexOf((byte) 0, BARRAY, 0, BARRAY.length));
		assertEquals(10, ArrayUtils.indexOf((byte) 5, BARRAY, 0, BARRAY.length));

		assertEquals(-1,
				ArrayUtils.indexOf((byte) -5, BARRAY, 5, BARRAY.length - 5));
		assertEquals(5,
				ArrayUtils.indexOf((byte) 0, BARRAY, 5, BARRAY.length - 5));
		assertEquals(10,
				ArrayUtils.indexOf((byte) 5, BARRAY, 5, BARRAY.length - 5));

		assertEquals(-1, ArrayUtils.indexOf((byte) -5, BARRAY, 10, 1));
		assertEquals(-1, ArrayUtils.indexOf((byte) 0, BARRAY, 10, 1));
		assertEquals(10, ArrayUtils.indexOf((byte) 5, BARRAY, 10, 1));
	}

	/*
	 * ========================================================================
	 * byte[] array, byte[] values
	 * ========================================================================
	 */
	@Test
	public void testIndexOfBaBa() {
		try {
			ArrayUtils.indexOf(BVALUES_ZERO, null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOf(BVALUES_NEG, BARRAY));
		assertEquals(5, ArrayUtils.indexOf(BVALUES_ZERO, BARRAY));
		assertEquals(6, ArrayUtils.indexOf(BVALUES_POS, BARRAY));
	}

	@Test
	public void testIndexOfIBaBa() {
		try {
			ArrayUtils.indexOf(BVALUES_ZERO, null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf(BVALUES_ZERO, BARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOf(BVALUES_NEG, BARRAY, 0));
		assertEquals(5, ArrayUtils.indexOf(BVALUES_ZERO, BARRAY, 0));
		assertEquals(6, ArrayUtils.indexOf(BVALUES_POS, BARRAY, 0));

		assertEquals(-1, ArrayUtils.indexOf(BVALUES_NEG, BARRAY, 5));
		assertEquals(5, ArrayUtils.indexOf(BVALUES_ZERO, BARRAY, 5));
		assertEquals(6, ArrayUtils.indexOf(BVALUES_POS, BARRAY, 6));

		assertEquals(-1, ArrayUtils.indexOf(BVALUES_NEG, BARRAY, 6));
		assertEquals(-1, ArrayUtils.indexOf(BVALUES_ZERO, BARRAY, 10));
	}

	@Test
	public void testIndexOfIIBaBa() {
		try {
			ArrayUtils.indexOf(BVALUES_ZERO, null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf(BVALUES_ZERO, BARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf(BVALUES_ZERO, BARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf(BVALUES_ZERO, BARRAY, 0, BARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(-1, ArrayUtils.indexOf(new byte[] { 80 }, BARRAY));

		assertEquals(0,
				ArrayUtils.indexOf(BVALUES_NEG, BARRAY, 0, BARRAY.length));
		assertEquals(5,
				ArrayUtils.indexOf(BVALUES_ZERO, BARRAY, 0, BARRAY.length));
		assertEquals(6,
				ArrayUtils.indexOf(BVALUES_POS, BARRAY, 0, BARRAY.length));

		assertEquals(-1,
				ArrayUtils.indexOf(BVALUES_NEG, BARRAY, 5, BARRAY.length - 5));
		assertEquals(5,
				ArrayUtils.indexOf(BVALUES_ZERO, BARRAY, 5, BARRAY.length - 5));
		assertEquals(6,
				ArrayUtils.indexOf(BVALUES_POS, BARRAY, 5, BARRAY.length - 5));

		assertEquals(-1, ArrayUtils.indexOf(BVALUES_NEG, BARRAY, 6, 5));
		assertEquals(5, ArrayUtils.indexOf(BVALUES_ZERO, BARRAY, 5, 1));
		assertEquals(-1, ArrayUtils.indexOf(BVALUES_POS, BARRAY, 2, 5));

		try {
			/*
			 * Test that the minimum length requirement for testing all of the
			 * values array is enforced; because the length arg applies to HOW
			 * MUCH to search in the array, to find the entire values array. Not
			 * some subset of each.
			 */
			assertEquals(0, ArrayUtils.indexOf(BVALUES_NEG, BARRAY, 0, 3));
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/*
	 * ========================================================================
	 * byte[] array, ANY byte[] values
	 * ========================================================================
	 */
	@Test
	public void testIndexOfAnyBaBa() {
		try {
			ArrayUtils.indexOfAny(BVALUE_0, null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOfAny(BVALUE_N5, BARRAY));
		assertEquals(5, ArrayUtils.indexOfAny(BVALUE_0, BARRAY));
		assertEquals(10, ArrayUtils.indexOfAny(BVALUE_5, BARRAY));
	}

	@Test
	public void testIndexOfAnyIBaBa() {
		try {
			ArrayUtils.indexOfAny(BVALUE_0, null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOfAny(BVALUE_0, BARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOfAny(BVALUE_N5, BARRAY, 0));
		assertEquals(5, ArrayUtils.indexOfAny(BVALUE_0, BARRAY, 0));
		assertEquals(10, ArrayUtils.indexOfAny(BVALUE_5, BARRAY, 0));

		assertEquals(-1, ArrayUtils.indexOfAny(BVALUE_N5, BARRAY, 5));
		assertEquals(5, ArrayUtils.indexOfAny(BVALUE_0, BARRAY, 5));
		assertEquals(10, ArrayUtils.indexOfAny(BVALUE_5, BARRAY, 5));

		assertEquals(-1, ArrayUtils.indexOfAny(BVALUE_N5, BARRAY, 10));
		assertEquals(-1, ArrayUtils.indexOfAny(BVALUE_0, BARRAY, 10));
		assertEquals(10, ArrayUtils.indexOfAny(BVALUE_5, BARRAY, 10));
	}

	@Test
	public void testIndexOfAnyIIBaBa() {
		try {
			ArrayUtils.indexOfAny(BVALUE_0, null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOfAny(BVALUE_0, BARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOfAny(BVALUE_0, BARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOfAny(BVALUE_0, BARRAY, 0, BARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0,
				ArrayUtils.indexOfAny(BVALUE_N5, BARRAY, 0, BARRAY.length));
		assertEquals(5,
				ArrayUtils.indexOfAny(BVALUE_0, BARRAY, 0, BARRAY.length));
		assertEquals(10,
				ArrayUtils.indexOfAny(BVALUE_5, BARRAY, 0, BARRAY.length));

		assertEquals(-1,
				ArrayUtils.indexOfAny(BVALUE_N5, BARRAY, 5, BARRAY.length - 5));
		assertEquals(5,
				ArrayUtils.indexOfAny(BVALUE_0, BARRAY, 5, BARRAY.length - 5));
		assertEquals(10,
				ArrayUtils.indexOfAny(BVALUE_5, BARRAY, 5, BARRAY.length - 5));

		assertEquals(-1, ArrayUtils.indexOfAny(BVALUE_N5, BARRAY, 10, 1));
		assertEquals(-1, ArrayUtils.indexOfAny(BVALUE_0, BARRAY, 10, 1));
		assertEquals(10, ArrayUtils.indexOfAny(BVALUE_5, BARRAY, 10, 1));
	}

	/*
	 * ========================================================================
	 * REVERSE byte[] array, byte value
	 * ========================================================================
	 */
	@Test
	public void testLastIndexOfBaB() {
		try {
			ArrayUtils.lastIndexOf((byte) 0, null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOf((byte) -5, BARRAY));
		assertEquals(5, ArrayUtils.lastIndexOf((byte) 0, BARRAY));
		assertEquals(10, ArrayUtils.lastIndexOf((byte) 5, BARRAY));
	}

	@Test
	public void testLastIndexOfIBaB() {
		try {
			ArrayUtils.lastIndexOf((byte) 0, null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf((byte) 0, BARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOf((byte) -5, BARRAY, 0));
		assertEquals(5, ArrayUtils.lastIndexOf((byte) 0, BARRAY, 0));
		assertEquals(10, ArrayUtils.lastIndexOf((byte) 5, BARRAY, 0));

		assertEquals(-1, ArrayUtils.lastIndexOf((byte) -5, BARRAY, 5));
		assertEquals(5, ArrayUtils.lastIndexOf((byte) 0, BARRAY, 5));
		assertEquals(10, ArrayUtils.lastIndexOf((byte) 5, BARRAY, 5));

		assertEquals(-1, ArrayUtils.lastIndexOf((byte) -5, BARRAY, 10));
		assertEquals(-1, ArrayUtils.lastIndexOf((byte) 0, BARRAY, 10));
		assertEquals(10, ArrayUtils.lastIndexOf((byte) 5, BARRAY, 10));
	}

	@Test
	public void testLastIndexOfIIBaB() {
		try {
			ArrayUtils.lastIndexOf((byte) 0, null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf((byte) 0, BARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf((byte) 0, BARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf((byte) 0, BARRAY, 0, BARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0,
				ArrayUtils.lastIndexOf((byte) -5, BARRAY, 0, BARRAY.length));
		assertEquals(5,
				ArrayUtils.lastIndexOf((byte) 0, BARRAY, 0, BARRAY.length));
		assertEquals(10,
				ArrayUtils.lastIndexOf((byte) 5, BARRAY, 0, BARRAY.length));

		assertEquals(-1,
				ArrayUtils.lastIndexOf((byte) -5, BARRAY, 5, BARRAY.length - 5));
		assertEquals(5,
				ArrayUtils.lastIndexOf((byte) 0, BARRAY, 5, BARRAY.length - 5));
		assertEquals(10,
				ArrayUtils.lastIndexOf((byte) 5, BARRAY, 5, BARRAY.length - 5));

		assertEquals(-1, ArrayUtils.lastIndexOf((byte) -5, BARRAY, 10, 1));
		assertEquals(-1, ArrayUtils.lastIndexOf((byte) 0, BARRAY, 10, 1));
		assertEquals(10, ArrayUtils.lastIndexOf((byte) 5, BARRAY, 10, 1));
	}

	/*
	 * ========================================================================
	 * REVERSE byte[] array, byte[] values
	 * ========================================================================
	 */
	@Test
	public void testLastIndexOfBaBa() {
		try {
			ArrayUtils.lastIndexOf(BVALUES_ZERO, null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOf(BVALUES_NEG, BARRAY));
		assertEquals(5, ArrayUtils.lastIndexOf(BVALUES_ZERO, BARRAY));
		assertEquals(6, ArrayUtils.lastIndexOf(BVALUES_POS, BARRAY));
	}

	@Test
	public void testLastIndexOfIBaBa() {
		try {
			ArrayUtils.lastIndexOf(BVALUES_ZERO, null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf(BVALUES_ZERO, BARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOf(BVALUES_NEG, BARRAY, 0));
		assertEquals(5, ArrayUtils.lastIndexOf(BVALUES_ZERO, BARRAY, 0));
		assertEquals(6, ArrayUtils.lastIndexOf(BVALUES_POS, BARRAY, 0));

		assertEquals(-1, ArrayUtils.lastIndexOf(BVALUES_NEG, BARRAY, 5));
		assertEquals(5, ArrayUtils.lastIndexOf(BVALUES_ZERO, BARRAY, 5));
		assertEquals(6, ArrayUtils.lastIndexOf(BVALUES_POS, BARRAY, 6));

		assertEquals(-1, ArrayUtils.lastIndexOf(BVALUES_NEG, BARRAY, 6));
		assertEquals(-1, ArrayUtils.lastIndexOf(BVALUES_ZERO, BARRAY, 10));
	}

	@Test
	public void testLastIndexOfIIBaBa() {
		try {
			ArrayUtils.lastIndexOf(BVALUES_ZERO, null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf(BVALUES_ZERO, BARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf(BVALUES_ZERO, BARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf(BVALUES_ZERO, BARRAY, 0, BARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0,
				ArrayUtils.lastIndexOf(BVALUES_NEG, BARRAY, 0, BARRAY.length));
		assertEquals(5,
				ArrayUtils.lastIndexOf(BVALUES_ZERO, BARRAY, 0, BARRAY.length));
		assertEquals(6,
				ArrayUtils.lastIndexOf(BVALUES_POS, BARRAY, 0, BARRAY.length));

		assertEquals(-1, ArrayUtils.lastIndexOf(BVALUES_NEG, BARRAY, 5,
				BARRAY.length - 5));
		assertEquals(5, ArrayUtils.lastIndexOf(BVALUES_ZERO, BARRAY, 5,
				BARRAY.length - 5));
		assertEquals(6, ArrayUtils.lastIndexOf(BVALUES_POS, BARRAY, 5,
				BARRAY.length - 5));

		assertEquals(-1, ArrayUtils.lastIndexOf(BVALUES_NEG, BARRAY, 6, 5));
		assertEquals(5, ArrayUtils.lastIndexOf(BVALUES_ZERO, BARRAY, 5, 1));
		assertEquals(-1, ArrayUtils.lastIndexOf(BVALUES_POS, BARRAY, 2, 5));
	}

	/*
	 * ========================================================================
	 * REVERSE byte[] array, ANY byte[] values
	 * ========================================================================
	 */
	@Test
	public void testLastIndexOfAnyBaBa() {
		try {
			ArrayUtils.lastIndexOfAny(BVALUE_0, null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOfAny(BVALUE_N5, BARRAY));
		assertEquals(5, ArrayUtils.lastIndexOfAny(BVALUE_0, BARRAY));
		assertEquals(10, ArrayUtils.lastIndexOfAny(BVALUE_5, BARRAY));
	}

	@Test
	public void testLastIndexOfAnyIBaBa() {
		try {
			ArrayUtils.lastIndexOfAny(BVALUE_0, null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOfAny(BVALUE_0, BARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOfAny(BVALUE_N5, BARRAY, 0));
		assertEquals(5, ArrayUtils.lastIndexOfAny(BVALUE_0, BARRAY, 0));
		assertEquals(10, ArrayUtils.lastIndexOfAny(BVALUE_5, BARRAY, 0));

		assertEquals(-1, ArrayUtils.lastIndexOfAny(BVALUE_N5, BARRAY, 5));
		assertEquals(5, ArrayUtils.lastIndexOfAny(BVALUE_0, BARRAY, 5));
		assertEquals(10, ArrayUtils.lastIndexOfAny(BVALUE_5, BARRAY, 5));

		assertEquals(-1, ArrayUtils.lastIndexOfAny(BVALUE_N5, BARRAY, 10));
		assertEquals(-1, ArrayUtils.lastIndexOfAny(BVALUE_0, BARRAY, 10));
		assertEquals(10, ArrayUtils.lastIndexOfAny(BVALUE_5, BARRAY, 10));
	}

	@Test
	public void testLastIndexOfAnyIIBaBa() {
		try {
			ArrayUtils.lastIndexOfAny(BVALUE_0, null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOfAny(BVALUE_0, BARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOfAny(BVALUE_0, BARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOfAny(BVALUE_0, BARRAY, 0, BARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0,
				ArrayUtils.lastIndexOfAny(BVALUE_N5, BARRAY, 0, BARRAY.length));
		assertEquals(5,
				ArrayUtils.lastIndexOfAny(BVALUE_0, BARRAY, 0, BARRAY.length));
		assertEquals(10,
				ArrayUtils.lastIndexOfAny(BVALUE_5, BARRAY, 0, BARRAY.length));

		assertEquals(-1, ArrayUtils.lastIndexOfAny(BVALUE_N5, BARRAY, 5,
				BARRAY.length - 5));
		assertEquals(5, ArrayUtils.lastIndexOfAny(BVALUE_0, BARRAY, 5,
				BARRAY.length - 5));
		assertEquals(10, ArrayUtils.lastIndexOfAny(BVALUE_5, BARRAY, 5,
				BARRAY.length - 5));

		assertEquals(-1, ArrayUtils.lastIndexOfAny(BVALUE_N5, BARRAY, 10, 1));
		assertEquals(-1, ArrayUtils.lastIndexOfAny(BVALUE_0, BARRAY, 10, 1));
		assertEquals(10, ArrayUtils.lastIndexOfAny(BVALUE_5, BARRAY, 10, 1));
	}

	@Test
	public void testIndexAfterBaB() {
		assertEquals(0, ArrayUtils.indexAfter(Byte.MAX_VALUE, BARRAY));
		assertEquals(1, ArrayUtils.indexAfter((byte) -5, BARRAY));
		assertEquals(3, ArrayUtils.indexAfter((byte) -3, BREPEAT));
	}

	@Test
	public void testIndexAfterIBaB() {
		assertEquals(0, ArrayUtils.indexAfter(Byte.MAX_VALUE, BARRAY, 0));
		assertEquals(1, ArrayUtils.indexAfter((byte) -5, BARRAY, 0));
		assertEquals(6, ArrayUtils.indexAfter((byte) 0, BARRAY, 5));
		assertEquals(11, ArrayUtils.indexAfter((byte) 5, BARRAY, 10));

		assertEquals(3, ArrayUtils.indexAfter((byte) -3, BREPEAT, 0));
		assertEquals(5, ArrayUtils.indexAfter((byte) 0, BREPEAT, 3));
		assertEquals(8, ArrayUtils.indexAfter((byte) 5, BREPEAT, 5));

		try {
			ArrayUtils.indexAfter((byte) 0, BARRAY, BARRAY.length + 1);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIndexAfterIIBaB() {
		assertEquals(0,
				ArrayUtils.indexAfter(Byte.MAX_VALUE, BARRAY, 0, BARRAY.length));
		assertEquals(1, ArrayUtils.indexAfter((byte) -5, BARRAY, 0, 1));
		assertEquals(6, ArrayUtils.indexAfter((byte) 0, BARRAY, 5, 1));
		assertEquals(11, ArrayUtils.indexAfter((byte) 5, BARRAY, 10, 1));

		assertEquals(1, ArrayUtils.indexAfter((byte) -5, BARRAY, 0, 10));
		assertEquals(6, ArrayUtils.indexAfter((byte) 0, BARRAY, 5, 5));
		assertEquals(11, ArrayUtils.indexAfter((byte) 5, BARRAY, 10, 1));

		assertEquals(2, ArrayUtils.indexAfter((byte) -3, BREPEAT, 0, 2));
		assertEquals(4, ArrayUtils.indexAfter((byte) 0, BREPEAT, 3, 1));
		assertEquals(8, ArrayUtils.indexAfter((byte) 5, BREPEAT, 5, 3));

		try {
			ArrayUtils.indexAfter((byte) 0, BARRAY, -1, BARRAY.length);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexAfter((byte) 0, BARRAY, 5, 20);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIndexAfterBaBa() {
		assertEquals(0, ArrayUtils.indexAfter(new byte[] { 45, 50 }, BARRAY));
		assertEquals(11, ArrayUtils.indexAfter(BARRAY, BARRAY));
		assertEquals(1, ArrayUtils.indexAfter(BVALUE_N5, BARRAY));
		assertEquals(5, ArrayUtils.indexAfter(BVALUES_NEG, BARRAY));

		assertEquals(3, ArrayUtils.indexAfter(BREPEAT_NEG, BREPEAT));
	}

	@Test
	public void testIndexAfterIBaBa() {
		assertEquals(0,
				ArrayUtils.indexAfter(new byte[] { Byte.MAX_VALUE }, BARRAY, 0));
		assertEquals(1, ArrayUtils.indexAfter(BVALUE_N5, BARRAY, 0));
		assertEquals(6, ArrayUtils.indexAfter(BVALUE_0, BARRAY, 5));
		assertEquals(11, ArrayUtils.indexAfter(BVALUE_5, BARRAY, 10));

		assertEquals(3, ArrayUtils.indexAfter(BREPEAT_NEG, BREPEAT, 0));
		assertEquals(5, ArrayUtils.indexAfter(BREPEAT_ZERO, BREPEAT, 3));
		assertEquals(8, ArrayUtils.indexAfter(BREPEAT_POS, BREPEAT, 5));

		try {
			ArrayUtils.indexAfter(BVALUE_N5, BARRAY, BARRAY.length + 1);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIndexAfterIIBaBa() {
		assertEquals(0, ArrayUtils.indexAfter(new byte[] { Byte.MAX_VALUE },
				BARRAY, 0, BARRAY.length));
		assertEquals(1, ArrayUtils.indexAfter(BVALUE_N5, BARRAY, 0, 1));
		assertEquals(6, ArrayUtils.indexAfter(BVALUE_0, BARRAY, 5, 1));
		assertEquals(11, ArrayUtils.indexAfter(BVALUE_5, BARRAY, 10, 1));

		assertEquals(1, ArrayUtils.indexAfter(BVALUE_N5, BARRAY, 0, 10));
		assertEquals(6, ArrayUtils.indexAfter(BVALUE_0, BARRAY, 5, 5));
		assertEquals(11, ArrayUtils.indexAfter(BVALUE_5, BARRAY, 10, 1));

		try {
			/*
			 * Test that the minimum length requirement for testing all of the
			 * values array is enforced; because the length arg applies to HOW
			 * MUCH to search in the array, to find the entire values array. Not
			 * some subset of each.
			 */
			assertEquals(2, ArrayUtils.indexAfter(BREPEAT_NEG, BREPEAT, 0, 2));
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(3, ArrayUtils.indexAfter(BREPEAT_NEG, BREPEAT, 0, 3));
		assertEquals(5, ArrayUtils.indexAfter(BREPEAT_ZERO, BREPEAT, 3, 2));
		assertEquals(8, ArrayUtils.indexAfter(BREPEAT_POS, BREPEAT, 5, 3));

		try {
			ArrayUtils.indexAfter(BVALUE_0, BARRAY, -1, BARRAY.length);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexAfter(BVALUE_0, BARRAY, 5, 20);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIndexAfterAnyBaBa() {
		assertEquals(0, ArrayUtils.indexAfterAny(new byte[] { 77 }, BREPEAT));
		assertEquals(3, ArrayUtils.indexAfterAny(BREPEAT_NEG, BREPEAT));
		assertEquals(8, ArrayUtils.indexAfterAny(BREPEAT_ANY, BREPEAT));
	}

	@Test
	public void testIndexAfterAnyIBaBa() {
		assertEquals(0, ArrayUtils.indexAfterAny(new byte[] { 77 }, BREPEAT, 0));
		assertEquals(5, ArrayUtils.indexAfterAny(BREPEAT_NEG, BREPEAT, 5));

		assertEquals(3, ArrayUtils.indexAfterAny(BREPEAT_NEG, BREPEAT, 1));
		assertEquals(5, ArrayUtils.indexAfterAny(BREPEAT_ZERO, BREPEAT, 3));
		assertEquals(8, ArrayUtils.indexAfterAny(BREPEAT_ANY, BREPEAT, 6));

		try {
			ArrayUtils.indexAfterAny(BREPEAT_ANY, BREPEAT, BREPEAT.length + 1);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIndexAfterAnyIIBaBa() {
		assertEquals(0, ArrayUtils.indexAfterAny(new byte[] { 77 }, BREPEAT, 0,
				BREPEAT.length));
		assertEquals(5, ArrayUtils.indexAfterAny(BREPEAT_NEG, BREPEAT, 5,
				BREPEAT.length - 5));

		assertEquals(3, ArrayUtils.indexAfterAny(BREPEAT_NEG, BREPEAT, 1,
				BREPEAT.length - 1));
		assertEquals(5, ArrayUtils.indexAfterAny(BREPEAT_ZERO, BREPEAT, 3,
				BREPEAT.length - 3));
		assertEquals(8, ArrayUtils.indexAfterAny(BREPEAT_ANY, BREPEAT, 6,
				BREPEAT.length - 6));

		try {
			ArrayUtils.indexAfterAny(BREPEAT_ANY, BREPEAT, 0,
					BREPEAT.length + 1);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexAfterAny(BREPEAT_ANY, BREPEAT, BREPEAT.length + 1,
					2);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/* ############################################################## */
	/* ############################################################## */
	/* ############################################################## */
	/* ############################################################## */
	/* ############################################################## */
	/* ############################################################## */
	/* ############################################################## */
	/* ############################################################## */

	@Test
	public void testAppendCaCa() {
		try {
			ArrayUtils.append((char[]) null, (char[]) null);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.append((char[]) null, CARRAY);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.append(CARRAY, (char[]) null);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		char[] array = new char[0];

		array = ArrayUtils.append(CVALUES_NEG, array);
		array = ArrayUtils.append(CVALUES_ZERO, array);
		array = ArrayUtils.append(CVALUES_POS, array);

		assertEquals(CARRAY.length, array.length);
		assertTrue(ArrayUtils.equals(CARRAY, array));
	}

	@Test
	public void testInsertCaCaI() {
		try {
			ArrayUtils.insert((char[]) null, (char[]) null, 0);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.insert((char[]) null, CARRAY, 0);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.insert(CARRAY, (char[]) null, 0);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		char[] array = new char[0];

		array = ArrayUtils.insert(CVALUES_NEG, array, 0);
		array = ArrayUtils.insert(CVALUES_ZERO, array, 0);
		array = ArrayUtils.insert(CVALUES_POS, array, 0);

		assertEquals(CARRAY.length, array.length);

		assertTrue(ArrayUtils.equals(CVALUES_POS, 0, array, 0,
				CVALUES_POS.length));
		assertTrue(ArrayUtils.equals(CVALUES_ZERO, 0, array,
				CVALUES_POS.length, CVALUES_ZERO.length));
		assertTrue(ArrayUtils.equals(CVALUES_NEG, 0, array, CVALUES_POS.length
				+ CVALUES_ZERO.length, CVALUES_NEG.length));
	}

	@Test
	public void testEnsureCapacityICa() {
		assertEquals(CARRAY,
				ArrayUtils.ensureCapacity(CARRAY, CARRAY.length - 1));
		assertNotSame(CARRAY, ArrayUtils.ensureCapacity(CARRAY, CARRAY.length));

		char[] array = ArrayUtils.ensureCapacity(CARRAY, CARRAY.length);
		assertEquals(CARRAY.length, array.length);
		assertTrue(ArrayUtils.equals(CARRAY, 0, array, 0, CARRAY.length));
	}

	@Test
	public void testEnsureCapacityIFCa() {
		assertEquals(CARRAY,
				ArrayUtils.ensureCapacity(CARRAY, CARRAY.length - 1, 2));
		assertNotSame(CARRAY,
				ArrayUtils.ensureCapacity(CARRAY, CARRAY.length, 2));

		char[] array = ArrayUtils.ensureCapacity(CARRAY, CARRAY.length, 2);
		assertEquals(CARRAY.length * 2, array.length);
		assertTrue(ArrayUtils.equals(CARRAY, 0, array, 0, CARRAY.length));
	}

	@Test
	public void testEqualsCaCa() {
		assertFalse(ArrayUtils.equals(CARRAY, (char[]) null));
		assertFalse(ArrayUtils.equals((char[]) null, CARRAY));
		assertFalse(ArrayUtils.equals(CARRAY, CVALUES_POS));

		assertTrue(ArrayUtils.equals((char[]) null, (char[]) null));
		assertTrue(ArrayUtils.equals(CARRAY, CARRAY));

		char[] copy = new char[CARRAY.length];
		System.arraycopy(CARRAY, 0, copy, 0, CARRAY.length);

		assertTrue(ArrayUtils.equals(copy, CARRAY));
	}

	@Test
	public void testEqualsICaICaI() {
		assertFalse(ArrayUtils.equals(null, 0, CARRAY, 0, CARRAY.length));
		assertFalse(ArrayUtils.equals(CARRAY, 0, null, 0, CARRAY.length));

		assertTrue(ArrayUtils.equals(CARRAY, 0, CARRAY, 0, CARRAY.length));
		assertTrue(ArrayUtils.equals(CVALUES_NEG, 0, CARRAY, 0,
				CVALUES_NEG.length));
		assertTrue(ArrayUtils.equals(CVALUES_POS, 0, CARRAY,
				CVALUES_NEG.length + 1, CVALUES_POS.length));
	}

	/*
	 * ========================================================================
	 * char[] array, char value
	 * ========================================================================
	 */
	@Test
	public void testIndexOfCaC() {
		try {
			ArrayUtils.indexOf((char) 0, null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOf('a', CARRAY));
		assertEquals(5, ArrayUtils.indexOf('0', CARRAY));
		assertEquals(10, ArrayUtils.indexOf('5', CARRAY));
	}

	@Test
	public void testIndexOfICaC() {
		try {
			ArrayUtils.indexOf('0', null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf('0', CARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOf('a', CARRAY, 0));
		assertEquals(5, ArrayUtils.indexOf('0', CARRAY, 0));
		assertEquals(10, ArrayUtils.indexOf('5', CARRAY, 0));

		assertEquals(-1, ArrayUtils.indexOf('a', CARRAY, 5));
		assertEquals(5, ArrayUtils.indexOf('0', CARRAY, 5));
		assertEquals(10, ArrayUtils.indexOf('5', CARRAY, 5));

		assertEquals(-1, ArrayUtils.indexOf('a', CARRAY, 10));
		assertEquals(-1, ArrayUtils.indexOf('0', CARRAY, 10));
		assertEquals(10, ArrayUtils.indexOf('5', CARRAY, 10));
	}

	@Test
	public void testIndexOfIICaC() {
		try {
			ArrayUtils.indexOf('0', null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf('0', CARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf('0', CARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf('0', CARRAY, 0, CARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOf('a', CARRAY, 0, CARRAY.length));
		assertEquals(5, ArrayUtils.indexOf('0', CARRAY, 0, CARRAY.length));
		assertEquals(10, ArrayUtils.indexOf('5', CARRAY, 0, CARRAY.length));

		assertEquals(-1, ArrayUtils.indexOf('a', CARRAY, 5, CARRAY.length - 5));
		assertEquals(5, ArrayUtils.indexOf('0', CARRAY, 5, CARRAY.length - 5));
		assertEquals(10, ArrayUtils.indexOf('5', CARRAY, 5, CARRAY.length - 5));

		assertEquals(-1, ArrayUtils.indexOf('a', CARRAY, 10, 1));
		assertEquals(-1, ArrayUtils.indexOf('0', CARRAY, 10, 1));
		assertEquals(10, ArrayUtils.indexOf('5', CARRAY, 10, 1));
	}

	/*
	 * ========================================================================
	 * char[] array, char[] values
	 * ========================================================================
	 */
	@Test
	public void testIndexOfCaCa() {
		try {
			ArrayUtils.indexOf(CVALUES_ZERO, null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOf(CVALUES_NEG, CARRAY));
		assertEquals(5, ArrayUtils.indexOf(CVALUES_ZERO, CARRAY));
		assertEquals(6, ArrayUtils.indexOf(CVALUES_POS, CARRAY));
	}

	@Test
	public void testIndexOfICaCa() {
		try {
			ArrayUtils.indexOf(CVALUES_ZERO, null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf(CVALUES_ZERO, CARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOf(CVALUES_NEG, CARRAY, 0));
		assertEquals(5, ArrayUtils.indexOf(CVALUES_ZERO, CARRAY, 0));
		assertEquals(6, ArrayUtils.indexOf(CVALUES_POS, CARRAY, 0));

		assertEquals(-1, ArrayUtils.indexOf(CVALUES_NEG, CARRAY, 5));
		assertEquals(5, ArrayUtils.indexOf(CVALUES_ZERO, CARRAY, 5));
		assertEquals(6, ArrayUtils.indexOf(CVALUES_POS, CARRAY, 6));

		assertEquals(-1, ArrayUtils.indexOf(CVALUES_NEG, CARRAY, 6));
		assertEquals(-1, ArrayUtils.indexOf(CVALUES_ZERO, CARRAY, 10));
	}

	@Test
	public void testIndexOfIICaCa() {
		try {
			ArrayUtils.indexOf(CVALUES_ZERO, null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf(CVALUES_ZERO, CARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf(CVALUES_ZERO, CARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOf(CVALUES_ZERO, CARRAY, 0, CARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0,
				ArrayUtils.indexOf(CVALUES_NEG, CARRAY, 0, CARRAY.length));
		assertEquals(5,
				ArrayUtils.indexOf(CVALUES_ZERO, CARRAY, 0, CARRAY.length));
		assertEquals(6,
				ArrayUtils.indexOf(CVALUES_POS, CARRAY, 0, CARRAY.length));

		assertEquals(-1,
				ArrayUtils.indexOf(CVALUES_NEG, CARRAY, 5, CARRAY.length - 5));
		assertEquals(5,
				ArrayUtils.indexOf(CVALUES_ZERO, CARRAY, 5, CARRAY.length - 5));
		assertEquals(6,
				ArrayUtils.indexOf(CVALUES_POS, CARRAY, 5, CARRAY.length - 5));

		assertEquals(-1, ArrayUtils.indexOf(CVALUES_NEG, CARRAY, 6, 5));
		assertEquals(5, ArrayUtils.indexOf(CVALUES_ZERO, CARRAY, 5, 1));
		assertEquals(-1, ArrayUtils.indexOf(CVALUES_POS, CARRAY, 2, 5));
	}

	/*
	 * ========================================================================
	 * char[] array, ANY char[] values
	 * ========================================================================
	 */
	@Test
	public void testIndexOfAnyCaCa() {
		try {
			ArrayUtils.indexOfAny(CVALUE_0, null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOfAny(CVALUE_N5, CARRAY));
		assertEquals(5, ArrayUtils.indexOfAny(CVALUE_0, CARRAY));
		assertEquals(10, ArrayUtils.indexOfAny(CVALUE_5, CARRAY));
	}

	@Test
	public void testIndexOfAnyICaCa() {
		try {
			ArrayUtils.indexOfAny(CVALUE_0, null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOfAny(CVALUE_0, CARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.indexOfAny(CVALUE_N5, CARRAY, 0));
		assertEquals(5, ArrayUtils.indexOfAny(CVALUE_0, CARRAY, 0));
		assertEquals(10, ArrayUtils.indexOfAny(CVALUE_5, CARRAY, 0));

		assertEquals(-1, ArrayUtils.indexOfAny(CVALUE_N5, CARRAY, 5));
		assertEquals(5, ArrayUtils.indexOfAny(CVALUE_0, CARRAY, 5));
		assertEquals(10, ArrayUtils.indexOfAny(CVALUE_5, CARRAY, 5));

		assertEquals(-1, ArrayUtils.indexOfAny(CVALUE_N5, CARRAY, 10));
		assertEquals(-1, ArrayUtils.indexOfAny(CVALUE_0, CARRAY, 10));
		assertEquals(10, ArrayUtils.indexOfAny(CVALUE_5, CARRAY, 10));
	}

	@Test
	public void testIndexOfAnyIICaCa() {
		try {
			ArrayUtils.indexOfAny(CVALUE_0, null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOfAny(CVALUE_0, CARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOfAny(CVALUE_0, CARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexOfAny(CVALUE_0, CARRAY, 0, CARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0,
				ArrayUtils.indexOfAny(CVALUE_N5, CARRAY, 0, CARRAY.length));
		assertEquals(5,
				ArrayUtils.indexOfAny(CVALUE_0, CARRAY, 0, CARRAY.length));
		assertEquals(10,
				ArrayUtils.indexOfAny(CVALUE_5, CARRAY, 0, CARRAY.length));

		assertEquals(-1,
				ArrayUtils.indexOfAny(CVALUE_N5, CARRAY, 5, CARRAY.length - 5));
		assertEquals(5,
				ArrayUtils.indexOfAny(CVALUE_0, CARRAY, 5, CARRAY.length - 5));
		assertEquals(10,
				ArrayUtils.indexOfAny(CVALUE_5, CARRAY, 5, CARRAY.length - 5));

		assertEquals(-1, ArrayUtils.indexOfAny(CVALUE_N5, CARRAY, 10, 1));
		assertEquals(-1, ArrayUtils.indexOfAny(CVALUE_0, CARRAY, 10, 1));
		assertEquals(10, ArrayUtils.indexOfAny(CVALUE_5, CARRAY, 10, 1));
	}

	/*
	 * ========================================================================
	 * REVERSE char[] array, char value
	 * ========================================================================
	 */
	@Test
	public void testLastIndexOfCaC() {
		try {
			ArrayUtils.lastIndexOf('0', null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOf('a', CARRAY));
		assertEquals(5, ArrayUtils.lastIndexOf('0', CARRAY));
		assertEquals(10, ArrayUtils.lastIndexOf('5', CARRAY));
	}

	@Test
	public void testLastIndexOfICaC() {
		try {
			ArrayUtils.lastIndexOf('0', null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf('0', CARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOf('a', CARRAY, 0));
		assertEquals(5, ArrayUtils.lastIndexOf('0', CARRAY, 0));
		assertEquals(10, ArrayUtils.lastIndexOf('5', CARRAY, 0));

		assertEquals(-1, ArrayUtils.lastIndexOf('a', CARRAY, 5));
		assertEquals(5, ArrayUtils.lastIndexOf('0', CARRAY, 5));
		assertEquals(10, ArrayUtils.lastIndexOf('5', CARRAY, 5));

		assertEquals(-1, ArrayUtils.lastIndexOf('a', CARRAY, 10));
		assertEquals(-1, ArrayUtils.lastIndexOf('0', CARRAY, 10));
		assertEquals(10, ArrayUtils.lastIndexOf('5', CARRAY, 10));
	}

	@Test
	public void testLastIndexOfIICaC() {
		try {
			ArrayUtils.lastIndexOf('0', null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf('0', CARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf('0', CARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf('0', CARRAY, 0, CARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOf('a', CARRAY, 0, CARRAY.length));
		assertEquals(5, ArrayUtils.lastIndexOf('0', CARRAY, 0, CARRAY.length));
		assertEquals(10, ArrayUtils.lastIndexOf('5', CARRAY, 0, CARRAY.length));

		assertEquals(-1,
				ArrayUtils.lastIndexOf('a', CARRAY, 5, CARRAY.length - 5));
		assertEquals(5,
				ArrayUtils.lastIndexOf('0', CARRAY, 5, CARRAY.length - 5));
		assertEquals(10,
				ArrayUtils.lastIndexOf('5', CARRAY, 5, CARRAY.length - 5));

		assertEquals(-1, ArrayUtils.lastIndexOf('a', CARRAY, 10, 1));
		assertEquals(-1, ArrayUtils.lastIndexOf('0', CARRAY, 10, 1));
		assertEquals(10, ArrayUtils.lastIndexOf('5', CARRAY, 10, 1));
	}

	/*
	 * ========================================================================
	 * REVERSE char[] array, char[] values
	 * ========================================================================
	 */
	@Test
	public void testLastIndexOfCaCa() {
		try {
			ArrayUtils.lastIndexOf(CVALUES_ZERO, null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOf(CVALUES_NEG, CARRAY));
		assertEquals(5, ArrayUtils.lastIndexOf(CVALUES_ZERO, CARRAY));
		assertEquals(6, ArrayUtils.lastIndexOf(CVALUES_POS, CARRAY));
	}

	@Test
	public void testLastIndexOfICaCa() {
		try {
			ArrayUtils.lastIndexOf(CVALUES_ZERO, null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf(CVALUES_ZERO, CARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOf(CVALUES_NEG, CARRAY, 0));
		assertEquals(5, ArrayUtils.lastIndexOf(CVALUES_ZERO, CARRAY, 0));
		assertEquals(6, ArrayUtils.lastIndexOf(CVALUES_POS, CARRAY, 0));

		assertEquals(-1, ArrayUtils.lastIndexOf(CVALUES_NEG, CARRAY, 5));
		assertEquals(5, ArrayUtils.lastIndexOf(CVALUES_ZERO, CARRAY, 5));
		assertEquals(6, ArrayUtils.lastIndexOf(CVALUES_POS, CARRAY, 6));

		assertEquals(-1, ArrayUtils.lastIndexOf(CVALUES_NEG, CARRAY, 6));
		assertEquals(-1, ArrayUtils.lastIndexOf(CVALUES_ZERO, CARRAY, 10));
	}

	@Test
	public void testLastIndexOfIICaCa() {
		try {
			ArrayUtils.lastIndexOf(CVALUES_ZERO, null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf(CVALUES_ZERO, CARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf(CVALUES_ZERO, CARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOf(CVALUES_ZERO, CARRAY, 0, CARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0,
				ArrayUtils.lastIndexOf(CVALUES_NEG, CARRAY, 0, CARRAY.length));
		assertEquals(5,
				ArrayUtils.lastIndexOf(CVALUES_ZERO, CARRAY, 0, CARRAY.length));
		assertEquals(6,
				ArrayUtils.lastIndexOf(CVALUES_POS, CARRAY, 0, CARRAY.length));

		assertEquals(-1, ArrayUtils.lastIndexOf(CVALUES_NEG, CARRAY, 5,
				CARRAY.length - 5));
		assertEquals(5, ArrayUtils.lastIndexOf(CVALUES_ZERO, CARRAY, 5,
				CARRAY.length - 5));
		assertEquals(6, ArrayUtils.lastIndexOf(CVALUES_POS, CARRAY, 5,
				CARRAY.length - 5));

		assertEquals(-1, ArrayUtils.lastIndexOf(CVALUES_NEG, CARRAY, 6, 5));
		assertEquals(5, ArrayUtils.lastIndexOf(CVALUES_ZERO, CARRAY, 5, 1));
		assertEquals(-1, ArrayUtils.lastIndexOf(CVALUES_POS, CARRAY, 2, 5));
	}

	/*
	 * ========================================================================
	 * REVERSE char[] array, ANY char[] values
	 * ========================================================================
	 */
	@Test
	public void testLastIndexOfAnyCaCa() {
		try {
			ArrayUtils.lastIndexOfAny(CVALUE_0, null);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOfAny(CVALUE_N5, CARRAY));
		assertEquals(5, ArrayUtils.lastIndexOfAny(CVALUE_0, CARRAY));
		assertEquals(10, ArrayUtils.lastIndexOfAny(CVALUE_5, CARRAY));
	}

	@Test
	public void testLastIndexOfAnyICaCa() {
		try {
			ArrayUtils.lastIndexOfAny(CVALUE_0, null, 0);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOfAny(CVALUE_0, CARRAY, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0, ArrayUtils.lastIndexOfAny(CVALUE_N5, CARRAY, 0));
		assertEquals(5, ArrayUtils.lastIndexOfAny(CVALUE_0, CARRAY, 0));
		assertEquals(10, ArrayUtils.lastIndexOfAny(CVALUE_5, CARRAY, 0));

		assertEquals(-1, ArrayUtils.lastIndexOfAny(CVALUE_N5, CARRAY, 5));
		assertEquals(5, ArrayUtils.lastIndexOfAny(CVALUE_0, CARRAY, 5));
		assertEquals(10, ArrayUtils.lastIndexOfAny(CVALUE_5, CARRAY, 5));

		assertEquals(-1, ArrayUtils.lastIndexOfAny(CVALUE_N5, CARRAY, 10));
		assertEquals(-1, ArrayUtils.lastIndexOfAny(CVALUE_0, CARRAY, 10));
		assertEquals(10, ArrayUtils.lastIndexOfAny(CVALUE_5, CARRAY, 10));
	}

	@Test
	public void testLastIndexOfAnyIICaCa() {
		try {
			ArrayUtils.lastIndexOfAny(CVALUE_0, null, 0, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOfAny(CVALUE_0, CARRAY, -1, 1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOfAny(CVALUE_0, CARRAY, 0, -1);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.lastIndexOfAny(CVALUE_0, CARRAY, 0, CARRAY.length * 2);
			assertTrue(false); // shouldn't get here
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(0,
				ArrayUtils.lastIndexOfAny(CVALUE_N5, CARRAY, 0, CARRAY.length));
		assertEquals(5,
				ArrayUtils.lastIndexOfAny(CVALUE_0, CARRAY, 0, CARRAY.length));
		assertEquals(10,
				ArrayUtils.lastIndexOfAny(CVALUE_5, CARRAY, 0, CARRAY.length));

		assertEquals(-1, ArrayUtils.lastIndexOfAny(CVALUE_N5, CARRAY, 5,
				CARRAY.length - 5));
		assertEquals(5, ArrayUtils.lastIndexOfAny(CVALUE_0, CARRAY, 5,
				CARRAY.length - 5));
		assertEquals(10, ArrayUtils.lastIndexOfAny(CVALUE_5, CARRAY, 5,
				CARRAY.length - 5));

		assertEquals(-1, ArrayUtils.lastIndexOfAny(CVALUE_N5, CARRAY, 10, 1));
		assertEquals(-1, ArrayUtils.lastIndexOfAny(CVALUE_0, CARRAY, 10, 1));
		assertEquals(10, ArrayUtils.lastIndexOfAny(CVALUE_5, CARRAY, 10, 1));
	}

	@Test
	public void testIndexAfterCaC() {
		assertEquals(0, ArrayUtils.indexAfter(Character.MAX_VALUE, CARRAY));
		assertEquals(1, ArrayUtils.indexAfter('a', CARRAY));
		assertEquals(3, ArrayUtils.indexAfter('a', CREPEAT));
	}

	@Test
	public void testIndexAfterICaC() {
		assertEquals(0, ArrayUtils.indexAfter(Character.MAX_VALUE, CARRAY, 0));
		assertEquals(1, ArrayUtils.indexAfter('a', CARRAY, 0));
		assertEquals(6, ArrayUtils.indexAfter('0', CARRAY, 5));
		assertEquals(11, ArrayUtils.indexAfter('5', CARRAY, 10));

		assertEquals(3, ArrayUtils.indexAfter('a', CREPEAT, 0));
		assertEquals(5, ArrayUtils.indexAfter('h', CREPEAT, 3));
		assertEquals(8, ArrayUtils.indexAfter('z', CREPEAT, 5));

		try {
			ArrayUtils.indexAfter('0', CARRAY, CARRAY.length + 1);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIndexAfterIICaC() {
		assertEquals(0, ArrayUtils.indexAfter(Character.MAX_VALUE, CARRAY, 0,
				CARRAY.length));
		assertEquals(1, ArrayUtils.indexAfter('a', CARRAY, 0, 1));
		assertEquals(6, ArrayUtils.indexAfter('0', CARRAY, 5, 1));
		assertEquals(11, ArrayUtils.indexAfter('5', CARRAY, 10, 1));

		assertEquals(1, ArrayUtils.indexAfter('a', CARRAY, 0, 10));
		assertEquals(6, ArrayUtils.indexAfter('0', CARRAY, 5, 5));
		assertEquals(11, ArrayUtils.indexAfter('5', CARRAY, 10, 1));

		assertEquals(2, ArrayUtils.indexAfter('a', CREPEAT, 0, 2));
		assertEquals(4, ArrayUtils.indexAfter('h', CREPEAT, 3, 1));
		assertEquals(8, ArrayUtils.indexAfter('z', CREPEAT, 5, 3));

		try {
			ArrayUtils.indexAfter('0', CARRAY, -1, CARRAY.length);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexAfter('0', CARRAY, 5, 20);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIndexAfterCaCa() {
		assertEquals(0, ArrayUtils.indexAfter(new char[] { 45, 50 }, CARRAY));
		assertEquals(11, ArrayUtils.indexAfter(CARRAY, CARRAY));
		assertEquals(1, ArrayUtils.indexAfter(CVALUE_N5, CARRAY));
		assertEquals(5, ArrayUtils.indexAfter(CVALUES_NEG, CARRAY));

		assertEquals(3, ArrayUtils.indexAfter(CREPEAT_NEG, CREPEAT));
	}

	@Test
	public void testIndexAfterICaCa() {
		assertEquals(0, ArrayUtils.indexAfter(
				new char[] { Character.MAX_VALUE }, CARRAY, 0));
		assertEquals(1, ArrayUtils.indexAfter(CVALUE_N5, CARRAY, 0));
		assertEquals(6, ArrayUtils.indexAfter(CVALUE_0, CARRAY, 5));
		assertEquals(11, ArrayUtils.indexAfter(CVALUE_5, CARRAY, 10));

		assertEquals(3, ArrayUtils.indexAfter(CREPEAT_NEG, CREPEAT, 0));
		assertEquals(5, ArrayUtils.indexAfter(CREPEAT_ZERO, CREPEAT, 3));
		assertEquals(8, ArrayUtils.indexAfter(CREPEAT_POS, CREPEAT, 5));

		try {
			ArrayUtils.indexAfter(CVALUE_N5, CARRAY, CARRAY.length + 1);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIndexAfterIICaCa() {
		assertEquals(0, ArrayUtils.indexAfter(
				new char[] { Character.MAX_VALUE }, CARRAY, 0, CARRAY.length));
		assertEquals(1, ArrayUtils.indexAfter(CVALUE_N5, CARRAY, 0, 1));
		assertEquals(6, ArrayUtils.indexAfter(CVALUE_0, CARRAY, 5, 1));
		assertEquals(11, ArrayUtils.indexAfter(CVALUE_5, CARRAY, 10, 1));

		assertEquals(1, ArrayUtils.indexAfter(CVALUE_N5, CARRAY, 0, 10));
		assertEquals(6, ArrayUtils.indexAfter(CVALUE_0, CARRAY, 5, 5));
		assertEquals(11, ArrayUtils.indexAfter(CVALUE_5, CARRAY, 10, 1));

		try {
			/*
			 * Test that the minimum length requirement for testing all of the
			 * values array is enforced; because the length arg applies to HOW
			 * MUCH to search in the array, to find the entire values array. Not
			 * some subset of each.
			 */
			assertEquals(2, ArrayUtils.indexAfter(CREPEAT_NEG, CREPEAT, 0, 2));
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		assertEquals(3, ArrayUtils.indexAfter(CREPEAT_NEG, CREPEAT, 0, 3));
		assertEquals(5, ArrayUtils.indexAfter(CREPEAT_ZERO, CREPEAT, 3, 2));
		assertEquals(8, ArrayUtils.indexAfter(CREPEAT_POS, CREPEAT, 5, 3));

		try {
			ArrayUtils.indexAfter(CVALUE_0, CARRAY, -1, CARRAY.length);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexAfter(CVALUE_0, CARRAY, 5, 20);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIndexAfterAnyCaCa() {
		assertEquals(0, ArrayUtils.indexAfterAny(new char[] { 77 }, CREPEAT));
		assertEquals(3, ArrayUtils.indexAfterAny(CREPEAT_NEG, CREPEAT));
		assertEquals(8, ArrayUtils.indexAfterAny(CREPEAT_ANY, CREPEAT));
	}

	@Test
	public void testIndexAfterAnyICaCa() {
		assertEquals(0, ArrayUtils.indexAfterAny(new char[] { 77 }, CREPEAT, 0));
		assertEquals(5, ArrayUtils.indexAfterAny(CREPEAT_NEG, CREPEAT, 5));

		assertEquals(3, ArrayUtils.indexAfterAny(CREPEAT_NEG, CREPEAT, 1));
		assertEquals(5, ArrayUtils.indexAfterAny(CREPEAT_ZERO, CREPEAT, 3));
		assertEquals(8, ArrayUtils.indexAfterAny(CREPEAT_ANY, CREPEAT, 6));

		try {
			ArrayUtils.indexAfterAny(CREPEAT_ANY, CREPEAT, CREPEAT.length + 1);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIndexAfterAnyIICaCa() {
		assertEquals(0, ArrayUtils.indexAfterAny(new char[] { 77 }, CREPEAT, 0,
				CREPEAT.length));
		assertEquals(5, ArrayUtils.indexAfterAny(CREPEAT_NEG, CREPEAT, 5,
				CREPEAT.length - 5));

		assertEquals(3, ArrayUtils.indexAfterAny(CREPEAT_NEG, CREPEAT, 1,
				CREPEAT.length - 1));
		assertEquals(5, ArrayUtils.indexAfterAny(CREPEAT_ZERO, CREPEAT, 3,
				CREPEAT.length - 3));
		assertEquals(8, ArrayUtils.indexAfterAny(CREPEAT_ANY, CREPEAT, 6,
				CREPEAT.length - 6));

		try {
			ArrayUtils.indexAfterAny(CREPEAT_ANY, CREPEAT, 0,
					CREPEAT.length + 1);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		try {
			ArrayUtils.indexAfterAny(CREPEAT_ANY, CREPEAT, CREPEAT.length + 1,
					2);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
}