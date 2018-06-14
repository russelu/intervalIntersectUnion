// intervalIntersectUnion

// "static void main" must be defined in a public class.

class Interval {
    public int start, end;
    public Interval(int s, int e) {start = s; end = e;}
}

class Solution {
    
    public static union(List<Interval> list1, List<Interval> list2) {
        if (list1.isEmpty()) return list2;
        if (list2.isEmpty()) return list1;
        List<Interval> res = new ArrayList<Interval>();
        Interval curr;
        int i = 0, j = 0;
        Interval a = list1.get(0), b = list2.get(0);
        /*while (i < list1.size() && j < list2.size()) {
            
        }
        while (curr != null) {
            
        }*/
    }
    
    public static List<Interval> intersect(List<Interval> list1, List<Interval> list2) {
        List<Interval> res = new ArrayList<Interval>();
        if (list1.isEmpty() || list2.isEmpty()) return res;
        int i = 0, j = 0;
        Interval a = list1.get(0), b = list2.get(0);
        while (i < list1.size() && j < list2.size()) {
            getIntersect(res, a, b);
            if (a.end <= b.end) {
                i++;
                if (i < list1.size()) a = list1.get(i);
            } else {
                j++;
                if (j < list2.size()) b = list2.get(j);
            }
        }
        return res;
    }
    
    private static void getIntersect(List<Interval> res, Interval a, Interval b) {
        if (a.start > b.end || a.end < b.start) return;
        res.add(new Interval(Math.max(a.start, b.start), Math.min(a.end, b.end)));
    }
    
    public static void buildList(List<Interval> list1, List<Interval> list2) {
        list1.add(new Interval(0, 2));
        list1.add(new Interval(5, 10));
        list1.add(new Interval(13, 23));
        list1.add(new Interval(24, 25));
        list2.add(new Interval(1, 5));
        list2.add(new Interval(8, 12));
        list2.add(new Interval(15, 18));
        list2.add(new Interval(20, 24));
    }
    
    public static void printList(List<Interval> list) {
        for (Interval it : list) {
            System.out.print("[" + it.start + ", " + it.end + "] ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<Interval> list1 = new ArrayList<Interval>();
        List<Interval> list2 = new ArrayList<Interval>();
        Solution.buildList(list1, list2);
        List<Interval> is = Solution.intersect(list1, list2);
        Solution.printList(is);
        System.out.println();
        //is  = Solution.union(list1, list2);
        //Solution.printList(is);
    }
}
