package ddvudo.Controller;

import ddvudo.ORM.POJO.Spittr;
import ddvudo.Service.Services.SpittrService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/spittr")
public class SpittrController {
	SpittrService spittrService;

	public SpittrController(SpittrService spittrService) {
		this.spittrService = spittrService;
	}

	@RequestMapping
	public String getLatestSpittrs(@RequestParam(value = "count", defaultValue = "50") int count, Model model) {
		model.addAttribute("spittrList", spittrService.selectLatest(count));
		return "spittrviews/latest";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getSpittr(@PathVariable("id") int id, Model model) {
		String json = "{\"content\":\"pkqlnYU7QdomwW20wXEd7O8ZRoL0Q7qwaL6Dx1D33WkHGHYQYjmKp1nblRU8NdrTw2LkayD5TLkkn1SJP4brUgGiifcfgEpZpRUN5x0kfr0eRDxt9KsSBA9PwnC4WhYPfK4WgEOcE1LmpmQersgVVXXoSFbSv5N8Tl8GIbM46QNKzWjKWiNoBC3HxQDbm5rJnMTEnJVPflZFJNz5fR5Mrj7Qs0T2XvRAjEgmDfgNM7IZnx7kaI6VAebEPCWw0c1\",\"createtime\":1420068210000,\"id\":0,\"userid\":0,\"username\":\"Nate Shaw\"}";
		model.addAttribute("spittr", spittrService.selectOne(id));
		model.addAttribute("view", true);
		return "spittrviews/spittrinfo";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newSpittr(Model model) {
		model.addAttribute(new Spittr());
		return "spittrviews/spittrinfo";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String addSpittr(@RequestPart("file") Part file, @Valid Spittr spittr, Errors errors, Model model) {
		spittr.setCreatetime(new Timestamp(new Date().getTime()));
		if (errors.hasErrors()) {
			model.addAttribute("error", "输入有误，请检查并重试");
			return "spittrviews/spittrinfo";
		}
		spittrService.saveOne(spittr);
		model.addAttribute("info", "提交成功");
		return "redirect:/spittr";
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
