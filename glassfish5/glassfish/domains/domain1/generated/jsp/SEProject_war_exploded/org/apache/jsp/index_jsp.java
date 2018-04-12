package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("  <meta charset=\"UTF-8\">\r\n");
      out.write("  <title>Chat</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body style=\"margin:0;\">\r\n");
      out.write("\r\n");

  if (session.getAttribute("user_name") == null) {
      response.sendRedirect("/SEProject_war_exploded/home");
  }

      out.write("\r\n");
      out.write("\r\n");
      out.write("<textarea title=\"Chat Log\" id=\"log\" readonly\r\n");
      out.write("          style=\"display: block; width: 100%; height: 600px; resize: none; margin: 0; padding: 0; border: 0;\"></textarea>\r\n");
      out.write("<input title=\"Chat Input\" id=\"input\" type=\"text\" style=\"display: block; width: 100%; border-width: 1px 0 1px 0;\"\r\n");
      out.write("       autofocus/>\r\n");
      out.write("<script>\r\n");
      out.write("    var ws = new WebSocket(\"ws://localhost:8080/SEProject_war_exploded/ws\");\r\n");
      out.write("    ws.onmessage = function (event) {\r\n");
      out.write("        console.log(event.data);\r\n");
      out.write("        document.getElementById(\"log\").value += \"[\" + timestamp() + \"] \" + event.data + \"\\n\";\r\n");
      out.write("    };\r\n");
      out.write("\r\n");
      out.write("    document.getElementById(\"input\").addEventListener(\"keyup\", function (event) {\r\n");
      out.write("        if (event.keyCode === 13) {\r\n");
      out.write("            ws.send(event.target.value);\r\n");
      out.write("            event.target.value = \"\";\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    function timestamp() {\r\n");
      out.write("        var d = new Date(), minutes = d.getMinutes();\r\n");
      out.write("        if (minutes < 10) minutes = '0' + minutes;\r\n");
      out.write("        return d.getHours() + ':' + minutes;\r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
