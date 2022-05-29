import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC983 {
    int[] costs;
    Integer[] plan;
    Set<Integer> dayset;

    public LC983 () {
        this.costs = new int[3];
        this.costs[0] = 2;
        this.costs[1] = 7;
        this.costs[2] = 15;
    }

    public int endBackFront(int[] days) {
        plan = new Integer[366];
        dayset = new HashSet();
        for (int d : days) {
            dayset.add(d);
        }
        int result = dp1(1);
        System.out.println(Arrays.asList(plan));
        return result;
    }

    public int frontToEnd (int[] days) {
        int len = days.length;
        int maxDay = days[len - 1];
        plan = new Integer[maxDay + 1];
        plan[0] = 0;
        for (int d : days) {
            dayset.add(d);
        }
        int result = dp2(days);
        System.out.println(Arrays.asList(plan));
        return result;
    }

    public int dp1(int i) {
        if (i > 365) {
            return 0;
        }
        if (plan[i] != null) {
            return plan[i];
        }
        if (dayset.contains(i)) {
            plan[i] = Math.min(Math.min(dp1(i + 1) + costs[0], dp1(i + 7) + costs[1]), dp1(i + 30) + costs[2]);
        } else {
            plan[i] = dp1(i + 1);
        }
        return plan[i];
    }

    public int dp2 (int[] days) {
        for (int i = 1; i < plan.length; i++) {
            if (!dayset.contains(i)) {
                plan[i] = plan[i-1];
            } else {
                int firstCost = plan[i-1] + costs[0];
                int secondCost = i > 7 ? plan[i-7] + costs[1] : costs[1];
                int thirdCost = i > 30 ? plan[i-30] + costs[2] : costs[2];
                plan[i] = Math.min(Math.min(firstCost, secondCost), thirdCost);
            }
        }
        return plan[days[days.length-1]];
    }

    @Test
    public void test () {
        int[] days = {1,2,3,4,5,6,20};
        System.out.println(endBackFront(days));
        System.out.println(frontToEnd(days));
    }
}
