package com.daydreamers.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daydreamers.model.Tag;
import com.daydreamers.services.TagService;

@Controller
@RequestMapping("/tag")
public class TagController {

	@Inject
	private TagService tagService;

	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllTags(Model model) {
		List<Tag> tags = tagService.getAllTags();
		model.addAttribute("tags", tags);		
		return "tag/list";
	}
	
	@RequestMapping(value="{tagId}", method = RequestMethod.DELETE)
	public String removeTag(@PathVariable String tagId) {
		tagService.removeTag(Long.valueOf(tagId));
		return "redirect:/tag";
	}
	
	@RequestMapping(value="{tagId}/images", method=RequestMethod.DELETE)
	public String removeAllImages(@PathVariable String tagId) {
		tagService.removeAllImages(Long.valueOf(tagId));
		return "redirect:/tag";
	}
	
}
