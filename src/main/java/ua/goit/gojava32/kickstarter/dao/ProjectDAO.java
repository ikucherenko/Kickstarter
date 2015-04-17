package ua.goit.gojava32.kickstarter.dao;

import ua.goit.gojava32.kickstarter.model.Project;

public interface ProjectDAO {
  void add(Project project);
  void update(Project project);
  void delete(Project project);
  Project get(Integer id);
}