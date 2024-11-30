package app.bike;
import java.util.List;

public interface  BikeRoutes{
    public int getRecordCount();
    public List<BikeRoute> getOffRoad();
    public List<BikeRoute> getOnRoad();
}
