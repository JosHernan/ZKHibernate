package com.main.zk.Rental;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;

import com.entidades.actor;
import com.hibernate.dao.ActorManager;
import com.hibernate.impl.ActorDaoImpl;




public class MyViewModel extends GenericForwardComposer<Component> {

			private actor SelectedItem;
			private ArrayList<actor>  showActor=new ArrayList<actor>();
		  //  private ArrayList<gender> listGender=new ArrayList<gender>();
			private Integer selectedItemIndex;
			public actor getSelectedItem() {
				return SelectedItem;
			}
			public void setSelectedItem(actor selectedItem) {
				SelectedItem = selectedItem;
			}
			public ArrayList<actor> getShowActor() {
				
				return showActor;
			}
			public void setShowActor(ArrayList<actor> showActor) {
				this.showActor = showActor;
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
				ActorDaoImpl obj=new ActorDaoImpl();
				obj.setup();
				 try {
					
					 ActorManager dao=new ActorDaoImpl();
					 showActor=dao.listActor();
					
				
					 
				} catch (Exception e) {
					System.out.println("Exception Error:" + e.getMessage());
					showActor=null;
					
				}finally {
					obj=null;
					
				
				}
				
				 
				 
				
			}	
			
			
			@Command
			public void onAddNew() {

				final HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("selectedRecord", null);
				map.put("recordMode", "NEW");
				Executions.createComponents("/actor/dialog-actor.zul",
						null, map);
			}
			
			@GlobalCommand("refreshList")
			@NotifyChange("showActor")
			public void refreshList(@BindingParam("selectedRecord") actor actor,
					@BindingParam("recordMode") String recordMode) {
				if (recordMode.equals("NEW")) {
					showActor.add(actor);
				}

				if (recordMode.equals("EDIT")) {
					showActor.set(this.selectedItemIndex, actor);
				}
			}
			
			
			@Command
			public void onEdit(@BindingParam("actorRecord") actor actor) {
				final HashMap<String, Object> map = new HashMap<String, Object>();
				this.SelectedItem = actor;
				map.put("selectedRecord", actor);
				map.put("recordMode", "EDIT");
				setSelectedItemIndex(showActor.indexOf(SelectedItem));
				Executions.createComponents(
						"/actor/dialog-actoredit.zul", null, map);
			}
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Command
			public void onDelete(@BindingParam("actorRecord") final actor actor) {
				this.SelectedItem = actor;
				String str = null;
				try {
					str = "Desea eliminar el  actor \"" + actor.getFirst_name()
							+ "\"?";
					Messagebox.show(str, "Confirmar",
							Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
							new EventListener() {
								public void onEvent(Event event) throws Exception {
									if (((Integer) event.getData()).intValue() == Messagebox.CANCEL) {
										return;
									} else if (((Integer) event.getData()).intValue() == Messagebox.OK) {
										deleteApp(actor);
									}
								}
							});
					str = null;

				} catch (Exception e) {
					str = null;
				} finally {
					str = null;
				}

			}

			public void deleteApp(actor actor) {
				this.SelectedItem = actor;
			
				ActorDaoImpl obj=new ActorDaoImpl();
				try {
					try {
						
						obj.setup();

					} catch (Exception err) {
						System.out.println("No se establecio conexion con la Base de Datos.");
							obj=null;
						return;
						// TODO: handle exception
					}
					 if(this.SelectedItem.getActor_id()==0){
						 Messagebox.show("Campo Obligatorio", "INFORMACIONN", Messagebox.OK,
									Messagebox.INFORMATION);
						
							return;
						}
					 ActorManager dao=new ActorDaoImpl();
					 dao.deleteActor(this.SelectedItem);
					
					 showActor.remove(showActor.indexOf(SelectedItem));
					Messagebox.show("Registros eliminado correctamente",
							"INFORMACION", Messagebox.OK, Messagebox.INFORMATION);
					BindUtils
							.postNotifyChange(null, null, MyViewModel.this, "showActor");
					
				
				} catch (Exception e) {
					System.out.println("Exception-->"+e.getMessage());
					
				} finally {
					obj=null;
					
				}
			}
			
			
			
}
