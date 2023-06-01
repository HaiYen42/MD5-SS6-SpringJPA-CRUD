package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.Student;
import rikkei.academy.service.IStudentService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/student"})
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @GetMapping
    public String showListStudent(Model model){
        List<Student> studentList = studentService.findALl();
        model.addAttribute("listStudent", studentList);
        return "student/list";
    }
    @PostMapping("/search")
    public String showListSearch(Model model, @RequestParam("search") String search){
        List<Student> studentList = studentService.findByNameStatic(search);
        model.addAttribute("listStudent", studentList);
        return "student/list";
    }
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable Long id, Model model){
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/detail";
    }
    @GetMapping("/create")
    public String showFormCreate(){
        return "student/create";
    }
    @PostMapping("/create")
    public String actionCreate(@RequestParam String name){
        List<Student> studentList = studentService.findALl();
        Long id=0L;
        if (studentList.size()==0) {
            id= 1L;
        }else {
            id = studentList.get(studentList.size()-1).getId()+1;
        }
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        studentService.save(student);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String showFormDelete(@PathVariable Long id, Model model){
        Student student =studentService.findById(id);
        model.addAttribute("student",student);
        return "/student/delete";
    }
    @PostMapping("delete/{id}")
    public String actionDelete(@PathVariable Long id){
        studentService.deleteById(id);
        return "redirect:/";
    }
}
