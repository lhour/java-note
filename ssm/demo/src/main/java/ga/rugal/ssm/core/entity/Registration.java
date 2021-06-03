package ga.rugal.ssm.core.entity;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Rugal Bernstein
 */
public class Registration
{

    public static final String TABLE_NAME = "registration";

    @Expose
    private Integer rid;

    @Expose
    private Student student;

    @Expose
    private Course course;

    @Expose
    private Integer mark;

    public Registration()
    {
    }

    public Integer getRid()
    {
        return rid;
    }

    public void setRid(Integer rid)
    {
        this.rid = rid;
    }

    public Student getStudent()
    {
        return student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public Course getCourse()
    {
        return course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    public Integer getMark()
    {
        return mark;
    }

    public void setMark(Integer mark)
    {
        this.mark = mark;
    }

}
