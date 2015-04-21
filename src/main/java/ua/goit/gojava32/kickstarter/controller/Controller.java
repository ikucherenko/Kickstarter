package ua.goit.gojava32.kickstarter.controller;

import java.io.IOException;

import ua.goit.gojava32.kickstarter.model.Category;
import ua.goit.gojava32.kickstarter.model.Project;
import ua.goit.gojava32.kickstarter.service.CategoryService;
import ua.goit.gojava32.kickstarter.service.CategoryServiceImpl;
import ua.goit.gojava32.kickstarter.service.ProjectService;
import ua.goit.gojava32.kickstarter.service.ProjectServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller {

  private HttpServletRequest request;
  private HttpServletResponse response;
  private String[] uriSegments;
  
  private CategoryService categoryService = new CategoryServiceImpl();
  private ProjectService projectService = new ProjectServiceImpl();

  public Controller(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
    uriSegments = request.getRequestURI().split("/");
  }

  public void handleRequest() throws ServletException, IOException {

    if ("addCategory".equals(uriSegments[uriSegments.length-1])) {
      Category category = new Category(5, request.getParameter("category_name"));
      category.setDescription(request.getParameter("category_description"));
      categoryService.add(category);
      response.sendRedirect("/categories");
    }
    else if ("addProject".equals(uriSegments[uriSegments.length-1])) {
      Project project = new Project(5, request.getParameter("project_name"));
      project.setDescription(request.getParameter("project_description"));
      project.setCategory(categoryService.get(request.getParameter("category_name")));
      projectService.add(project);
      response.sendRedirect("/categories/" + request.getParameter("category_name"));
    }
    else if ("addProjectComment".equals(uriSegments[uriSegments.length-1])) {
      Project project = projectService.get(request.getParameter("project"));
      project.addComment(request.getParameter("comment"));
      projectService.update(project);
      response.sendRedirect("/categories/" + request.getParameter("category") + "/" + request.getParameter("project"));
    }
    else {
      String jspUrl = "/jsp/";
      jspUrl += getJspName();

      RequestDispatcher dispatcher = request.getRequestDispatcher(jspUrl);
      dispatcher.forward(request, response);
    }
  }

  private String getJspName() {
    String jspName = "";

    if (uriSegments[1].equals("categories") && uriSegments.length == 2) {
      jspName = "categories.jsp";
      request.setAttribute("categories", categoryService.findAll());
    } else if (uriSegments[1].equals("categories") && uriSegments.length > 2) {
      jspName = processCategories();
    }
    return jspName;
  }

  private String processCategories() {
    String jspName = "";
    String categoryName = uriSegments[2];
    request.setAttribute("category_name", categoryName);

    if (uriSegments.length == 3) {
      jspName = "category.jsp";
      request.setAttribute("projects", categoryService.findAllProjects(categoryService.get(categoryName)));
    } else {
      jspName = processProjects();
    }
    return jspName;
  }

  private String processProjects() {
    String jspName = "";
    if (uriSegments.length == 4) {
      request.setAttribute("project", projectService.get(uriSegments[3].trim()));
      jspName = "project.jsp";
    }
    return jspName;
  }
}
