package com.anteash.rng.landingpage;

import com.anteash.rng.helper.FileDownloader;
import com.anteash.rng.helper.ReleaseNoteHelper;
import com.anteash.rng.helper.TemplatePreviewer;
import com.anteash.rng.issue.IssueService;
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

import java.util.List;

@Controller
public class IndexController {
    public static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    private static final String HOME = "Home";
    private static final String BASE_URL = "localhost:8080/visualize?";
    @Autowired
    IssueService issueService;
    @Autowired
    TemplateService templateService;
    @Autowired
    TemplatePreviewer templatePreviewer;
    @Autowired
    ReleaseNoteHelper releaseNoteHelper;
    @Autowired
    FileDownloader fileDownloader;
    @Value("${page.index}")
    private String INDEX_JSP;
    @Value("${page.visual}")
    private String VISUALIZATION_JSP;

    @GetMapping("/")
    public ModelAndView welcome(Model model) {
        List<String> releases = issueService.getReleases();
        model.addAttribute("pageName", HOME);
        model.addAttribute("releases", releases);
        model.addAttribute("templates", templateService.getAllTemplate());
        return new ModelAndView(INDEX_JSP);
    }

    @GetMapping("/visualize")
    public ModelAndView visualize(@RequestParam(name = "projectVersion") String projectVersion,
            @RequestParam(name = "selectedTemplate") String templateName,
            Model model) {
        model.addAttribute("HTML_CODE", templatePreviewer.getPreview(templateName, projectVersion));
        return new ModelAndView(VISUALIZATION_JSP);
    }

    @GetMapping("/refetch")
    @ResponseBody
    public String refetch(@RequestParam String projectVersion) {
        String[] projectAndVersion = projectVersion.split(" ");
        releaseNoteHelper.refetchIssues(projectAndVersion[0], projectAndVersion[1]);
        return "reloaded";
    }

    @GetMapping("/export")
    @ResponseBody
    public String getFile(@RequestParam(name = "projectVersion") String projectVersion,
            @RequestParam(name = "selectedTemplate") String templateName) {
        return fileDownloader.createHtmlCode(templateName, projectVersion);
    }
}
