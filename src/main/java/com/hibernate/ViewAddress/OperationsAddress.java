package com.hibernate.ViewAddress;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.entidades.address;
import com.hibernate.dao.AddressManager;
import com.hibernate.impl.AddressDaoImpl;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;


public class OperationsAddress {
	@Wire("#address")
	private Window win;
	private address selectedRecord;
	private String recordMode;
	
	private Double altitud;
	private Double longitud;
	
	GeometryFactory geometryFactory = new GeometryFactory();
	
	public Double getAltitud() {
		return altitud;
	}

	public void setAltitud(Double altitud) {
		this.altitud = altitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Window getWin() {
		return win;
	}

	public void setWin(Window win) {
		this.win = win;
	}

	public address getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(address selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("selectedRecord") address getaddress,
			@ExecutionArgParam("recordMode") String recordMode) throws CloneNotSupportedException {
		Selectors.wireComponents(view, this, false);
		setRecordMode(recordMode);
		
	
		if (recordMode.equals("NEW")) {
			this.selectedRecord = new address();

		}
		if (recordMode.equals("EDIT")) {
			this.selectedRecord = getaddress;
			
			
			
		}
		
	}
	
	@Command
	public void closeThis() {
		win.detach();	
	}
	
	
	@Command
	public void saveThis() {
		save();
	}
	@Command
	public void save() {
		Map<String, Object> args = new HashMap<String, Object>();

		try {
			AddressDaoImpl obj=new AddressDaoImpl();
			
			try {
			
				
				obj.setup();

			} catch (Exception err) {
				System.out.println("No se establecio conexión con la Base de Datos."+err);
				obj=null;	
				return;
				// TODO: handle exception
			}
			 
			
			 if(selectedRecord.getAddress().equals("")){
				 Messagebox.show("Campo Obligatorio:["+"Address"+"]", "INFORMACIÓN", Messagebox.OK,
							Messagebox.INFORMATION);
				
					return;
			 }
			 
			
			 if(selectedRecord.getAddress2().equals("")){
				 Messagebox.show("Campo Obligatorio:["+"Addres 2"+"]", "INFORMACIÓN", Messagebox.OK,
							Messagebox.INFORMATION);
				
					return;
			 }
			 if(selectedRecord.getDistrict().equals("")){
				 Messagebox.show("Campo Obligatorio:["+"District"+"]", "INFORMACIÓN", Messagebox.OK,
							Messagebox.INFORMATION);
				
					return;
			 }
			 if(selectedRecord.getCity_id().equals("")){
				 Messagebox.show("Campo Obligatorio:["+"City"+"]", "INFORMACIÓN", Messagebox.OK,
							Messagebox.INFORMATION);
				
					return;
			 }
			 if(selectedRecord.getPostal_code().equals("")){
				 Messagebox.show("Campo Obligatorio:["+"Postal Code"+"]", "INFORMACIÓN", Messagebox.OK,
							Messagebox.INFORMATION);
				
					return;
			 }
			 if(selectedRecord.getPhone().equals("")){
				 Messagebox.show("Campo Obligatorio:["+"Phone"+"]", "INFORMACIÓN", Messagebox.OK,
							Messagebox.INFORMATION);
				
					return;
			 }
			 
			 
			 selectedRecord.setLocation(geometryFactory.createPoint(new Coordinate(altitud,longitud)));
			 if(selectedRecord.getLocation().equals("")){
				 Messagebox.show("Campo Obligatorio:["+"Location"+"]", "INFORMACIÓN", Messagebox.OK,
							Messagebox.INFORMATION);
				
					return;
			 }
			 AddressManager dao=new AddressDaoImpl();
			 dao.insertAddress(selectedRecord);
			
			    args.put("selectedRecord", this.selectedRecord);
			   
				args.put("recordMode", this.recordMode);
				Clients.showNotification("Insertado Correctamente",
			            Clients.NOTIFICATION_TYPE_INFO, null, "top_center", 4100);
				BindUtils.postGlobalCommand(null, null, "refreshList", args);
				win.detach();
		} 
		catch (Exception e) {
			Messagebox.show("Exception:" + e.getMessage(), "ERROR", Messagebox.OK, Messagebox.ERROR);
			
			args = null;
		
			
			
		} finally {
			args = null;
		
			
			
		}
	}
}
