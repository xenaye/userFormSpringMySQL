package com.xenaye.projects.userservice.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xenaye.projects.userservice.model.AppResponse;
import com.xenaye.projects.userservice.model.User;
import com.xenaye.projects.userservice.service.ApplicationService;
import com.xenaye.projects.userservice.service.FileStorageService;
import com.xenaye.projects.userservice.utils.AppConstants;

@RestController
public class UserRestController {

	@Autowired
	ApplicationService applicationService;
	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	FileStorageService fileStorageService;

	@RequestMapping(value = AppConstants.USER_URI, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AppResponse createEmployee(
			@RequestParam(value = AppConstants.USER_JSON_PARAM, required = true) String empJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) MultipartFile file)
			throws JsonParseException, JsonMappingException, IOException {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
				.path(fileName).toUriString();

		User user = objectMapper.readValue(empJson, User.class);
		user.setProfilePicPath(fileDownloadUri);
		applicationService.createUser(user);

		return new AppResponse(AppConstants.SUCCESS_CODE, AppConstants.SUCCESS_MSG);
	}

	@RequestMapping(value = AppConstants.USER_URI, method = RequestMethod.GET)
	public List<User> getAllEmployees() {
		return applicationService.getAllUsers();
	}

	@RequestMapping(value = AppConstants.DOWNLOAD_URI, method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (contentType == null) {
			contentType = AppConstants.DEFAULT_CONTENT_TYPE;
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						String.format(AppConstants.FILE_DOWNLOAD_HTTP_HEADER, resource.getFilename()))
				.body(resource);
	}
}