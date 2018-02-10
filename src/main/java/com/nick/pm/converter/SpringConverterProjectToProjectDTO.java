package com.nick.pm.converter;

import com.nick.pm.DTO.ProjectDTO;
import com.nick.pm.entity.Project;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class SpringConverterProjectToProjectDTO implements Converter<Project, ProjectDTO> {

    @Override
    public ProjectDTO convert(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setTitle(project.getTitle());
        projectDTO.setUserId(project.getUserId().getId());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setProjectDate(project.getProjectDate());
        return projectDTO;
    }
}
