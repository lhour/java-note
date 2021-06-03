package ga.rugal.ssm.core.service.impl;

import ga.rugal.ssm.core.entity.Course;
import ga.rugal.ssm.core.entity.Registration;
import ga.rugal.ssm.core.entity.Student;
import ga.rugal.ssm.core.mapper.RegistrationMapper;
import ga.rugal.ssm.core.service.RegistrationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService
{

    @Autowired
    private RegistrationMapper registrationMapper;

    /**
     *
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public List<Registration> selectAll()
    {
        return registrationMapper.selectAll();
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public Registration getByPK(Integer id)
    {
        return registrationMapper.getByPK(id);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public List<Registration> findByStudent(Student student)
    {
        return registrationMapper.findByStudent(student);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public List<Registration> findByCourse(Course course)
    {
        return registrationMapper.findByCourse(course);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    public void save(Registration registration)
    {
        registrationMapper.save(registration);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    public int delete(Registration registration)
    {
        return registrationMapper.delete(registration);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    public int update(Registration registration)
    {
        return registrationMapper.update(registration);
    }

    @Override
    public int deleteByPK(Integer sid)
    {
        return registrationMapper.deleteByPK(sid);
    }
}
