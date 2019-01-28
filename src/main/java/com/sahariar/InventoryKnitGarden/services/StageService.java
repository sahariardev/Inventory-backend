package com.sahariar.InventoryKnitGarden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.sahariar.InventoryKnitGarden.models.Stage;
import com.sahariar.InventoryKnitGarden.repositories.StageRepository;
import com.sahariar.InventoryKnitGarden.requests.StageRequest;

@Service
public class StageService {

	@Autowired
	StageRepository stageRepository;
	
	public Long createNewStage(StageRequest request)
	{
		Stage stage=new Stage();
		stage.setName(request.getName());
		stage.setDescription(request.getDescription());
		try
		{
			stageRepository.save(stage);
			return stage.getId();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1L;
		}
		
	}
	public List<Stage> getAllStage()
	{
		return stageRepository.findAll();
	}
}
