package ${project.packageName};
import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinal.core.JFinal;

public class IndexController extends Controller {
    protected final Logger logger = Logger.getLogger(getClass());

	public  void index(){
		<#if project.viewEngine == "jsp">
		render("/index.jsp");
		<#else>
		render("/index.html");
		</#if>
	}
	
}
