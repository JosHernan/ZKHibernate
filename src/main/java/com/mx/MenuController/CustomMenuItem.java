package com.mx.MenuController;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Executions;



public class CustomMenuItem {
	  private final String name;

      private final String unencodedURL;

      private final String encodedURL;

      private final List<CustomMenuItem> children;

      private boolean activeParent;

      private String helpLink;

      private boolean disabled;

      public String getName() {
          return name;
      }

      public String getUrl() {
          return unencodedURL;
      }

      public String getEncodedUrl() {
          return encodedURL;
      }

      public List<CustomMenuItem> getChildren() {
          return children;
      }

      public boolean getHasChildren() {
          return !children.isEmpty();
      }

      public boolean getHasNotChildren() {
          return children.isEmpty();
      }

      public CustomMenuItem(String name, String url) {
          this(name, url, new ArrayList<CustomMenuItem>());
      }

      public CustomMenuItem(String name, String url, String helpLink) {
          this(name, url, new ArrayList<CustomMenuItem>());
          this.helpLink = helpLink;
      }

      public CustomMenuItem(String name, String url, boolean disabled) {
          this(name, url, new ArrayList<CustomMenuItem>());
          this.disabled = disabled;
      }

      public CustomMenuItem(String name, String url, List<CustomMenuItem> children) {
          this.name = name;
          this.unencodedURL = url;
          this.encodedURL = Executions.getCurrent().encodeURL(url);
          this.children = children;
          this.disabled = false;
          this.helpLink = "";
      }

      public void appendChildren(CustomMenuItem newChildren) {
          this.children.add(newChildren);
      }

      public boolean isActiveParent() {
          return activeParent;
      }

      public boolean isDisabled() {
          return disabled;
      }

      public boolean contains(String requestPath) {
          for (CustomMenuItem item : thisAndChildren()) {
              if ( requestContains(requestPath, item.unencodedURL) ) {
                  return true;
              }
          }

          return false;
      }

      private List<CustomMenuItem> thisAndChildren() {
          List<CustomMenuItem> items = new ArrayList<CustomMenuItem>();
          items.add(this);
          items.addAll(children);
          for (CustomMenuItem child : children) {
              items.addAll(child.children);
          }

          return items;
      }

      private static boolean requestContains(String requestPath, String url) {
          return requestPath.startsWith(url);
      }

      public void setActive(boolean activeParent) {
          this.activeParent = activeParent;
      }

      public void setHelpLink(String helpLink) {
          this.helpLink = helpLink;
      }
}
