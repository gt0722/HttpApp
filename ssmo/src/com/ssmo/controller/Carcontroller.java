package com.ssmo.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssmo.pojo.Car;
import com.ssmo.pojo.Pager;
import com.ssmo.service.CarService;

@Controller
public class Carcontroller {
	@Resource(name = "carService")
	private CarService carService;

	@RequestMapping("carController_save")
	public String save(Car car) {
		if (car != null && car.getId() != null) {
			carService.modify(car);

		} else {
			carService.add(car);
		}
		return "redirect:carController_list";
	}

	@RequestMapping("carController_remove")
	public String Remove(@RequestParam(value = "id", required = true) Integer id) {
		carService.remove(id);
		return "redirect:carController_list";
	}

	@RequestMapping("carController_findById")
	public ModelAndView findById(@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView modelAndView = new ModelAndView("caredit");
		Car car = carService.findById(id);
		modelAndView.addObject("car", car);
		return modelAndView;
	}

	@RequestMapping("carController_list")
	public String list(ModelMap modelMap) {
		// 访问模型(业务逻辑层，返回模型数据)
		List<Car> cars = carService.find();

		// modelMap相当于map
		modelMap.put("cars", cars);
		// 逻辑视图层
		return "carlist";
	}

	@RequestMapping("carController_find")
	public ModelAndView find(@RequestParam(value = "name") String name,
			@RequestParam(value = "beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		ModelAndView modelAndView = new ModelAndView("carlist");
		Integer page = 1;
		Integer rows = 10;
		String sort = "id";
		String order = "asc";

		if (name != null && name != "") {
			name = "%" + name + "%";
		}
		Integer pageno = (page - 1) * rows;
		Integer pagesize = page * rows;
		Pager<Car> pager = carService.findPager(pageno, pagesize, sort, order, name, beginDate, endDate);
		modelAndView.addObject("cars", pager.getRows());

		return modelAndView;

	}
}
