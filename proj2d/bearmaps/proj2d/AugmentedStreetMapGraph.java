package bearmaps.proj2d;

import bearmaps.proj2ab.KdTree;
import bearmaps.proj2ab.Point;
import bearmaps.proj2c.streetmap.StreetMapGraph;
import bearmaps.proj2c.streetmap.Node;
import bearmaps.proj2d.trie.MyTrieSet;
import org.w3c.dom.ls.LSInput;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

    List<Point> points;
    Map<Point, Node> pointNodeMap;
    Map<String, String> cleanedNameMap;
    Map<String, List<Node>> nameNodeMap;
    KdTree kd;
    MyTrieSet locTrie;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        List<Node> nodes = this.getNodes();
        points = new ArrayList<>();
        pointNodeMap = new HashMap<>();
        locTrie = new MyTrieSet();
        cleanedNameMap = new HashMap<>();
        nameNodeMap = new HashMap<>();
        for (Node node : nodes){
            Point point = new Point(node.lon(), node.lat());
            if (neighbors(node.id()).size() > 0){
                points.add(point);
                pointNodeMap.put(point, node);
            }
            if (node.name() != null) {
                String cleanedName = cleanString(node.name());
                locTrie.add(cleanedName);
                cleanedNameMap.put(cleanedName, node.name());
                if (!nameNodeMap.containsKey(cleanedName)){
                    List<Node> nodeList = new ArrayList<>();
                    nodeList.add(node);
                    nameNodeMap.put(cleanedName, nodeList);
                }else{
                    List<Node> nodeList = nameNodeMap.get(cleanedName);
                    nodeList.add(node);
                }
            }
        }
        kd = new KdTree(points);
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point goal = kd.nearest(lon, lat);
        Node goalNode = pointNodeMap.get(goal);
        return goalNode.id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> cleanedName = locTrie.keysWithPrefix(prefix);
        List<String> locations = new ArrayList<>();
        for (String name : cleanedName){
            String realName = cleanedNameMap.get(name);
            locations.add(realName);
        }
        return locations;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Node> locList = nameNodeMap.get(cleanString(locationName));
        List<Map<String, Object>> results = new ArrayList<>();
        for (Node node : locList){
            Map<String, Object> loc = new HashMap<>();
            loc.put("lat", node.lat());
            loc.put("lon", node.lon());
            loc.put("name", node.name());
            loc.put("id", node.id());
            results.add(loc);
        }
        return results;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
