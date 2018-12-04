package com.hibernate.ViewAddress;

import java.util.ArrayList;
import java.util.HashMap;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;

import com.entidades.actor;
import com.entidades.address;
import com.hibernate.dao.AddressManager;
import com.hibernate.impl.AddressDaoImpl;



public class ViewAddress extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 1L;
	private address SelectedItem;
	private ArrayList<address>  showAddress=new ArrayList<address>();
  //  private ArrayList<gender> listGender=new ArrayList<gender>();
	private Integer selectedItemIndex;
	public address getSelectedItem() {
		return SelectedItem;
	}
	public void setSelectedItem(address selectedItem) {
		SelectedItem = selectedItem;
	}
	public ArrayList<address> getShowAddress() {
		
		return showAddress;
	}
	public void setShowAddress(ArrayList<address> showAddress) {
		this.showAddress = showAddress;
	}
	public Integer getSelectedItemIndex() {
		return selectedItemIndex;
	}
	public void setSelectedItemIndex(Integer selectedItemIndex) {
		this.selectedItemIndex = selectedItemIndex;
	}
	

	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW)Component view){
	
		Selectors.wireComponents(view, this, false);
		AddressDaoImpl obj=new AddressDaoImpl();
		
		
		 try {
			
			 if(obj.setup()!=0) {
				 AddressManager dao=new AddressDaoImpl();
				 showAddress=dao.listAddress();
			}else {
				
				Messagebox.show("No se establecio conexion"
						+ "Revise la configuracion:", "Exception Hibernate", Messagebox.OK,
						Messagebox.ERROR);
				
				return;
			}
			 
			
		
			 
		} catch (Exception e) {
			System.out.println("Exception Error:" + e.getMessage());
			showAddress=null;
			
		}finally {
			obj=null;
			
		
		}
	
	}	
	
	@GlobalCommand("refreshList")
	@NotifyChange("showAddress")
	public void refreshList(@BindingParam("selectedRecord") address addr,
			@BindingParam("recordMode") String recordMode) {
		if (recordMode.equals("NEW")) {
			showAddress.add(addr);
		}

		if (recordMode.equals("EDIT")) {
			showAddress.set(this.selectedItemIndex, addr);
		}
	}
	
	
	@Command
	public void onAddNew() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedRecord", null);
		map.put("recordMode", "NEW");
		Executions.createComponents("/address/dialog-address.zul",
				null, map);
	}
	
}
