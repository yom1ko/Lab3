import java.util.*;

public class AStarState
{
    private Map2D map;
    private Map<Location, Waypoint> Opened = new java.util.HashMap<Location, Waypoint>();
    private Map<Location, Waypoint> Closed = new java.util.HashMap<Location, Waypoint>();

    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");
        this.map = map;
    }


    public Map2D getMap() {

        return map;
    }


    public Waypoint getMinOpenWaypoint() {
        // Проверка на то, есть ли в наборе открытые вершины
        if (Opened.size() == 0)
            return null;

        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>(Opened.values());
        float mincost = waypoints.get(0).getTotalCost();
        Waypoint min = waypoints.get(0);

        // Проходимся по всех точкам
        for (int i = 1; i < waypoints.size(); i++) {

            // Если текущий вес меньше, чем тот что был запомнен, то заменяем
            if (waypoints.get(i).getTotalCost() < mincost) {
                min = waypoints.get(i);
                mincost = min.getTotalCost();
            }
        }
        // Возвращаем точку с минимальной стоимостью
        return min;
    }


    public boolean addOpenWaypoint(Waypoint newWP)
    {
        if (Opened.get(newWP.getLocation()) == null )
        {
            Opened.put(newWP.getLocation(), newWP);
            return true;
        }
        else
        {
            if (Opened.get(newWP.getLocation()).getPreviousCost() >
                    newWP.getPreviousCost())
            {
                Opened.put(newWP.getLocation(), newWP);
                return true;
            }
        }
        return false;
    }
    public int numOpenWaypoints() {
        // Возвращаем размер
        return Opened.size();
    }


    public void closeWaypoint(Location loc) {
        // Перенос точки из открытых в закрытые
        Closed.put(loc, Opened.remove(loc));
    }
    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
        {
        // Проверка на нахождение в наборе указанного места
            if (Closed.containsKey(loc)) return true;
            return false;
    }
}