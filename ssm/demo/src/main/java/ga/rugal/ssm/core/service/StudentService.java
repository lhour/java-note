package ga.rugal.ssm.core.service;

import ga.rugal.ssm.core.entity.Student;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rugal Bernstein
 */
public interface StudentService
{

    /**
     *
     * {@inheritDoc }
     */
    int delete(Student student);

    List<Student> findByName(String name);

    /**
     *
     * {@inheritDoc }
     */
    @Transactional(readOnly = true)
    Student getByPK(Integer id);

    /**
     *
     * {@inheritDoc }
     */
    void save(Student contact);

    /**
     *
     * {@inheritDoc }
     */
    @Transactional(readOnly = true)
    List<Student> selectAll();

    /**
     *
     * {@inheritDoc }
     */
    int update(Student contact);

    int deleteByPK(Integer sid);

}
