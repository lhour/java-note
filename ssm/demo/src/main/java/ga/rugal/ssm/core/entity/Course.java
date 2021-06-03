package ga.rugal.ssm.core.entity;

import com.google.gson.annotations.Expose;
import java.util.List;

/**
 *
 * @author Rugal Bernstein
 */
public class Course {

    public static final String TABLE_NAME = "course";

    @Expose
    private Integer cid;

    @Expose
    private String name;

    @Expose
    private List<Registration> registrations;

    public Course() {
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
}
