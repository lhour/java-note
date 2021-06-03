package ga.rugal.ssm.core.service;

import ga.rugal.ssm.core.entity.Course;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rugal Bernstein
 */
public interface CourseService
{

    /**
     *
     * {@inheritDoc }
     */
    int delete(Course student);

    List<Course> findByName(String name);

    /**
     *
     * {@inheritDoc }
     */
    @Transactional(readOnly = true)
    Course getByPK(Integer id);

    /**
     *
     * {@inheritDoc }
     */
    void save(Course contact);

    /**
     *
     * {@inheritDoc }
     */
    @Transactional(readOnly = true)
    List<Course> selectAll();

    /**
     *
     * {@inheritDoc }
     */
    int update(Course contact);

    int deleteByPK(Integer sid);

}
