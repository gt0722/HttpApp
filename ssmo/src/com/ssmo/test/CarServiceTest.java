package com.ssmo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssmo.pojo.Car;
import com.ssmo.pojo.Pager;
import com.ssmo.service.CarService;

public class CarServiceTest {

	private CarService carService;

	@Before
	public void init() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		carService = ctx.getBean("carService", CarService.class);
	}

	@Test
	public void add() {
		Car car = new Car();
		car.setName("保时捷");
		car.setSaleDate(new Date());
		car.setPrice(190000.00);
		if (carService.add(car) > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void find() {
		for (Car car : carService.find()) {
			System.out.println(car.getName() + " " + car.getSaleDate() + " " + car.getPrice());
		}
	}

	@Test
	public void modify() {
		Car car = new Car();
		car.setId(3);
		car.setName("大众");
		car.setSaleDate(new Date());
		car.setPrice(80000.00);
		if (carService.modify(car) > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void findById() {
		Car car = carService.findById(3);
		System.out.println(car.getName() + " " + car.getSaleDate() + " " + car.getPrice());
	}

	@Test
	public void findPager() {
		// easyui 必需的参数
		Integer page = 1;
		Integer rows = 10;
		String sort = "id";
		String order = "asc";

		// 条件参数 (可选)
		String name = null;
		Date beginDate = null;
		Date endDate = null;

		// name = "%S%";
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			beginDate = sdf.parse("2016-10-01");
			endDate = sdf.parse("2016-12-13");
		} catch (ParseException e) {
			e.printStackTrace();
		}*/

		// 计算分页的起始页和结束页
		Integer pageno = (page - 1) * rows;
		Integer pagesize = page * rows;
		Pager<Car> pager = carService.findPager(pageno, pagesize, sort, order, name, beginDate, endDate);
		
		
		System.out.println("记录总数: " + pager.getTotal());
		for (Car car : pager.getRows()) {
			System.out.println(car.getName() + " " + car.getSaleDate() + " " + car.getPrice());
		}
	}
}
