package ga.rugal.ssm.core.mapper;

import ga.rugal.ssm.core.entity.Course;
import ga.rugal.ssm.core.entity.Registration;
import ga.rugal.ssm.core.entity.Student;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

/**
 *
 * @author Rugal Bernstein
 */
public interface RegistrationMapper
{

    String TABLE_NAME = Registration.TABLE_NAME;

    @Select("SELECT * FROM " + TABLE_NAME)
    List<Registration> selectAll();

    @Select("SELECT * FROM " + TABLE_NAME + " WHERE rid = #{rid}")
    @Results(value =
    {
        @Result(property = "student", column = "sid", javaType = Student.class,
            one = @One(select = "ga.rugal.ssm.core.mapper.StudentMapper.getByPK",
                fetchType = FetchType.LAZY)),
        @Result(property = "course", column = "cid", javaType = Course.class,
            one = @One(select = "ga.rugal.ssm.core.mapper.CourseMapper.getByPK",
                fetchType = FetchType.LAZY))
    })
    Registration getByPK(@Param("rid") Integer rid);

    @Select("SELECT * FROM " + TABLE_NAME + " WHERE sid = #{sid}")
    List<Registration> findByStudent(Student student);

    @Select("SELECT * FROM " + TABLE_NAME + " WHERE cid = #{cid}")
    List<Registration> findByCourse(Course course);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE rid = #{rid}")
    int delete(Registration bean);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE rid = #{rid}")
    int deleteByPK(@Param("rid") Integer rid);

    @Insert(
        "INSERT INTO " + TABLE_NAME + "(sid, cid, mark) VALUES (#{student.sid}, #{course.cid}, #{mark})")
    @Options(useGeneratedKeys = true, keyProperty = "rid")
    void save(Registration bean);

    @Update("UPDATE " + TABLE_NAME
            + " SET mark = #{mark}, cid = #{course.cid}, sid = #{student.sid} WHERE rid = #{rid}")
    int update(Registration bean);
}
