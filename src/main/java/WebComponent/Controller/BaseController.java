package WebComponent.Controller;

import Services.SpittrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import javax.servlet.http.Part;
import java.io.IOException;
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
}
