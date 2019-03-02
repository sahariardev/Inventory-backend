package com.sahariar.InventoryKnitGarden.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahariar.InventoryKnitGarden.models.Store;


public interface StoreRepository extends JpaRepository<Store,Long> {
	
	public List<Store> findStoreByStageNameAndLocationNameAndItemName(String stageName,String LocationName,String itemName);
	public List<Store> findStoreByStageName(String stageName);
	public List<Store> findStoreByLocationName(String locationName);
	public List<Store> findStoreByItemNameAndItemColorCode(String itemName,String color_code);
	public List<Store> findStoreByItemName(String itemName);
	public List<Store> findStoreByStageNameAndLocationName(String stageName, String locationName);
	public List<Store> findStoreByLocationNameAndItemName(String locatioName, String itemName);
	public List<Store>findStoreByStageNameAndItemName(String stageName, String itemName);
	
	
	

}
