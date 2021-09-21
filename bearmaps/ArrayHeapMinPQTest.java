package bearmaps;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    @Test
    public void testBasic() {
        ArrayHeapMinPQ<String> test = new ArrayHeapMinPQ();
        test.add("kobe", 5.0);
        test.add("yeet", 1.0);
        test.add("it's lit", 0.0);
        String val = test.removeSmallest();
        Assert.assertEquals("it's lit", val);
        Assert.assertEquals(2, test.size());
        Assert.assertEquals("yeet", test.getSmallest());

        ArrayHeapMinPQ<Integer> um = new ArrayHeapMinPQ();
        Assert.assertEquals(0, um.size());
        for (int i = 0; i < 10; i++) {
            um.add(i, i);
        }
        Assert.assertEquals(10, um.size());
        Assert.assertEquals(9, um.leftChild(4));
        Assert.assertEquals(15, um.leftChild(7));
        /*for (int i = 0; i < um.size(); i++) {
            System.out.print(um.heap()[i].item + " ");
        }
        */
        System.out.println();
        int y = um.removeSmallest();
        Assert.assertEquals(0, y);
        int x = um.getSmallest();
        Assert.assertEquals(1, x);
        //Assert.assertEquals(3, um.leftChild(0));
        System.out.println(um.size());
        /*for (int i = 0; i < um.size(); i++) {
            //System.out.print(um.heap()[i].item + " ");
        }
        */
    }

    @Test
    public void testSink() {
        ArrayHeapMinPQ<Integer> sinker = new ArrayHeapMinPQ();
        sinker.add(3, 3);
        sinker.add(2, 2);
        sinker.add(1, 1);
        sinker.add(4, 4);
        sinker.add(5, 5);
        sinker.add(6, 6);
        sinker.removeSmallest();
        /*for (int i = 0; i < sinker.size(); i++) {
            //System.out.print(sinker.heap()[i].item + " ");
        }
        */
        System.out.println();
        ArrayHeapMinPQ<Integer> again = new ArrayHeapMinPQ();
        for (int i = 0; i < 11; i++) {
            again.add(i, i + 0.1);
        }
        again.removeSmallest();
        /*for (int i = 0; i < again.size(); i++) {
            //System.out.print(again.heap()[i].item + " ");
        }
        */
        assertEquals(true, again.contains(1));
        System.out.println();
        System.out.print(again.contains(0));
        System.out.println();
        again.changePriority(9, 0.0);
        /*for (int i = 0; i < again.size(); i++) {
            //System.out.print(again.heap()[i].item + " ");
        }
        */
        again.changePriority(9, 15);
        System.out.println();
        //System.out.println(again.heap()[again.size() - 1].item);
        /*for (int i = 0; i < again.size(); i++) {
            //System.out.print(again.heap()[i].item + " ");
        }
        */
    }

    @Test
    public void testChangeP() {
        ArrayHeapMinPQ<Integer> again = new ArrayHeapMinPQ();
        for (int i = 0; i < 11; i++) {
            again.add(i, i + 0.1);
        }
        /*for (int i = 0; i < 11; i++) {
           // System.out.print(again.heap()[i].item + " ");
        }
        */
        System.out.println();
        again.changePriority(10, 0);
        /*for (int i = 0; i < 11; i++) {
            // System.out.print(again.heap()[i].item + " ");
        }
        */
        System.out.println();
        System.out.print(again.contains(12));
        System.out.println();
        again.changePriority(0, 11);
        /*for (int i = 0; i < 11; i++) {
           // System.out.print(again.heap()[i].item + " ");
        }
        */
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> plsfam = new ArrayHeapMinPQ();
        plsfam.add(1, 1);
        plsfam.add(2, 2);
        int x = plsfam.removeSmallest();
        boolean kobe = plsfam.contains(1);
        System.out.println(x);
        System.out.println(kobe);
    }

    @Test
    public void testResize() {
        ArrayHeapMinPQ<Integer> yeet = new ArrayHeapMinPQ();
        for (int i = 0; i < 20; i++) {
            yeet.add(i, i + 0.5);
        }
        //System.out.println(yeet.heap().length);
        System.out.println(yeet.size());
        for (int i = 0; i < 5; i++) {
            yeet.add(i + 20, (i + 20) + 0.5);
        }
        //System.out.println(yeet.heap().length);
        System.out.println(yeet.size());
        for (int i = 0; i < 14; i++) {
            yeet.removeSmallest();
        }
        //System.out.println(yeet.heap().length);
        System.out.println(yeet.size());
    }

    @Test
    public void timeTest() {
        ArrayHeapMinPQ<Integer> time = new ArrayHeapMinPQ();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            time.add(i, i + 0.2);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapse add: " + (end - start) / 1000.0 +  " seconds.");

        ArrayHeapMinPQ<Integer> removes = new ArrayHeapMinPQ();
        for (int i = 0; i < 2000000; i++) {
            removes.add(i, i + 0.5);
        }
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            removes.removeSmallest();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Total time elapsed removeSmallest: "
                + (end1 - start1) / 1000.0 +  " seconds.");

        ArrayHeapMinPQ<Integer> contains = new ArrayHeapMinPQ();
        for (int i = 0; i < 2000000; i++) {
            contains.add(i, i + 0.5);
        }
        long start2 = System.currentTimeMillis();
        boolean x = contains.contains(20000000);
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed contains: "
                + (end2 - start2) / 1000.0 +  " seconds.");

        ArrayHeapMinPQ<Integer> changep = new ArrayHeapMinPQ();
        for (int i = 0; i < 2000000; i++) {
            changep.add(i, i + 0.5);
        }
        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            changep.changePriority(i, 700 - i);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Total time elapsed changePriority: "
                + (end3 - start3) / 1000.0 +  " seconds.");
    }

}
