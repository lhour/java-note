package ga.rugal.ssm.springmvc.controller;

import ga.rugal.ssm.core.service.StudentService;
import ml.rugal.sshcommon.springmvc.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Rugal Bernstein
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController
{

    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class.getName());

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping(value = "/{sid}", method = RequestMethod.GET)
    public Message getStudent(@PathVariable("sid") Integer sid)
    {
        return Message.successMessage("Get student", studentService.getByPK(sid));
    }
}
