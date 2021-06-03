package ga.rugal.ssm.core.mapper;

import ga.rugal.ssm.core.entity.Course;
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
public interface CourseMapper
{

    String TABLE_NAME = Course.TABLE_NAME;

    @Select("SELECT * FROM " + TABLE_NAME)
    List<Course> selectAll();

    @Select("SELECT * FROM " + TABLE_NAME + " WHERE cid = #{cid}")
    @Results(value =
    {
        @Result(id = true, property = "cid", column = "cid", javaType = Integer.class),
        @Result(property = "name", column = "name", javaType = String.class),
        @Result(property = "registrations", column = "cid", javaType = List.class,
            many = @Many(select = "ga.rugal.ssm.core.mapper.RegistrationMapper.findByCourse",
                fetchType = FetchType.LAZY))
    })
    Course getByPK(Integer cid);

    @Select("SELECT * FROM " + TABLE_NAME + " WHERE name LIKE '%${name}%' ")
    List<Course> findByName(@Param(value = "name") String name);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE cid = #{cid}")
    int delete(Course bean);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE cid = #{cid}")
    int deleteByPK(@Param("cid") Integer cid);

    @Insert("INSERT INTO " + TABLE_NAME + "(name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "cid")
    void save(Course bean);

    @Update("UPDATE " + TABLE_NAME + " SET name = #{name} WHERE cid = #{cid}")
    int update(Course bean);
}
