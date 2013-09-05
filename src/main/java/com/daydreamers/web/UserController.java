package com.daydreamers.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

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
import org.springframework.web.multipart.MultipartFile;

import com.daydreamers.model.Image;
import com.daydreamers.model.User;
import com.daydreamers.services.ImageService;
import com.daydreamers.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final String NEW_USER_PARAM = "new";
		
	@Inject
	private UserService userService;
	
	@Inject
	private ImageService imageService;

	@Inject
	private ServletContext servletContext;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "user/list";
	}

	@RequestMapping(value = "{login}", method = RequestMethod.GET)
	public String getUser(@PathVariable String login, Model model) {

		User user = null;

		if (NEW_USER_PARAM.equals(login)) {
			user = new User();
			model.addAttribute("user", user);			
			return "user/create";
		} else {
			user = userService.getUserForUpdate(login);
			model.addAttribute("user", user);
			model.addAttribute("image", new Image());
			return "user/edit";
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String createUser(@Valid User user, BindingResult result) throws BindException {
		
		if (result.hasErrors()) {
			return "user/create";
		}
		
		userService.addUser(user);

		return "redirect:/user";
	}
	
	@RequestMapping(value="{login}", method = RequestMethod.POST)
	public String updateUser(@PathVariable String login, @Valid User user, BindingResult result, Model model) throws BindException {
		
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("image", new Image());
			return "user/edit";
		}		
		userService.updateUser(login, user);
		
		return "redirect:/user";
	}
	
	
	@RequestMapping(value = "{login}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable String login) {
		userService.deleteUser(login);
		return "redirect:/user";
	}

	
	@RequestMapping(value="{login}/image", method = RequestMethod.GET)
	public void getUserImage(@PathVariable String login, HttpServletResponse response) throws IOException {
		
		response.setContentType("image/jpeg");
		User user = userService.getUser(login);
		InputStream in = null;
		if (user != null && user.getFace() != null) {			
		    in = new ByteArrayInputStream(user.getFace()); 
		} else {
			in = servletContext.getResourceAsStream("WEB-INF/images/default_user.jpeg");
		}
		IOUtils.copy(in, response.getOutputStream());	
	}
	
	@RequestMapping(value="{login}/images", method = RequestMethod.POST)
	public String createImageForUser(@PathVariable String login, @Valid Image newImage, BindingResult result, @RequestParam(value = "image", required = false) MultipartFile image) throws BindException {

		if (result.hasErrors()) {
			return "user/edit";
		}
		
		if (!image.isEmpty()) {
			try {
				newImage.setContent(image.getBytes());		
				newImage.setDateAdded(new Date());
				userService.createImageForUser(login, newImage);
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
		
		return "redirect:/user/" + login;
	}
	
	@RequestMapping(value="{login}/images/{imageId}", method = RequestMethod.DELETE)
	public String deleteImageForUser(@PathVariable String login, @PathVariable String imageId) {		
		imageService.deleteImage(Long.valueOf(imageId));
		return "redirect:/user/" + login;
	}

}
