package com.anteash.rng.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateService {

    private static final Logger logger = LoggerFactory.getLogger(TemplateService.class);
    @Autowired
    private TemplateRepository templateRepository;

    public List<Template> getAllTemplate(){
        List<Template> templates = new ArrayList<>();
        templateRepository.findAll().forEach(template -> templates.add(template));
        return templates;
    }

    public Template getTempalteByName(String name) {
        return templateRepository.findByName(name);
    }

    public void updateTemplate(Template template) {
        templateRepository.save(template);
    }

    public void saveTemplate(Template template) {
        templateRepository.save(template);
    }

    @Transactional
    public String deleteTemplate(String templateName) {
        long id = templateRepository.deleteByName(templateName);
        if(id == 0)
            return "No template exists or some error occurred.";
        return "Template "+templateName+" is deleted.";
    }
}