package com.hibernate.dao;
import java.util.ArrayList;
import java.util.List;

import com.entidades.actor;

public interface ActorManager {
	public ArrayList<actor>listActor();
	public void insertActor(actor act);
	public void updateActor(actor act);
	public void deleteActor(actor act);
}
