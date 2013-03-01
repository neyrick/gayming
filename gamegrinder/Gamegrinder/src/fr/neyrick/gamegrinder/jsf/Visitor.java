package fr.neyrick.gamegrinder.jsf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class Visitor implements Serializable {

	private static final String USER_COOKIE = "gamegrinder_user";
	
	private String name;
	
	@Inject
	private Instance<FacesContext> facesContextInstance;
	
	@PostConstruct
	private void init() {
		Cookie cookie = (Cookie)facesContextInstance.get().getExternalContext().getRequestCookieMap().get(USER_COOKIE);
		if (cookie != null) {
			this.name = cookie.getValue();
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		Map<String, Object> cookieProps = new HashMap<String, Object>();
		cookieProps.put("maxAge", new Integer(3600*24*100));
		facesContextInstance.get().getExternalContext().addResponseCookie(USER_COOKIE, name, cookieProps);
	}
	
	public boolean isKnown() {
		return (name != null);
	}

	public String clear() {
		this.name = null;
		return null;
	}
	
	public Visitor() {
	}

}
