import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        for (int i = 1000; i <= 64000; i = i*2){
            Ns.add(i);
        }
        AList alist = new AList<Integer>();
        for (int n : Ns) {
            int count = 0;
            Stopwatch sw = new Stopwatch();
            for (int i=0; i < n; i++){
                alist.addLast(2);
                count++;
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
            opCounts.add(count);
        }
        printTimingTable(Ns, times, opCounts);

    }


}
