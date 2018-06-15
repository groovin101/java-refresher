package com.groovin101.refresher.generic;

import java.util.List;
import java.util.Stack;

/**
 * See Item 31 in Effective Java 3rd edition
 */
public class Stackk<E> {

    private Stack<E> stack = new Stack<>();

    public void push(E e) {
        stack.push(e);
    }

    public E pop() {
        return stack.pop();
    }

    public void popAllDeficient(List<E> popItemsOntoThisCollection) {
        while (!stack.isEmpty()) {
            popItemsOntoThisCollection.add(stack.pop());
        }
    }

    public void popAll(List<? super E> popItemsOntoThisCollection) {
        while (!stack.isEmpty()) {
            popItemsOntoThisCollection.add(stack.pop());
        }
    }

    public void pushAllDeficient(List<E> elementsToAddToStack) {
        for (E e : elementsToAddToStack) {
            stack.push(e);
        }
    }

    public void pushAll(List<? extends E> elementsToAddToStack) {
        for (E e : elementsToAddToStack) {
            stack.push(e);
        }
    }

    //Returning wildcards is a bad idea. In this cae, we can't guarantee the actual Type Argument that will be used on the returned List<? super E> object, causing potential problems to callers of this method
//    public List<? super E> popAll(List<? super E> popItemsOntoThisCollection) {
//        while (!stack.isEmpty()) {
//            popItemsOntoThisCollection.add(stack.pop());
//        }
//        return popItemsOntoThisCollection;
//    }

}
