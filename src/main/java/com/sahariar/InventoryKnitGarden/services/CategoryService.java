package com.sahariar.InventoryKnitGarden.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.BaseEntity;
import com.sahariar.InventoryKnitGarden.models.Category;
import com.sahariar.InventoryKnitGarden.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	
	public List<Category> getAllCategories()
	{
	   return categoryRepository.findAll();
	}
	public long createNewCategory(String name,String username)
	{
		Category category=new Category();
		category.setName(name);
		category.setCratedBy(username);
		category.setUpdatedBy(username);
		
		try {
			categoryRepository.save(category);
			return category.getId();
		}
		catch(Exception e)
		{
			//As we can not send null value so we are sending unreachable long number 
			e.printStackTrace();
			return -1L;
		}
	}
	public BaseEntity fillExtraInformation(HashMap<String,String>map,BaseEntity baseEntity)
	{
		for(String s:map.keySet())
		{
			if(s.equals("code"))
			{
				baseEntity.setCode(map.get(s));
			}
			else if(s.equals("extra"))
			{
				baseEntity.setExtra(map.get(s));
			}
			else if(s.equals("description"))
			{
				baseEntity.setDescription(map.get(s));
			}
		}
		return baseEntity;
	}
	public long createNewCategoryWithExtraInformation(String name,String username,HashMap<String,String>map)
	{
		Category category=new Category();
		category=(Category)fillExtraInformation(map,category);
		category.setName(name);
		category.setCratedBy(username);
		category.setUpdatedBy(username);
		System.out.println(category);
		
		
		try {
			categoryRepository.save(category);
			return category.getId();
		}
		catch(Exception e)
		{
			//As we can not send null value so we are sending unreachable long number 
			e.printStackTrace();
			return -1L;
		}
	}
	public boolean delete(long id)
	{
		Category category=categoryRepository.getOne(id);
		category.setCode("deleted");
		try {
			categoryRepository.save(category);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
}
