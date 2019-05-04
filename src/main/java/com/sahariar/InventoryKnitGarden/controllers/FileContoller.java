package com.sahariar.InventoryKnitGarden.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sahariar.InventoryKnitGarden.helper.FileUploadHelper;
import com.sahariar.InventoryKnitGarden.services.InventoryItemService;
@CrossOrigin
@RestController
@RequestMapping("/api/file")
public class FileContoller {
	
	@Value("${inventory.app.uploadfolder}")
    private String uploadpath;
	
	@Autowired
	FileUploadHelper helper;
	
	@Autowired
	InventoryItemService inventoryItemService;
	
	@PostMapping("/upload/")
    public ResponseEntity<?> uploadFileMultis(@RequestParam("itemid") long id, @RequestParam("files") MultipartFile[] uploadfiles)
	    {
           String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename()).filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

           	if (StringUtils.isEmpty(uploadedFileName)) {
           		return new ResponseEntity("please select a file!", HttpStatus.OK);
            }

        try {
 
        	helper.saveUploadedFiles(Arrays.asList(uploadfiles),uploadpath);

        } catch (IOException e) {
            
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
         
        //now save the upload file name to the corresponding database
        boolean status= inventoryItemService.saveItemIMage(id, uploadedFileName);
        if(status == false)
        {
        	return new ResponseEntity<>("item does not exist",	HttpStatus.BAD_REQUEST);
        }
        
        
        return new ResponseEntity("Successfully uploaded"+ uploadedFileName, HttpStatus.OK);

    }
	@GetMapping(value = "/getimage/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
			public @ResponseBody byte[] getImageWithMediaType(@PathVariable ("imagename") String imagename ) throws IOException {
			   
		        
		        File initialFile = new File(uploadpath+"imagename");
	            InputStream in= new FileInputStream(initialFile);  
			    return helper.getByteArrayFromInputStream(in);
			    
	   }
	
	

}
