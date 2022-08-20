package bearmaps.proj2c;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.security.cert.TrustAnchor;
import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    SolverOutcome outcome;
    private List<Vertex> solution;
    private double solutionWeight;
    private ArrayHeapMinPQ<Vertex> Fringe;
    private Map<Vertex, Double> distToMap;
    private Map<Vertex, Vertex> edgeToMap;
    private double timeSpent;
    int numStatesExplored;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        //initialization:
        distToMap = new HashMap<>();
        Fringe = new ArrayHeapMinPQ<>();
        Stopwatch sw = new Stopwatch();
        edgeToMap = new HashMap<>();
        timeSpent = 0.0;
        numStatesExplored = 0;
        solution = new ArrayList<>();
        solutionWeight = 0.0;

        //add starting point to priority queue
        Fringe.add(start, input.estimatedDistanceToGoal(start,end));
        distToMap.put(start, 0.0);
        while(true){
            //need to add timeout constraint.
            timeSpent = sw.elapsedTime();
            if (timeSpent > timeout){
                outcome = SolverOutcome.TIMEOUT;
                break;
            }
            if (Fringe.size()==0){
                outcome = SolverOutcome.UNSOLVABLE;
                break;
            }
            if (Fringe.getSmallest().equals(end)){
                solution = solutionList(start, end);
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distToMap.get(end);
                timeSpent = sw.elapsedTime();
                break;
            }
            Vertex p = Fringe.removeSmallest();
            numStatesExplored++;
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(p);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                relax(e, end, input);
            }
        }

    }

    public SolverOutcome outcome(){
        return outcome;
    }

    public List<Vertex> solution(){
        return solution;
    }
    public double solutionWeight(){
        return solutionWeight;
    }
    public int numStatesExplored(){
        return numStatesExplored;
    }
    public double explorationTime(){
        return timeSpent;
    }

    private double distTo (Vertex e){
        if (distToMap.get(e) == null){
            return Double.POSITIVE_INFINITY;
        }
        return distToMap.get(e);
    }

    private void relax(WeightedEdge<Vertex> e, Vertex goal, AStarGraph<Vertex> input){
        Vertex p = e.from();
        Vertex q = e.to();
        Double w = e.weight();
        if (distTo(p) + w < distTo(q)){
            if (distToMap.containsKey(q)){
                distToMap.replace(q, distTo(p) + w);
            }else{
                distToMap.put(q, distTo(p) + w);
            }

            if (edgeToMap != null && edgeToMap.containsKey(q) ){
                edgeToMap.replace(q, p);
            }else{
                edgeToMap.put(q, p);
            }
            if (Fringe.contains(q)){
                Fringe.changePriority(q, distTo(q) + input.estimatedDistanceToGoal(q, goal));
            }else{
                Fringe.add(q, distTo(q) + input.estimatedDistanceToGoal(q, goal));
            }
        }
    }

    private List<Vertex> solutionList(Vertex start, Vertex end){
        Vertex e = end;
        LinkedList<Vertex> bestRoute = new LinkedList<>();
        bestRoute.add(end);
        while (!e.equals(start)){
            e = edgeToMap.get(e);
            bestRoute.addFirst(e);
        }
        return bestRoute;
    }
}
