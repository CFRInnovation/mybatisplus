package com.coding.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coding.mybatisplus.entity.User;
import com.coding.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisplusApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {

		List<User> users = userMapper.selectList(null);

		users.forEach(System.out::println);
	}

	@Test
	public void testInsert(){
		//id 会自动生成，生成的方式是twitter的雪花算法
		User user = new User();
		user.setName("Zeng");
		user.setAge(24);
		user.setEmail("470278353@qq.com");

		int insert = userMapper.insert(user);
		System.out.println(insert);
		System.out.println(user);
	}

	@Test
	public void testUpdate(){

		User user = new User();
		user.setId(1237387681550348298L);
		user.setEmail("121212121@qq.com");

		userMapper.updateById(user);

	}

	//删除： 悲观锁  乐观锁
	//逻辑删除  乐观锁
	/**
	 *
	 */
	//测试修改成功
	@Test
	public void testOptimisticLocker(){
		//t通过查询修改，避免直接修改
		User user = userMapper.selectById(1237387681550348298L);
		user.setEmail("22222222222222222222@qq.com");
		userMapper.updateById(user);
	}

	//测试插入失败  模拟两个线程，测试结果
	@Test
	public void testOptimisticLockerFail(){
		//t通过查询修改，避免直接修改
		//Thread 1 查询
		User user = userMapper.selectById(1237387681550348298L);
		//Thread 1 修改
		user.setEmail("33333333333333333333@qq.com");

		//Thread 2 查询
		User user2 = userMapper.selectById(1237387681550348298L);
		//Thread 2 修改
		user2.setEmail("444444444444444444444@qq.com");

		//Thread 2 执行更新
		userMapper.updateById(user2);

		//Thread 1 执行更新
		userMapper.updateById(user);
	}

	@Test
	public void testSelect(){
		User user = userMapper.selectById(1237387681550348298L);
		System.out.println(user);
	}

	//批量查询
	@Test
	public void testSelectbatch(){
		List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
		users.forEach(System.out::println);
	}

	//简单的条件查询
	@Test
	public void testSelectByMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("email", "470278353@qq.com");
		map.put("age", 23);

		List<User> users = userMapper.selectByMap(map);
		users.forEach(System.out::println);
	}

	//分页插件 测试
	@Test
	public void testPagination(){
		Page<User> page = new Page<>(2, 8);
		// Wapper 条件构造器， 模糊查询 排序  多表查询  子查询....
		userMapper.selectPage(page, null);

		page.getRecords().forEach(System.out::println);

		System.out.println(page.getCurrent());
		System.out.println(page.getPages());
		System.out.println(page.getSize());
		System.out.println(page.getTotal());
		System.out.println(page.hasNext());
		System.out.println(page.hasPrevious());
	}

	@Test
	public void testMapPage(){
		Page<Map<String, Object>> page = new Page<>(2, 8);

		IPage<Map<String, Object>> e = userMapper.selectMapsPage(page, null);

		e.getRecords().forEach(System.out::println);
	}




}
