package ga.rugal.ssm.core.service.impl;

import ga.rugal.DBTestBase;
import ga.rugal.ssm.core.entity.Course;
import ga.rugal.ssm.core.service.CourseService;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
public class CourseServiceImplTest extends DBTestBase
{

    @Autowired
    private Course course;

    @Autowired
    private CourseService courseService;

    public CourseServiceImplTest()
    {
    }

    @Before
    public void setUp()
    {
        System.out.println("setUp");
        courseService.save(course);
    }

    @After
    public void tearDown()
    {
        System.out.println("tearDown");
        courseService.delete(course);
    }

    @Test
    public void testSelectAll()
    {
        System.out.println("selectAll");
        List<Course> result = courseService.selectAll();
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void testGetByPK()
    {
        System.out.println("getByPK");
        courseService.getByPK(course.getCid());
    }

    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Course updated = new Course();
        updated.setCid(course.getCid());
        updated.setName("Updated");
        int result = courseService.update(updated);
        Assert.assertEquals(1, result);

    }

    @Test
    public void testFindByName()
    {
        System.out.println("findByName");
        List<Course> result = courseService.findByName("Data");
        Assert.assertFalse(result.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testTransactional()
    {
        System.out.println("Transactional");
        courseService.save(course);
        throw new RuntimeException();
    }

}
