/*
 * PathList Class contains list of paths.  Path is the relationship between two 
 * species nodes, as determined by linked predator and prey nodes
 */
package shared.simulation.simjob;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author justinacotter
 */
public class PathList {
    
    //class Path is created rather than using string so that updates by 
    //reference (pointer) can work through the recursion.  Allows paths to be
    //stored in end-use PathList Array while recursion passes the paths back
    //as non-pair-matched Lists.  Updates to List entries update the Array.
    List <Path> pathList;
    
    public PathList ()
    {
        this.pathList = new ArrayList<Path>();
    }
    
    public PathList (String path)
    {
        this.pathList = new ArrayList<Path>();
        pathList.add(new Path(path));
    }
    
    public void addPath (Path path)
    {
        pathList.add(path);
    }
    
    public void setPathI (int idx, Path path)
    {
        pathList.set(idx, path);
    }
    
    public Path getPathI (int idx)
    {
        return pathList.get(idx);
    }
    
    public List <Path> getPathList ()
    {
        return pathList;
    }
    
}
