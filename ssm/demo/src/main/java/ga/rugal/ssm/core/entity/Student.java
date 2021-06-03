package ga.rugal.ssm.core.entity;

import com.google.gson.annotations.Expose;
import java.util.List;

/**
 *
 * @author Rugal Bernstein
 */
public class Student
{

    public static final String TABLE_NAME = "student";

    @Expose
    private Integer sid;

    @Expose
    private String name;

    @Expose
    private List<Registration> registrations;

    public Student()
    {

    }

    public Integer getSid()
    {
        return sid;
    }

    public void setSid(Integer sid)
    {
        this.sid = sid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Registration> getRegistrations()
    {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations)
    {
        this.registrations = registrations;
    }

}
