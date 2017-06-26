package org.apache.jsp.test.excel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class excelImportFrame_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!doctype html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t<title>excelæä»¶å¯¼å¥æµè¯</title>\r\n");
      out.write("    <script type=\"text/javascript\"  src=\"/util/js/jquery-1.4.3.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\t<!--æ£æ¥æä»¶æ¯å¦å­å¨ -->\r\n");
      out.write("\t<!--æ£æ¥æ©å±å -->\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t$(document).ready(function(){\r\n");
      out.write("\t\t\tvar columnString = '[{\"name\":\"svcNum\",\"type\":\"DECIMAL\",\"size\":\"64\",\"nullable\":\"false\"},'\r\n");
      out.write("\t\t\t+'{\"name\":\"prodId\",\"type\":\"DECIMAL\",\"size\":\"10\",\"nullable\":\"false\"},'\r\n");
      out.write("\t\t\t+'{\"name\":\"acceptTime\",\"type\":\"TIMESTAMP\",\"pattern\":\"yyyy/MM/dd HH:mm:ss\",\"size\":\"\",\"nullable\":\"false\"},'\r\n");
      out.write("\t\t\t+'{\"name\":\"chnlType\",\"type\":\"VARCHAR\",\"size\":\"64\",\"nullable\":\"true\"},'\r\n");
      out.write("\t\t\t+'{\"name\":\"agent1\",\"type\":\"VARCHAR\",\"size\":\"64\",\"nullable\":\"true\"},'\r\n");
      out.write("\t\t\t+'{\"name\":\"agent2\",\"type\":\"VARCHAR\",\"size\":\"64\",\"nullable\":\"true\"},'\r\n");
      out.write("\t\t\t+'{\"name\":\"dataSrc\",\"type\":\"DECIMAL\",\"size\":\"1\",\"nullable\":\"false\"}]';\r\n");
      out.write(" \t\t\t$(\"input[name=columnString]\").val(columnString);\r\n");
      out.write("\t\t\talert(columnString);\r\n");
      out.write("\t\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<form action=\"/util/test/excel!upload\" name=\"excelFile\" method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("\t\t<input type=\"file\" name=\"file\"></input>\r\n");
      out.write("\t\t<input type=\"submit\" value=\"æäº¤\"/>\r\n");
      out.write("\t\t<input type=\"hidden\" name =\"columnString\"/>\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
