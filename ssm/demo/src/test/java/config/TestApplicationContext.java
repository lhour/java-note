package config;

import ga.rugal.ssm.core.entity.Course;
import ga.rugal.ssm.core.entity.Registration;
import ga.rugal.ssm.core.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Rugal Bernstein
 */
@Configuration
public class TestApplicationContext
{

    @Bean
    @Autowired
    public Registration registration(Student student, Course course)
    {
        Registration r = new Registration();
        r.setCourse(course);
        r.setStudent(student);
        r.setMark(100);
        return r;
    }

    @Bean
    public Course course()
    {
        Course c = new Course();
        c.setName("Data Structure");
        return c;
    }

    @Bean
    public Student student()
    {
        Student s = new Student();
        s.setName("Rugal Bernstein");
        return s;
    }

}
