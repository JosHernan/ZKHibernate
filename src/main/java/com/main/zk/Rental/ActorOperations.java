package com.main.zk.Rental;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

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

import com.entidades.actor;
import com.hibernate.dao.ActorManager;
import com.hibernate.impl.ActorDaoImpl;




public class ActorOperations {
	@Wire("#actor")
	private Window win;
	private actor selectedRecord;
	private String recordMode;
	public Window getWin() {
		return win;
	}
	public void setWin(Window win) {
		this.win = win;
	}
	public actor getSelectedRecord() {
		return selectedRecord;
	}
	public void setSelectedRecord(actor selectedRecord) {
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
			@ExecutionArgParam("selectedRecord") actor getactor,
			@ExecutionArgParam("recordMode") String recordMode) throws CloneNotSupportedException {
		Selectors.wireComponents(view, this, false);
		setRecordMode(recordMode);
		
	
		if (recordMode.equals("NEW")) {
			this.selectedRecord = new actor();

		}
		if (recordMode.equals("EDIT")) {
			this.selectedRecord = getactor;
			
			
			
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
	
		
		//String DataSource=MyConfigListener.getValor();
		try {
			ActorDaoImpl obj=new ActorDaoImpl();
			
			try {
			
				
				obj.setup();

			} catch (Exception err) {
				System.out.println("No se establecio conexión con la Base de Datos."+err);
				obj=null;	
				return;
				// TODO: handle exception
			}
			 
		
			 if(selectedRecord.getFirst_name().equals("")){
				 Messagebox.show("Campo Obligatorio:["+"First_Name"+"]", "INFORMACIÓN", Messagebox.OK,
							Messagebox.INFORMATION);
				
					return;
			 }
			 
			
			 if(selectedRecord.getLast_name().equals("")){
				 Messagebox.show("Campo Obligatorio:["+"Last Name"+"]", "INFORMACIÓN", Messagebox.OK,
							Messagebox.INFORMATION);
				
					return;
			 }
			 ActorManager dao=new ActorDaoImpl();
			 dao.insertActor(selectedRecord);
			
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
	
	
	@Command
	public void updateThis() {
		update();
	}
	@Command
	public void update() {
		Map<String, Object> args = new HashMap<String, Object>();
		try {
			ActorDaoImpl obj=new ActorDaoImpl();
			
			try {
			
				
				obj.setup();

			} catch (Exception err) {
				System.out.println("No se establecio conexión con la Base de Datos."+err);
				obj=null;	
				return;
				// TODO: handle exception
			}
			 
		
			 if(selectedRecord.getFirst_name().equals("")){
				 Messagebox.show("Campo Obligatorio:["+"First_Name"+"]", "INFORMACIÓN", Messagebox.OK,
							Messagebox.INFORMATION);
				
					return;
			 }
			 
			
			 if(selectedRecord.getLast_name().equals("")){
				 Messagebox.show("Campo Obligatorio:["+"Last Name"+"]", "INFORMACIÓN", Messagebox.OK,
							Messagebox.INFORMATION);
				
					return;
			 }
			 ActorManager dao=new ActorDaoImpl();
			 dao.updateActor(selectedRecord);
			
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
