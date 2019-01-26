package com.sahariar.InventoryKnitGarden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.Project;
import com.sahariar.InventoryKnitGarden.models.Style;
import com.sahariar.InventoryKnitGarden.repositories.ProjectRepository;
import com.sahariar.InventoryKnitGarden.repositories.StyleRepository;
import com.sahariar.InventoryKnitGarden.requests.StyleRequest;

@Service
public class StyleService {

	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	StyleRepository styleRepository;
	
	
	
	public List<Style> getAllStyleByProjectId(Long id)
	{
	  return styleRepository.findByProjectId(id);	
	}
	
	public Long createNewStyle(StyleRequest request)
	{
		
		Project project=projectRepository.getOne(request.getProject_id());
		
		Style style=new Style();
		
		style.setName(request.getName());
		style.setDescription(request.getDescription());
		style.setProject(project);
		
		try {
			
			styleRepository.save(style);
			return style.getId();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1L;
		}
		
	}
	
	
}
