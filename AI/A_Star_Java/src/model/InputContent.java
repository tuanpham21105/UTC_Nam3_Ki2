package model;

import java.util.Dictionary;
import java.util.Hashtable;

public class InputContent {
    public String startNodeName;
    public String endNodeName;
    public Dictionary<String, Node> nodes = new Hashtable<String,Node>();
}
