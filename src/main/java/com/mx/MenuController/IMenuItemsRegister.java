package com.mx.MenuController;

import org.zkoss.zk.ui.event.EventListener;


public interface IMenuItemsRegister {
	/**
     * @param cssClass
     *            can be null
     * @return a key to access the new menu item
     */
    public Object addMenuItem(String name, String cssClass, EventListener eventListener);

    public void activateMenuItem(Object key);

    public void renameMenuItem(Object key, String name, String cssClass);

    public void toggleVisibilityTo(Object key, boolean visible);

	void goToCreateForm();
}
