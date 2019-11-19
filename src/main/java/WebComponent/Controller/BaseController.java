package WebComponent.Controller;

import ORM.POJO.Spittr;
import Services.SpittrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class BaseController {
	@RequestMapping("/")
	public String helloView() {
		return "spittrviews/index";
	}

	@Autowired
	SpittrService spittrService;

	@RequestMapping("/latest")
	public String getLatestSpittrs(@RequestParam(value = "count", defaultValue = "50") int count, Model model) {
		model.addAttribute("spittrList", spittrService.selectLatest(count));
		return "spittrviews/latest";
	}

	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public String testFileUpload(@RequestPart("file") Part file) throws IOException {
		file.write(UUID.randomUUID() + "-" + file.getSubmittedFileName());
		return "spittrviews/fileupload";
	}

	@RequestMapping(value = "/fileupload", method = RequestMethod.GET)
	public String testFileUpload() {
		return "spittrviews/fileupload";
	}

	@RequestMapping(value = "/spittr/{id}", method = RequestMethod.GET)
	public String getSpittr(@PathVariable("id") int id, Model model) {
		model.addAttribute("spittr", spittrService.selectOne(id));
		model.addAttribute("view", true);
		return "spittrviews/spittr";
	}

	@RequestMapping(value = "/spittr", method = RequestMethod.GET)
	public String getSpittr(Model model) {
		model.addAttribute(new Spittr());
		return "spittrviews/spittr";
	}

	@RequestMapping(value = "/spittr", method = RequestMethod.POST)
	public String addSpittr(@RequestPart("file") Part file, @Valid Spittr spittr, Errors errors, Model model) {
		spittr.setCreatetime(new Date());
		if (errors.hasErrors()) {
			model.addAttribute("error", "输入有误，请检查并重试");
			return "spittrviews/spittr";
		}
		spittrService.saveOne(spittr);
		model.addAttribute("info", "提交成功");
		return "redirect:latest";
	}
}
