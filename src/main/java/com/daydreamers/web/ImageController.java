package com.daydreamers.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.daydreamers.model.Image;
import com.daydreamers.model.Tag;
import com.daydreamers.services.ImageService;
import com.daydreamers.services.TagService;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	@Inject
	ImageService imageService;

	@Inject
	TagService imageTagService;
	
	@Inject
	ServletContext servletContext;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllImages(Model model) {
		model.addAttribute("images", imageService.getAllImages());
		model.addAttribute("tag", new Tag());
		return "image/list";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Image createImage(@Valid Image newImage, BindingResult result, @RequestParam(value = "image", required = false) MultipartFile image) throws BindException {
		
		if (result.hasErrors()) {
			throw new RuntimeException();
		}
		
		if (!image.isEmpty()) {
			try {
				newImage.setContent(image.getBytes());				
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
		
		return imageService.makePersistent(newImage);
	}
	
	@RequestMapping(value="{imageId}", method = RequestMethod.GET)
	public @ResponseBody Image readImage(@PathVariable String imageId) {
		return imageService.readImage(Long.valueOf(imageId));
	}
	
	@RequestMapping(value="{imageId}/content", method = RequestMethod.GET)  
	public void readImageContent(@PathVariable String imageId, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg");
		Image image = readImage(imageId);
		
		if (image != null && image.getContent() != null) {
			InputStream in = new ByteArrayInputStream(image.getContent());
			IOUtils.copy(in, response.getOutputStream());	
		} 		
	}

	@RequestMapping(value="{imageId}/thumbnail", method = RequestMethod.GET)  
	public void readImageThumbnail(@PathVariable String imageId, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg");
		Image image = readImage(imageId);
		
		if (image != null && image.getThumbnail() != null) {
			InputStream in = new ByteArrayInputStream(image.getThumbnail());
			IOUtils.copy(in, response.getOutputStream());	
		} 		
	}
	
	@RequestMapping(value="{imageId}/tag", method = RequestMethod.POST)
	public String addNewTagForImage(@PathVariable String imageId, @Valid Tag tag, BindingResult result) {
		
		if (result.hasErrors()) {
			throw new RuntimeException();
		}				
		
		imageTagService.addTagForImage(Long.valueOf(imageId), tag);
		return "redirect:/image/";
	}
	
	@RequestMapping(value="{imageId}/tag/{tagValue}", method = RequestMethod.DELETE)
	public String removeTagFromImage(@PathVariable String imageId, @PathVariable String tagValue) {
		imageTagService.removeTagForImage(Long.valueOf(imageId), tagValue);
		return "redirect:/image/";
	}
	
	@RequestMapping(value="{imageId}", method = RequestMethod.DELETE)
	public String removeImage(@PathVariable String imageId) {
		imageService.deleteImage(Long.valueOf(imageId));
		return "redirect:/image/"; 
	}
	
}
