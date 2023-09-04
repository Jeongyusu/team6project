package shop.mtcoding.project.jobopening;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobOpeningController {
    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @GetMapping("/compMyPageForm")
    public String jobOpeningList(Model model) {
        List<JobOpening> jobOpeningList = jobOpeningRepository.findAll();
        int totalJopOpeningList = jobOpeningList.size();
        model.addAttribute("totalJopOpeningList", totalJopOpeningList);
        model.addAttribute("jobOpeningList", jobOpeningList);
        return "comp/comp_info";
    }
}
