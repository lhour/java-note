package ga.rugal.ssm.core.service.impl;

import ga.rugal.ssm.core.entity.Student;
import ga.rugal.ssm.core.mapper.StudentMapper;
import ga.rugal.ssm.core.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService
{

    @Autowired
    private StudentMapper studentMapper;

    /**
     *
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public List<Student> selectAll()
    {
        return studentMapper.selectAll();
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public Student getByPK(Integer id)
    {
        return studentMapper.getByPK(id);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public List<Student> findByName(String name)
    {
        return studentMapper.findByName(name);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    public void save(Student student)
    {
        studentMapper.save(student);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    public int delete(Student student)
    {
        return studentMapper.delete(student);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    public int update(Student student)
    {
        return studentMapper.update(student);
    }

    @Override
    public int deleteByPK(Integer sid)
    {
        return studentMapper.deleteByPK(sid);
    }
}
