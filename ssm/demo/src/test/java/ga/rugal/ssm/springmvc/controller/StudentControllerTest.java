package ga.rugal.ssm.springmvc.controller;

import ga.rugal.ControllerClientSideTestBase;
import ga.rugal.ssm.core.entity.Student;
import ga.rugal.ssm.core.service.StudentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Rugal Bernstein
 */
public class StudentControllerTest extends ControllerClientSideTestBase
{

    @Autowired
    private Student student;

    @Autowired
    private StudentService studentService;

    public StudentControllerTest()
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
    public void testGetStudent() throws Exception
    {
        this.mockMvc.perform(get("/student/" + student.getSid())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

}
