package com.michielo.util;

import java.util.List;

public class CircularListNavigator<T> {

    /*
        // Example usage
        List<String> stringList = List.of("A", "B", "C", "D", "E", "F");
        CircularListNavigator<String> navigator = new CircularListNavigator<>(stringList);

        // Moving through the list
        System.out.println(navigator.getNext());      // Output: B
        System.out.println(navigator.getNext());      // Output: C
        System.out.println(navigator.getPrevious());  // Output: B
        System.out.println(navigator.getPrevious());  // Output: A
     */

    private List<T> list;
    private int currentIndex;

    public CircularListNavigator(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must be non-null and non-empty");
        }
        this.list = list;
        this.currentIndex = 0;
    }

    public void moveToNext() {
        currentIndex = (currentIndex + 1) % list.size();
    }

    public void moveToPrevious() {
        currentIndex = (currentIndex - 1 + list.size()) % list.size();
    }

    public T getCurrent() {
        return list.get(currentIndex);
    }

}