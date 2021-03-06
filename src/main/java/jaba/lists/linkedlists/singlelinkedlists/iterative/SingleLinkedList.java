package jaba.lists.linkedlists.singlelinkedlists.iterative;

import jaba.lists.MyList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;

@Setter
@Getter
@EqualsAndHashCode(of = "root")
public class SingleLinkedList<Type> implements MyList<Type> {
  private Node<Type> root;

  public String getStrings() {
    if (root == null) {
      return "";
    } else {
      StringBuilder salida = new StringBuilder(root.toString());
      Node<Type> actual = root;
      while (actual.getNext() != null) {
        actual = actual.getNext();
        salida.append(actual.toString());
      }
      return salida.toString();
    }
  }

  /** */
  public boolean addAtEnd(Type element) {
    Node<Type> newNode = new Node<>(element);
    try {
      if (root == null) {
        root = newNode;
      } else {
        Node<Type> actual = root;
        while (actual.getNext() != null) {
          actual = actual.getNext();
        }
        actual.setNext(newNode);
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /** */
  public void addAtRoot(Type element) {
    Node<Type> newNode = new Node<>(element);
    newNode.setNext(root);
    root = newNode;
  }

  /**
   * Index is 0 base
   *
   * @param element
   * @param index position to insert
   */
  public void addAtIndex(Type element, int index) {
    if (index == 0) {
      addAtRoot(element);
    } else {
      if (index > 0) {
        Node<Type> currentNode = root.getNext();
        for (int currentIndex = 1; currentIndex < index; currentIndex++) {
          Node<Type> next = currentNode.getNext();
          if (next != null) {
            currentNode = next;
          } else {
            throw new IndexOutOfBoundsException("the specified index is not possible to reach");
          }
        }
        currentNode.setNext(new Node<>(element));
      } else {
        throw new IndexOutOfBoundsException("no negative index values allowed");
      }
    }
  }

  public void addSingleLinkedListAtRoot(SingleLinkedList<Type> singleLinkedList) {
    singleLinkedList.addAtEnd(root.getItem());
    root = singleLinkedList.getRoot();
  }

  public void addSingleLinkedListAtEnd(SingleLinkedList<Type> singleLinkedList) {
    addAtRoot(singleLinkedList.getRoot().getItem());
  }

  public void printList() {
    System.out.println(getStrings());
  }

  public void removeLast() {
    if (root != null) {
      if (root.getNext() == null) {
        root = null;
      } else {
        Node<Type> currentNode = root;
        while (currentNode.getNext() != null) {
          currentNode = currentNode.getNext();
        }
        if (currentNode.getNext() == null) {
          currentNode.setNext(null);
        }
      }
    }
  }

  @Override
  public int size() {
    int size = 0;
    Node<Type> currentNode = root;
    while (currentNode != null) {
      currentNode = currentNode.getNext();
      size++;
    }
    return size;
  }

  @Override
  public boolean isEmpty() {
    return root == null;
  }

  @Override
  public boolean contains(Object o) {
    return null != o
        && null != root
        && o.getClass().equals(root.getItem().getClass())
        && containsSameTypeVerified((Type) o);
  }

  /** {@inheritDoc} */
  @NotNull
  public Iterator<Type> iterator() {
    return new Iterator<Type>() {
      Node<Type> tmp = root;

      @Override
      public boolean hasNext() {
        return null != tmp.getNext();
      }

      @Override
      public Type next() {
        Type aux = tmp.getItem();
        tmp = tmp.getNext();
        return aux;
      }
    };
  }

  public boolean containsSameTypeVerified(Type o) {
    if (root == null && o == null) {
      return false;
    } else {
      Node<Type> currentNode = root;
      while (currentNode != null) {
        if (currentNode.getItem().equals(o)) {
          return true;
        }
        currentNode = currentNode.getNext();
      }
    }
    return false;
  }

  @Override
  public Type[] toArray() {
    int size = size();
    Type[] resultingArray = (Type[]) new Object[size];
    if (size != 0) {
      Node<Type> currentNode = root;
      int index = 0;
      while (currentNode != null) {
        resultingArray[index] = currentNode.getItem();
        currentNode = currentNode.getNext();
        index++;
      }
    }
    return resultingArray;
  }

  /** {@inheritDoc} */
  @NotNull
  public <T> T[] toArray(@NotNull T[] resultingArray) {
    int size = size();
    if (size != 0) {
      Node<T> currentNode = (Node<T>) root;
      int index = 0;
      while (currentNode != null) {
        resultingArray[index] = currentNode.getItem();
        currentNode = currentNode.getNext();
        index++;
      }
    }
    return resultingArray;
  }

  /** {@inheritDoc} */
  @Override
  public boolean add(Type o) {
    return addAtEnd(o);
  }

  /** {@inheritDoc} */
  @Override
  public boolean remove(Object o) {
    return null != o
        && null != root
        && o.getClass().equals(root.getItem().getClass())
        && removeChecked((Type) o);
  }

  public boolean removeChecked(Type content) {
    Node<Type> anterior = root;
    Node<Type> actual = root;

    while (actual.getItem() != content && actual.getNext() != null) {
      anterior = actual;
      actual = actual.getNext();
    }
    if (anterior.equals(actual)) {
      root = actual.getNext();
    } else {
      anterior.setNext(actual.getNext());
    }
    return true;
  }

  /** {@inheritDoc} */
  public boolean containsAll(@NotNull Collection<?> collection) {
    return collection.stream().allMatch(this::contains);
  }

  /** {@inheritDoc} */
  public boolean addAll(@NotNull Collection<? extends Type> collection) {
    return collection.stream().allMatch(this::add);
  }

  /** {@inheritDoc} */
  public boolean removeAll(@NotNull Collection<?> collection) {
    return collection.stream().allMatch(this::remove);
  }

  @Override
  public boolean retainAll(@NotNull Collection<?> c) {
    return this.stream().filter(o -> !c.contains(o)).allMatch(this::remove);
  }

  Node swap(Node first) {
    Node next = first.getNext();
    first.setNext(next.getNext());
    next.setNext(first);
    return next;
  }

  public void reverse() {
    root = reverse(this.root);
  }

  private Node<Type> reverse(Node<Type> head) {
    if (head == null) {
      return null;
    } else if (head.getNext() == null) {
      return head;
    } else {
      Node<Type> aux = head;
      head = aux.getNext();
      aux.setNext(null);
      while (head.getNext() != null) {
        Node<Type> aux1 = head.getNext();
        head.setNext(aux);
        aux = head;
        head = aux1;
      }
      head.setNext(aux);
      return head;
    }
  }

  /*
   * {@inheritDoc}
   */
  //  public boolean retainAll(@NotNull Collection<?> c) {
  //    return this.stream().filter(o -> !containsSameTypeVerified(o)).allMatch(this::remove);
  //  }

  @Override
  public void clear() {
    root = null;
  }

  @Override
  public Type get(int index) {
    int currentIdx = 0;
    if (index == 0) {
      return root.getItem();
    }
    Node<Type> currentNode = root;
    while (currentNode != null) {
      currentNode = currentNode.getNext();
      currentIdx++;
      if (currentIdx == index) {
        return currentNode.getItem();
      }
    }
    return null;
  }
}
