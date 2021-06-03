package ga.rugal.ssm.core.service.impl;

import ga.rugal.ssm.core.entity.Course;
import ga.rugal.ssm.core.mapper.CourseMapper;
import ga.rugal.ssm.core.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     *
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public List<Course> selectAll() {
        return courseMapper.selectAll();
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public Course getByPK(Integer id) {
        return courseMapper.getByPK(id);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public List<Course> findByName(String name) {
        return courseMapper.findByName(name);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    public void save(Course course) {
        courseMapper.save(course);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    public int delete(Course course) {
        return courseMapper.delete(course);
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    public int update(Course course) {
        return courseMapper.update(course);
    }

    @Override
    public int deleteByPK(Integer sid) {
        return courseMapper.deleteByPK(sid);
    }
}
