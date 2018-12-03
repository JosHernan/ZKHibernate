package com.mx.Roles;

public enum UserRole {

	 // Access to all pages
    ROLE_SUPERUSER(("Superuser")),

    // Page roles
    ROLE_PLANNING(("Planning")),
    ROLE_ACTOR(("Actor")),
    ROLE_ADDRESS(("Address")),
    ROLE_CATEGORY(("Category")),
    ROLE_CITY(("City")),
    ROLE_COUNTRY(("Country")),
    ROLE_CUSTOMER(("Customer")),
    ROLE_FILM(("Film")),
    ROLE_FILM_ACTOR(("Film Actor")),
    ROLE_FILM_CATEGORY(("Film Category")),
    ROLE_FILM_TEXT(("Film Text")),
    ROLE_DETAIL(("Detail")),
    ROLE_INVENTORY_DETAIL(("Inventory Detail")),
    ROLE_INVENTORYG(("Inventory")),
    ROLE_PAYMENT(("Payment")),
    ROLE_RENTAL(("Rental")),
    ROLE_STAFF(("Staff")),
    ROLE_STORE(("Store")),
    ROLE_LANGUAGE(("Language")),
    ROLE_PAY(("Pay")),
    ROLE_READ_ALL_PROJECTS(("Read all projects")),
    ROLE_EDIT_ALL_PROJECTS(("Edit all projects")),
    ROLE_CREATE_PROJECTS(("Create projects")),
    ROLE_TEMPLATES(("Templates"));
    
    
	private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
 
}
