package ga.rugal.ssm.core.service;

import ga.rugal.ssm.core.entity.Course;
import ga.rugal.ssm.core.entity.Registration;
import ga.rugal.ssm.core.entity.Student;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rugal Bernstein
 */
public interface RegistrationService
{

    /**
     *
     * {@inheritDoc }
     */
    int delete(Registration registration);

    int deleteByPK(Integer sid);

    /**
     *
     * {@inheritDoc }
     */
    @Transactional(readOnly = true)
    List<Registration> findByCourse(Course course);

    /**
     *
     * {@inheritDoc }
     */
    @Transactional(readOnly = true)
    List<Registration> findByStudent(Student student);

    /**
     *
     * {@inheritDoc }
     */
    @Transactional(readOnly = true)
    Registration getByPK(Integer id);

    /**
     *
     * {@inheritDoc }
     */
    void save(Registration contact);

    /**
     *
     * {@inheritDoc }
     */
    @Transactional(readOnly = true)
    List<Registration> selectAll();

    /**
     *
     * {@inheritDoc }
     */
    int update(Registration contact);

}
