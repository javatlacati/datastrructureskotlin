package jaba.lists.linkedlists.singlelinkedlists.recursive;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

/**
 * Created by Administrador on 22/05/2017.
 *
 * @param <T> generic type
 */
@Setter
@Getter
@EqualsAndHashCode(of = {"item"})
@ToString(of = "item")
public class Node<T> {

    private T item;
    private Node next;

    public Node(T item) {
        this.item = item;
    }

    //second step
    /**
     * Appends a node at the end.
     */
    synchronized void add(Node newNode) {
        if (next == null) {
            next = newNode;
        } else {
            next.add(newNode);
        }
    }

    synchronized void addAtIndex(Node newNode, int targetIndex, int currentIndex) {
        if (targetIndex == currentIndex) {
            next = newNode;
        } else {
            if (next != null) {
                next.addAtIndex(newNode, targetIndex, 1 + currentIndex);
            } else {
                throw new IndexOutOfBoundsException("the specified index is not possible to reach");
            }
        }
    }

    String getStrings() {
        return next == null ? toString() : toString() + next.getStrings();
    }

    void removeLast() {
        if (next != null) {
            if (next.getNext() == null) {
                next = null;
            } else {
                next.removeLast();
            }
        }
    }

    void reverse() {
        if (next != null) {
            if (next.getNext() != null) {
                if (next.getNext().getNext() != null) {
                    next.getNext().reverse();
                }else{
                    val aux= next.getNext();
                    val aux1=aux.getNext();
                    
                }
            }
        }
    }

    public int size(int i) {
        if(next==null) {
            return i;
        }else {
            int i1 = i+1;
            return next.size(i1);
        }
    }
}