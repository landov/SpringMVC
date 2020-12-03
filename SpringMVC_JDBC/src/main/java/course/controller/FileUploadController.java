package course.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);

	/**
	 * Upload single file using Spring Controller
	 * 
	 */
	
	@RequestMapping(value = "/getImage/{imageId}")
	@ResponseBody
	public byte[] getImage(@PathVariable String imageId, HttpServletRequest request)  {
	    @SuppressWarnings("deprecation")
		//String rpath = request.getRealPath("/");
	    String rpath = System.getProperty("catalina.home") + File.separator + "tmpFiles" + File.separator + imageId; // whatever path you used for storing the file
	    System.out.println(rpath);
	    Path path = Paths.get(rpath);
	    byte[] data = null;
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return data;
	}
	
	@RequestMapping(value = "upload")
	public String startPage(Model model) {
		String href = new String();
		
		href = System.getProperty("catalina.home") + File.separator + "tmpFiles" + File.separator +"nana";
		model.addAttribute("picture", href);
		return "upload";
	}
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name
					+ " because the file was empty.";
		}
	}
}
