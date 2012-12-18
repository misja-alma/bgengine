package org.misja.bg;

import java.util.Collection;

public class CollectionUtil {
  public static <T> int count(Collection<T> collection, T object) {
    int count = 0;
    for(T element: collection) {
      if(element == object) {
        count++;
      }
    }
    return count;
  }
}
