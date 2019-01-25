package com.sahariar.InventoryKnitGarden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.Client;
import com.sahariar.InventoryKnitGarden.models.User;
import com.sahariar.InventoryKnitGarden.repositories.ClientRepository;
import com.sahariar.InventoryKnitGarden.repositories.UserRepository;
import com.sahariar.InventoryKnitGarden.requests.ClientRequest;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public List<Client> getAllClients()
	{
		return clientRepository.findAll();
	}
	public List<Client> getAllClientsByAssignedUserName(String username)
	{
		return clientRepository.findClientByAssignedUsername(username);
	}
	
	
	public Long createNewClient(ClientRequest request)
	{
	    Client client=new Client();
	    client.setName(request.getName());
	    client.setDescription(request.getDescription());
	    client.setContact(request.getContact());
	    client.setEmail(request.getEmail());
	    User user=userRepository.getOne(request.getAssigned_to());
	    client.setAssigned(user);
	    
	    try {
	    	clientRepository.save(client);
	    	return client.getId(); 
	    }
	    catch(Exception e)
	    {
	    	return -1L;
	    }
	    
	}
}
