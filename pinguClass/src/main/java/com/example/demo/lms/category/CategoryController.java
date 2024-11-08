package com.example.demo.lms.category;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.lms.course.CourseDTO;
import com.example.demo.lms.course.CourseService;
import com.example.demo.lms.entity.Course;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CategoryController {

	private final CategoryService categoryService;
	private final CourseService courseService;
 
	/*************************************** 카테고리 ***************************************/
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 카테고리 창으로 보내기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */	
	@GetMapping("/category")
	public String category( Model model) {

		//List<Course> courseList = this.categoryService.getCourseByKeyword();
		
		List<CourseDTO> courseList = this.categoryService.getAllCourseDTOList();
				
		model.addAttribute("courseList", courseList);

		return "/category/categorypage";
	}
		
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 카테고리 창으로 보내기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */	
//	@GetMapping("/category/{id}")
//	public String category( Model model,@PathVariable("id") Integer categoryId) {
//
//		//List<Course> courseList = this.categoryService.getCourseByKeyword();
//		
//		List<CourseDTO> courseList = this.categoryService.getCourseDTOList(categoryId);
//				
//		model.addAttribute("courseList", courseList);
//
//		return "/category/categorypage";
//	}

	
	
	
	
	
}