package ua.goit.gojava32.kickstarter.controller;

import ua.goit.gojava32.kickstarter.model.Category;
import ua.goit.gojava32.kickstarter.model.Project;
import ua.goit.gojava32.kickstarter.servlet.Request;
import ua.goit.gojava32.kickstarter.view.ViewModel;

public class ControllerCommon implements Controller{
  @Override
  public ViewModel process(Request request) {
    
    String jspUrl = "/jsp/" + getJspName();

    ViewModel vm = new ViewModel("jspUrl", "forward", null);

    return vm;
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
    Category category = categoryService.get(categoryName);
    request.getSession().setAttribute("category", category);

    if (uriSegments.length == 3) {
      jspName = "category.jsp";
      request.setAttribute("projects", categoryService.findAllProjects(category));
    } else {
      jspName = processProjects();
    }
    return jspName;
  }

  private String processProjects() {
    String jspName = "";
    if (uriSegments.length == 4) {
      Project project = projectService.get(uriSegments[3].trim());
      request.getSession().setAttribute("project", project);
      request.getSession().setAttribute("comments", projectService.getComments(project));
      request.getSession().setAttribute("blogs", projectService.getBlogs(project));
      jspName = "project.jsp";
    }
    return jspName;
  }
}
