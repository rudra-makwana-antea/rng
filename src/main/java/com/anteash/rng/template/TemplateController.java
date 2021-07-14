package com.anteash.rng.template;

import com.anteash.rng.helper.TemplatePreviewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class TemplateController {

    public static final String TEMPLATE_MANAGEMENT = "Template Management";
    public static final Logger logger = LoggerFactory.getLogger(TemplateController.class);
    public static final String TEMPLATE_CREATION = "Template Creation";
    public static final String TEMPLATE_LIST = "Template List";
    @Autowired
    TemplateService templateService;
    @Autowired
    TemplatePreviewer templatePreviewer;
    @Value("${page.template}")
    private String TEMPLATE_MANAGEMENT_VIEW_JSP;

    @GetMapping("/template")
    public ModelAndView templateManagementView(Model model) {
        model.addAttribute("templates", templateService.getAllTemplate());
        model.addAttribute("pageName", TEMPLATE_MANAGEMENT);
        model.addAttribute("pagePartName", TEMPLATE_LIST);
        return new ModelAndView(TEMPLATE_MANAGEMENT_VIEW_JSP);
    }

    @GetMapping("/template/preview")
    public ModelAndView previewTemplate(@RequestParam(name = "templateName") String previewTemplateName, Model model) {
        model.addAttribute("pageName", TEMPLATE_MANAGEMENT);
        model.addAttribute("pagePartName", TEMPLATE_LIST);
        model.addAttribute("templates", templateService.getAllTemplate());
        model.addAttribute("previewTemplate", templatePreviewer.getPreview(previewTemplateName));
        return new ModelAndView("template/template_management_view.jsp");
    }

    @GetMapping("template/creation")
    public ModelAndView getTemplateCreationView(Model model) {
        model.addAttribute("pageName", TEMPLATE_MANAGEMENT);
        model.addAttribute("pagePartName", TEMPLATE_CREATION);
        return new ModelAndView(TEMPLATE_MANAGEMENT_VIEW_JSP, "template", new Template());
    }

    @PostMapping("/template")
    public ModelAndView saveTemplate(@Valid @ModelAttribute(name = "template") Template template, Model model) {
        templateService.updateTemplate(template);
        return templateManagementView(model);
    }

    @GetMapping("/template/delete")
    public ModelAndView deleteTemplate(@RequestParam String templateName, Model model) {
        model.addAttribute("ACTION_MESSAGE", templateService.deleteTemplate(templateName));
        return templateManagementView(model);
    }

    @GetMapping("/template/edit")
    public ModelAndView editTemplate(@RequestParam(name = "templateName") String templateName, Model model) {
        Template template = templateService.getTempalteByName(templateName);
        model.addAttribute("pageName", TEMPLATE_MANAGEMENT);
        model.addAttribute("pagePartName", TEMPLATE_CREATION);
        return new ModelAndView(TEMPLATE_MANAGEMENT_VIEW_JSP, "template", template);
    }
}