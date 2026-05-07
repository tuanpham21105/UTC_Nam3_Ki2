package solve;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.PriorityQueue;
import model.InputContent;
import model.Node;
import model.PriorityQueueNode;
import model.RelativeNode;

public class SolveHandler {
    public static String solve(InputContent inputContent) {
        String stepsText = "";
        String pathText = "";
        int cost = 0;
        int steps = 0;

        PriorityQueue<PriorityQueueNode> pq = new PriorityQueue<>();
        Dictionary<String, String> marker = new Hashtable<>();

        pq.add(new PriorityQueueNode(inputContent.startNodeName, 0));

        stepsText += "|Steps\t|Now\t|Next\t|k(u,v)\t|h(v)\t|g(v)\t|f(v)\t|Queue\t|\n";
        stepsText += "==========================================================================================================================================\n";
        stepsText += "|0\t\t|\t\t|\t\t|\t\t|\t\t|\t\t|\t\t|" + priorityQueueToString(pq) + "\t|\n";
        stepsText += "==========================================================================================================================================\n";
        
        while (!inputContent.nodes.isEmpty()) {
            PriorityQueueNode currentPqNode = pq.poll();

            if (currentPqNode == null)
                break;

            steps++;

            if (currentPqNode.nodeName.compareTo(inputContent.endNodeName) == 0) {
                cost = currentPqNode.f - inputContent.nodes.get(inputContent.endNodeName).h;
                stepsText += "|" + steps + "\t\t|" + inputContent.endNodeName + "\t\t|Trạng thái kết thúc - Dừng\t|\n";
                break;
            }

            String currentNodeName = currentPqNode.nodeName;
            Node currentNode = inputContent.nodes.get(currentPqNode.nodeName);

            if (currentNode.relativeNodes.size() == 0) {
                stepsText += "|" + steps + "\t\t" + 
                            "|" + currentNodeName + "\t\t" + 
                            "|" + "\t\t" + 
                            "|" + 0 + "\t\t" + 
                            "|" + 0 + "\t\t" + 
                            "|" + 0 + "\t\t" + 
                            "|" + 0 + "\t\t" + 
                            "|" + priorityQueueToString(pq) + "\t|\n";

                stepsText += "==========================================================================================================================================\n";

                continue;
            }

            int substep = 0;

            for (RelativeNode relativeRNode : currentNode.relativeNodes) {
                substep++;

                String relativeNodeName = relativeRNode.nodeName;
                Node relativeNode = inputContent.nodes.get(relativeRNode.nodeName);

                marker.put(relativeNodeName, currentNodeName);

                int k = relativeRNode.k;
                int h = relativeNode.h;
                int g = k + ((currentNodeName.compareTo(inputContent.startNodeName) == 0) ? 0 : currentPqNode.f - currentNode.h);
                int f = h + g;

                pq.add(new PriorityQueueNode(relativeNodeName, f));
                
                stepsText += "|" + (substep == 1 ? steps : "") + "\t\t" + 
                            "|" + (substep == 1 ? currentNodeName : "") + "\t\t" + 
                            "|" + relativeNodeName + "\t\t" + 
                            "|" + k + "\t\t" + 
                            "|" + h + "\t\t" + 
                            "|" + g + "\t\t" + 
                            "|" + f + "\t\t" + 
                            "|" + (substep == currentNode.relativeNodes.size() ? priorityQueueToString(pq) : "") + "\t|\n";
            }

            stepsText += "==========================================================================================================================================\n";
        }

        String currentMarker = marker.get(inputContent.endNodeName);
        pathText += inputContent.endNodeName;

        while (currentMarker != null && !currentMarker.isBlank()) {
            pathText = currentMarker + " -> " + pathText;

            currentMarker = marker.get(currentMarker);
        }

        return " * Steps\n"
                + stepsText
                + " * Path: " + pathText + "\n"
                + " * Cost: " + cost;
    }

    static String priorityQueueToString(PriorityQueue<PriorityQueueNode> pq) {
        StringBuilder sb = new StringBuilder();

        for (PriorityQueueNode node : pq) {
            sb.append("(")
            .append(node.nodeName)
            .append(", ")
            .append(node.f)
            .append("), ");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }
}
