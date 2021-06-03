package ga.rugal.ssm.core.service.impl;

import ga.rugal.DBTestBase;
import ga.rugal.ssm.core.entity.Course;
import ga.rugal.ssm.core.entity.Registration;
import ga.rugal.ssm.core.entity.Student;
import ga.rugal.ssm.core.service.CourseService;
import ga.rugal.ssm.core.service.RegistrationService;
import ga.rugal.ssm.core.service.StudentService;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrator
 */
public class RegistrationServiceImplTest extends DBTestBase
{

    @Autowired
    private Registration registration;

    @Autowired
    private Course course;

    @Autowired
    private Student student;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private RegistrationService registrationService;

    public RegistrationServiceImplTest()
    {
    }

    @Before
    public void setUp()
    {
        System.out.println("setUp");
        studentService.save(student);
        courseService.save(course);
        registrationService.save(registration);
    }

    @After
    public void tearDown()
    {
        System.out.println("tearDown");
        registrationService.delete(registration);
        courseService.delete(course);
        studentService.delete(student);
    }

    @Test
    public void testGetByPK()
    {
        System.out.println("getByPK");
        Registration r = registrationService.getByPK(registration.getRid());
        Assert.assertNotNull(r);
        Assert.assertNotNull(r.getCourse());
        Assert.assertNotNull(r.getStudent());
    }

    @Test
    @Ignore
    public void testSelectAll()
    {
        System.out.println("selectAll");
        List<Registration> result = registrationService.selectAll();
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    @Ignore
    public void testFindByCourse()
    {
        System.out.println("findByCourse");
        List<Registration> result = registrationService.findByCourse(course);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    @Ignore
    public void testFindByStudent()
    {
        System.out.println("findByStudent");
        List<Registration> result = registrationService.findByStudent(student);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    @Ignore
    public void testUpdate()
    {
        System.out.println("update");
        int result = registrationService.update(registration);
        Assert.assertEquals(1, result);
    }

}
