package ua.goit.gojava32.kickstarter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ua.goit.gojava32.kickstarter.model.Category;
import ua.goit.gojava32.kickstarter.model.Project;
import ua.goit.gojava32.kickstarter.service.CategoryService;
import ua.goit.gojava32.kickstarter.service.CommentService;
import ua.goit.gojava32.kickstarter.service.ProjectService;
import ua.goit.gojava32.kickstarter.service.UserService;

@Controller
public class ProjectController {

  @Autowired
  ProjectService projectService;
  @Autowired
  CommentService commentService;
  @Autowired
  CategoryService categoryService;
  @Autowired
  UserService userService;

  @RequestMapping(value = "/project/{id}")
  @ResponseBody
  public ModelAndView showProject(@PathVariable("id") int id){
    Project project = projectService.get(id);
    ModelAndView vm = new ModelAndView("project");

    vm.addObject("project", project);
    vm.addObject("category", project.getCategory());

    vm.addObject("comments", projectService.getProjectComments(project));
    vm.addObject("blogs", projectService.getProjectBlogPosts(project));
    vm.addObject("video", projectService.getProjectVideo(project));

    return vm;
  }

  @RequestMapping(value = "/project/add", method = RequestMethod.POST)
  @ResponseBody
  public ModelAndView addProject(
      @RequestParam("category_id") String strCategoryId,
      @RequestParam("project_description") String projectDescription,
      @RequestParam("project_name") String projectName,
      @RequestParam("user_id") int user_id,
      @RequestParam("video_url") String video){

    String videoID = video.split("=")[1];

    Integer categoryId = Integer.parseInt(strCategoryId);
    Category category = categoryService.get(categoryId);
    Project project = new Project();
    project.setCategory(category);
    project.setDescription(projectDescription);
    project.setName(projectName);
    project.setVideo(videoID);
    project.setUser(userService.get(user_id));
    project = projectService.add(project);
    ModelAndView vm = new ModelAndView("redirect:/project/" + project.getId());
    return vm;
  }




}