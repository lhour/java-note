package ga.rugal.ssm.core.mapper;

import ga.rugal.ssm.core.entity.Student;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
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
public interface StudentMapper
{

    String TABLE_NAME = Student.TABLE_NAME;

    @Select("SELECT * FROM " + TABLE_NAME)
    List<Student> selectAll();

    @Select("SELECT * FROM " + TABLE_NAME + " WHERE sid = #{sid}")
    @Results(value =
    {
        @Result(id = true, property = "sid", column = "sid", javaType = Integer.class),
        @Result(property = "name", column = "name", javaType = String.class),
        @Result(property = "registrations", column = "sid", javaType = List.class,
            many = @Many(select = "ga.rugal.ssm.core.mapper.RegistrationMapper.findByStudent",
                fetchType = FetchType.LAZY))
    })
    Student getByPK(Integer sid);

    @Select("SELECT * FROM " + TABLE_NAME + " WHERE name LIKE '%${name}%' ")
    List<Student> findByName(@Param(value = "name") String name);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE sid = #{sid}")
    int delete(Student bean);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE sid = #{sid}")
    int deleteByPK(@Param("sid") Integer sid);

    @Insert("INSERT INTO " + TABLE_NAME + "(name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "sid")
    void save(Student bean);

    @Update("UPDATE " + TABLE_NAME + " SET name = #{name} WHERE sid = #{sid}")
    int update(Student bean);
}
