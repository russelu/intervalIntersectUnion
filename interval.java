// "static void main" must be defined in a public class.

class Interval {
    public int start, end;
    public Interval(int s, int e) {start = s; end = e;}
}

class Solution {
    
    public static List<Interval> union(List<Interval> list1, List<Interval> list2) {
        if (list1.isEmpty()) return list2;
        if (list2.isEmpty()) return list1;
        List<Interval> res = new ArrayList<Interval>();
        Interval curr;
        int i = 0, j = 0;
        Interval pre, cur;
        Interval a = list1.get(0), b = list2.get(0);
        if (a.start <= b.start) {
            pre = a;
            i++;
        } else {
            pre = b;
            j++;
        }
        while (i < list1.size() || j < list2.size()) {
            a = i == list1.size() ? null: list1.get(i);
            b = j == list2.size() ? null: list2.get(j);
            if (a == null || b != null && b.start <= a.start) {
                cur = b;
                j++;
            } else {
                cur = a;
                i++;
            }
            if (pre.end + 1 < cur.start) {
                res.add(pre);
                pre = cur;
            } else {
                pre.end = Math.max(pre.end, cur.end);
            }
        }
        res.add(pre);
        return res;
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
        int start = Math.max(a.start, b.start);
        int end = Math.min(a.end, b.end);
        if (!res.isEmpty() && start - 1 == res.get(res.size() - 1).end) {
            res.get(res.size() - 1).end = end;
        } else {
            res.add(new Interval(start, end));
        }
    }
    
    public static void buildList(List<Interval> list1, List<Interval> list2) {
        /* Case 1
        list1.add(new Interval(0, 2));
        list1.add(new Interval(5, 10));
        list1.add(new Interval(13, 23));
        list1.add(new Interval(24, 25));
        list2.add(new Interval(1, 5));
        list2.add(new Interval(8, 12));
        list2.add(new Interval(15, 18));
        list2.add(new Interval(20, 24));
        */
        //Case 2
        list1.add(new Interval(0, 2));
        list1.add(new Interval(3, 4));
        list1.add(new Interval(8, 8));
        list1.add(new Interval(11, 15));
        list1.add(new Interval(120, 130));

        list2.add(new Interval(5, 8));
        list2.add(new Interval(9, 9));
        list2.add(new Interval(10, 19));
        list2.add(new Interval(100, 200));
        //expecting intersect [8, 8] [11, 15] [120, 130]
        //expecting union [0, 19] [100, 200]
        /*
        list1.add(new Interval(0, 18));

        list2.add(new Interval(5, 8));
        list2.add(new Interval(9, 9));
        list2.add(new Interval(10, 19));
        //expecting intersect [5, 8] [9, 9] [10, 18]
        //expecting union [0,19]
        */
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
        List<Interval> is = Solution.intersect(list2, list1);
        Solution.printList(is);
        System.out.println();
        is = Solution.union(list2, list1);
        Solution.printList(is);
    }
}
