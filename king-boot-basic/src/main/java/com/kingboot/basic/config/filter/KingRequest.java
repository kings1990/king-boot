package com.kingboot.basic.config.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

/**
 * <p class="detail">
 * 功能：filter script
 * </p>
 * @author <a href="mailto:wangs@financegt.com">Kings</a>
 * @version V1.0
 * @ClassName: GtRequest
 * @date 2016年2月22日
 */

public class KingRequest extends HttpServletRequestWrapper {
	
	private Map params;
	
	public KingRequest(HttpServletRequest request, Map newParams) {
		super(request);
		this.params = newParams;
	}
	
	public String getParameter(String name) {
		Object v = params.get(name);
		if (v == null) {
			return null;
		} else if (v instanceof String) {
			String value = (String) v;
			value = value.replaceAll("<script>", "&lt;script&gt;");
			value = value.replaceAll("</script>", "&lt;/script&gt;");
			return (String) value;
		} else if (v instanceof String[]) {
			
			String[] strArr = (String[]) v;
			if (strArr.length > 0) {
				String value = strArr[0];
				value = value.replaceAll("<script>", "&lt;script&gt;");
				value = value.replaceAll("</script>", "&lt;/script&gt;");
				return value;
			} else {
				return null;
			}
		} else {
			return v.toString();
		}
	}
	
	public Map getParameterMap() {
		return params;
	}
	
	public Enumeration getParameterNames() {
		Vector l = new Vector(params.keySet());
		return l.elements();
	}
	
	public String[] getParameterValues(String name) {
		Object v = params.get(name);
		if (v == null) {
			return null;
		} else if (v instanceof String) {
			String value = (String) v;
			value = value.replaceAll("<script>", "&lt;script&gt;");
			value = value.replaceAll("</script>", "&lt;/script&gt;");
			return new String[]{(String) value};
			
		} else if (v instanceof String[]) {
			String[] value = (String[]) v;
			for (int i = 0; i < value.length; i++) {
				value[i] = value[i].replaceAll("<script>", "&lt;script&gt;");
				value[i] = value[i].replaceAll("</script>", "&lt;/script&gt;");
			}
			return (String[]) value;
		} else {
			return new String[]{v.toString()};
		}
	}
}