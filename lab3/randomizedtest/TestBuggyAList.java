package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  @Test
  public void testThreeAddThreeRemove(){
      AListNoResizing<Integer> list1 = new AListNoResizing<>();
      BuggyAList<Integer> list2 = new BuggyAList<>();

      list1.addLast(4);
      list2.addLast(4);
      list1.addLast(5);
      list2.addLast(5);
      list1.addLast(5);
      list2.addLast(5);

      for(int i=0; i<3; i++){
          int x = list1.removeLast();
          int y = list2.removeLast();
          assertEquals(x, y);
      }

  }

  @Test
  public void randomizedTest(){
      AListNoResizing<Integer> L = new AListNoResizing<>();
      BuggyAList<Integer> B = new BuggyAList<>();

      int N = 5000;
      for (int i = 0; i < N; i += 1) {
          int operationNumber = StdRandom.uniform(0, 3);
          if (operationNumber == 0) {
              // addLast
              int randVal = StdRandom.uniform(0, 100);
              L.addLast(randVal);
              B.addLast(randVal);
              System.out.println("addLast(" + randVal + ")");
          } else if (operationNumber == 1) {
              // size
              int size = L.size();
              int size1 = B.size();
              assertEquals(size, size1);
              System.out.println("size: " + size);
          } else if(operationNumber == 2){
              // getLast
              int item1 = 0, item2 = 0;
              if(L.size() > 0){
                  item1 = L.getLast();
              }

              if(B.size() > 0){
                  item2 = B.getLast();
              }

              if(L.size() > 0 && B.size() > 0){
                  assertEquals(item1, item2);
                  System.out.println("L.last: " + item1 + "B.last: " + item2);
              }
          } else if(operationNumber == 3){
              // removeLast
              int item1 = 0, item2 = 0;
              if(L.size() > 0){
                  item1 = L.removeLast();
              }

              if(B.size() > 0){
                  item2 = B.removeLast();
              }

              if(L.size() > 0 && B.size() > 0){
                  assertEquals(item1, item2);
                  System.out.println("remove L last: " + item1 + "remove B last: " + item2);
              }
          }
      }
  }
}
