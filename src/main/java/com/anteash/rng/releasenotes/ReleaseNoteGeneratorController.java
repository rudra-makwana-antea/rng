package com.anteash.rng.releasenotes;

import com.anteash.rng.helper.ReleaseNoteHelper;
import com.anteash.rng.template.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReleaseNoteGeneratorController {

    private static final Logger logger = LoggerFactory.getLogger(ReleaseNoteGeneratorController.class);
    @Autowired
    TemplateService templateService;
    @Autowired
    ReleaseNoteHelper releaseNoteHelper;
    @Value("${page.visual}")
    private String VISUALIZATION_JSP;
    @Value("${page.releasenote}")
    private String RELEASE_NOTE_GENERATOR_JSP;

    @GetMapping("/releasenotes")
    public ModelAndView getReleaseNotesGeneratorPage(Model model) {
        model.addAttribute("pageName", "Release Note");
        model.addAttribute("templates", templateService.getAllTemplate());
        return new ModelAndView(RELEASE_NOTE_GENERATOR_JSP);
    }

    @GetMapping("/releasenotes/create")
    @ResponseBody
    public ModelAndView createReleaseNote(@RequestParam("project") String project,
            @RequestParam("version") String version,
            @RequestParam("template") String template,
            Model model) {
        String html = releaseNoteHelper.createReleaseNote(project, version, template);
        model.addAttribute("HTML_CODE", html);
        return new ModelAndView(VISUALIZATION_JSP);
    }
}
