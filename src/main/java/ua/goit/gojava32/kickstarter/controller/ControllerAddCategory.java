package ua.goit.gojava32.kickstarter.controller;

import ua.goit.gojava32.kickstarter.factory.ServiceModel;
import ua.goit.gojava32.kickstarter.model.Category;
import ua.goit.gojava32.kickstarter.service.CategoryService;
import ua.goit.gojava32.kickstarter.view.ViewModel;
import javax.servlet.http.HttpServletRequest;

public class ControllerAddCategory implements Controller{

  @Override
  public ViewModel process(HttpServletRequest request) {

    CategoryService categoryService = ServiceModel.getCategoryService();

    String categoryName = request.getParameter("category_name");
    String description  = request.getParameter("category_description");
    Category category = categoryService.add(categoryName, description);

    ViewModel vm = new ViewModel("/categories", "sendRedirect", null);
    return vm;
  }
}
