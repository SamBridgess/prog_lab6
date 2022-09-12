package ilya.common.Classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * main data class
 */
@XmlRootElement(name = "route")
@XmlType(propOrder = {"id", "name", "coordinates", "creationDate", "from", "to", "distance"})
public class Route implements Comparable<Route>, Serializable {
    private static final long serialVersionUID = 123123213;
    private Long id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private Location from;
    private Location to;
    private float distance;

    /**
     * creates new Route and generates ID and date of creation
     *
     * @param name          name of a route (cannot be null, cannot be empty)
     * @param coordinates   coordinates of a route (cannot be null)
     * @param from          "from" coordinates (can be null)
     * @param to            "to" coordinates (cannot be null)
     * @param distance      route length (must be greater than 1)
     */
    public Route(Long id, String name, Coordinates coordinates, Location from, Location to, float distance) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
    public Route() {
    }
    @XmlElement
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @XmlElement
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @XmlElement
    public Location getFrom() {
         return from;
    }
    public void setFrom(Location from) {
        this.from = from;
    }

    @XmlElement
    public Location getTo() {
        return to;
    }
    public void setTo(Location to) {
        this.to = to;
    }

    @XmlElement
    public float getDistance() {
        return distance;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * @return          returns string with information about each field
     */
    @Override
    public String toString() {
        return "Route{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", coordinates=" + coordinates
                + ", creationDate=" + creationDate
                + ", from=" + from
                + ", to=" + to
                + ", distance=" + distance
                + '}';
    }

    /**
     * @param r         the object to compare with
     * @return          returns the result of comparing objects
     */
    @Override
    public int compareTo(Route r) {
        return Float.compare(this.distance, r.getDistance()) == 0 ? (this.name.compareTo(r.getName()) == 0 ? (Long.compare(this.id, r.getId())) : this.name.compareTo(r.getName())) : Float.compare(this.distance, r.getDistance());
    }

    /**
     * @param o         the object to compare with
     * @return          returns true or false based on objects equality
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Route route = (Route) o;
        return Float.compare(route.distance, distance) == 0 && Objects.equals(id, route.id) && Objects.equals(name, route.name) && Objects.equals(coordinates, route.coordinates) && Objects.equals(creationDate, route.creationDate) && Objects.equals(from, route.from) && Objects.equals(to, route.to);
    }

    /**
     * @return          returns hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, from, to, distance);
    }
}
