package com.sahariar.InventoryKnitGarden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.Client;
import com.sahariar.InventoryKnitGarden.models.Project;
import com.sahariar.InventoryKnitGarden.repositories.ClientRepository;
import com.sahariar.InventoryKnitGarden.repositories.ProjectRepository;
import com.sahariar.InventoryKnitGarden.requests.ProjectRequest;

@Service
public class ProjectService {

	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	
	public List<Project> getProjectByClientId(Long id)
	{
	   	return projectRepository.findByClientId(id);
	}
	
	public List<Project> getProjectForCurrentUser(Long id)
	{
	   	return projectRepository.findByClientAssignedId(id);
	}
	
	public Long createNewProject(ProjectRequest request)
	{
		Project project=new Project();
		Client client=clientRepository.getOne(request.getClient_id());
		project.setClient(client);
		project.setName(request.getName());
		project.setDescription(request.getDescription());
		try {
			
			projectRepository.save(project);
			return project.getId();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1L;
		}
	}
}
