package jaba.lists;

import jaba.lists.linkedlists.doublylinkedlists.iterative.TailedDoublyLinkedList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/** Created by Administrador on 10/06/2017. */
@RunWith(Parameterized.class)
public class MyListTest {
  @Parameters
  public static Collection<Object[]> instancesToTest() {
    return Arrays.asList(
        new Object[] {new jaba.lists.linkedlists.singlelinkedlists.iterative.SingleLinkedList()},
        new Object[] {new jaba.lists.linkedlists.singlelinkedlists.recursive.SingleLinkedList()},
        new Object[] {
          new jaba.lists.linkedlists.singlelinkedlists.iterative.TailedSingleLinkedList<>()
        },
        new Object[] {
                new jaba.lists.MyArrayList<>()
        },
        new Object[] {new TailedDoublyLinkedList<>()}
        );

  }

  private MyList myList;

  public MyListTest(MyList myList) {
    this.myList = myList;
  }

  @Before
  public void setUp() throws Exception {
    myList.clear();
    System.out.println("testing:" + myList.getClass().getName());
  }

  // tests
  @Test
  public void isEmpty() throws Exception {
    assertTrue(myList.isEmpty());
  }

  @Test
  public void size() throws Exception {
    assertNotNull(myList.size());
    assertEquals(0, myList.size());
    assertTrue(myList.add("hola"));
    assertEquals(1, myList.size());
    assertTrue(myList.add("mundo"));
    assertEquals(2, myList.size());
  }

  @Test
  public void contains() throws Exception {
    assertFalse(myList.contains(null));
    myList.add("hola");
    myList.add("mundo!");
    assertTrue(myList.contains("mundo!"));
  }

  @Test
  public void toArray() throws Exception {
    assertArrayEquals(new Object[0], myList.toArray());
    myList.add("hola");
    Object[] arr = new Object[] {"hola"};
    assertArrayEquals(arr, myList.toArray());
    myList.add("mundo!");
    arr = new Object[] {"hola", "mundo!"};
    assertArrayEquals(arr, myList.toArray());
    myList.add("cruel");
    arr = new Object[] {"hola", "mundo!","cruel"};
    assertArrayEquals(arr, myList.toArray());
  }

  @Test
  public void add() throws Exception {
    assertTrue(myList.add("hola"));
    assertTrue(myList.add("mundo!"));
  }

  @Test
  public void remove() {
    assertTrue(myList.add("adios"));
    assertTrue(myList.add("mundo"));
    assertTrue(myList.add("cruel"));

    assertTrue(myList.remove("adios"));
    Object[] arr = new Object[] {"mundo", "cruel"};
    assertArrayEquals(arr, myList.toArray());

    assertTrue(myList.remove("cruel"));
    arr = new Object[] {"mundo"};
    assertArrayEquals(arr, myList.toArray());
  }

  @Test
  public void containsAll() {
    assertTrue(myList.add("adios"));
    assertTrue(myList.add("mundo"));
    assertTrue(myList.add("cruel"));
    String[] arr = new String[] {"mundo", "cruel"};
    assertTrue(myList.containsAll(Arrays.asList(arr)));
  }

  @Test
  public void addAll() {
    assertTrue(myList.add("adios"));
    String[] arr = new String[] {"mundo", "cruel"};
    assertTrue(myList.addAll(Arrays.asList(arr)));
    assertArrayEquals(new String[] {"adios", "mundo", "cruel"}, myList.toArray());
  }

  @Test
  public void removeAll() {
    assertTrue(myList.add("adios"));
    assertTrue(myList.add("mundo"));
    assertTrue(myList.add("cruel"));
    String[] arr = new String[] {"mundo", "cruel"};
    assertTrue(myList.removeAll(Arrays.asList(arr)));
    assertArrayEquals(new String[] {"adios"}, myList.toArray());
  }

  @Test
  public void retainAll() {
    assertTrue(myList.add("adios"));
    assertTrue(myList.add("mundo"));
    assertTrue(myList.add("cruel"));
    assertTrue(myList.add("bye!"));

    String[] arr = new String[] {"mundo", "bye!"};
    assertTrue(myList.retainAll(Arrays.asList(arr)));
    assertArrayEquals(new String[] {"mundo", "bye!"}, myList.toArray());
  }

  @Test
  public void get() {
    assertTrue(myList.add("adios"));
    assertEquals("adios",myList.get(0));
    assertTrue(myList.add("mundo"));
    assertEquals("adios",myList.get(0));
    assertEquals("mundo",myList.get(1));
    assertTrue(myList.add("cruel"));
    assertEquals("adios",myList.get(0));
    assertEquals("mundo",myList.get(1));
    assertEquals("cruel",myList.get(2));
  }

  /**
   * This method is related to {@link MyList#contains(Object)} but is a simplification to see if
   * continuation can enhance the performance of the given datastructures.
   *
   * @see https://en.wikipedia.org/wiki/Continuation-passing_style
   */
  //  @Test
  //  public void searchANeedleInAHayStack() {
  //    assertTrue(myList.add("adios"));
  //    assertTrue(myList.add("mundo"));
  //    assertTrue(myList.add("cruel"));
  //    assertTrue(myList.add("bye!"));
  //    assertTrue(exists("bye!",myList));
  //  }
  //
  //  private boolean exists(String needle, MyList haystack) {
  //    for (Object element : haystack) {
  //      if (needle.equals(element)) return true;
  //    }
  //    return false; // nothing found
  //  }
}
