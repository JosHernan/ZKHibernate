package com.mx.Roles;

import org.zkoss.zk.ui.Executions;

public class SecurityUtils {
	  public static boolean isGatheredStatsAlreadySent = false;

	    private SecurityUtils() {
	    }

	    public static boolean isUserInRole(UserRole role) {
	    //	System.out.println(Executions.getCurrent().getRemoteUser());
	    	
	    //	System.out.println(role.name());
	   // 	System.out.println("::::"+Executions.getCurrent().isUserInRole(role.name())+"::::");
	    	
	     //   return Executions.getCurrent().isUserInRole(role.name());
	    	boolean bandera=false;
	    	if(role.name().equals("ROLE_SUPERUSER")) {
	    		bandera=true;
	    	}
	    	
	    	return bandera;
	    
	    }
	    
	    
	  public static boolean isSuperuserOrUserInRoles(UserRole... roles) {
	        if (isUserInRole(UserRole.ROLE_SUPERUSER)) {
	            return true;
	        }

	        for (UserRole role : roles) {
	        	
	            if (isUserInRole(role)) {
	                return true;
	            }
	        }
	        return false;
	    }
	  
	  
	  public static boolean isSuperuserOrRolePlanningOrHasAnyAuthorization() {
	        if (isSuperuserOrUserInRoles(UserRole.ROLE_PLANNING,
	        	      UserRole.ROLE_READ_ALL_PROJECTS,
	        	      UserRole.ROLE_EDIT_ALL_PROJECTS,
	        	      UserRole.ROLE_CREATE_PROJECTS)) {

	        	isGatheredStatsAlreadySent= true;
	        }
			return isGatheredStatsAlreadySent;
	        
	        
	  }
	   
	  
      /*UserRole.ROLE_PLANNING,
      UserRole.ROLE_READ_ALL_PROJECTS,
      UserRole.ROLE_EDIT_ALL_PROJECTS,
      UserRole.ROLE_CREATE_PROJECTS*/
	  
	  
	        
}
