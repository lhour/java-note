package ga.rugal.ssm.core.service.impl;

import ga.rugal.DBTestBase;
import ga.rugal.ssm.core.entity.Student;
import ga.rugal.ssm.core.service.StudentService;
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
public class StudentServiceImplTest extends DBTestBase
{

    @Autowired
    private Student student;

    @Autowired
    private StudentService studentService;

    public StudentServiceImplTest()
    {
    }

    @Before
    public void setUp()
    {
        System.out.println("setUp");
        studentService.save(student);
    }

    @After
    public void tearDown()
    {
        System.out.println("tearDown");
        studentService.delete(student);
    }

    @Test
    public void testSelectAll()
    {
        System.out.println("selectAll");
        List<Student> result = studentService.selectAll();
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void testGetByPK()
    {
        System.out.println("getByPK");
        Student result = studentService.getByPK(student.getSid());
        Assert.assertNotNull(result);
//        Assert.assertFalse(result.getRegistrations().isEmpty());
    }

    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Student updated = new Student();
        updated.setSid(student.getSid());
        updated.setName("Updated");
        int result = studentService.update(updated);
        Assert.assertEquals(1, result);

    }

    @Test
    public void testFindByName()
    {
        System.out.println("findByName");
        List<Student> result = studentService.findByName("Rugal");
        Assert.assertFalse(result.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testTransactional()
    {
        System.out.println("Transactional");
        studentService.save(student);
        throw new RuntimeException();
    }

}
