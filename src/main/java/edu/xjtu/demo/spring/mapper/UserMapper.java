package edu.xjtu.demo.spring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

/***
 * Mapper是具体定义对象和数据库表之间关系的结构
 * 每个方法定义代表一种操作，调用时是以java方法的形式，执行时是以SQL的形式
 * 这种转换由MyBatis内部完成
 *
 */
public interface UserMapper{
    @Select(value="select * from user")
    List<Map<String, String>> selectAll();
    
    /*
    @Select(value="select * from user where id = #{id}")
    List<User> selectById(@Param("id")int id);
    
    @Select("select * from user where username=#{uname} and password=PASSWORD(#{pwd})")
    User findByNameAndPassword(@Param("uname") String username, @Param("pwd")String password);    
    
    //增加
	@Insert("insert into user(username,password) values(#{username},PASSWORD(#{password}))")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int createUser(User user);
	 
	//删除
	@Delete("update user set status = #{status} where id = #{id}")
	int changeStatus(@Param("status")Integer status,@Param("id")Integer id);
	 
	//修改
	@Update("update user set password = PASSWORD(#{password}) where id = #{id}")
	int modifyPassword(@Param("password") String password, @Param("id")Integer id);
    */
}
