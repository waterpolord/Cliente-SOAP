package org.web.SOAP.Controllers;

import com.sun.net.httpserver.HttpContext;
import io.javalin.Javalin;
import org.eclipse.jetty.http.spi.HttpSpiContextHandler;
import org.eclipse.jetty.http.spi.JettyHttpContext;
import org.eclipse.jetty.http.spi.JettyHttpServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.web.SOAP.Services.StudentService;
import org.web.SOAP.WebServices.EstudianteWebServices;

import javax.xml.ws.Endpoint;
import java.lang.reflect.Method;


public class StudentController {
    private Javalin app;

    public StudentController(Javalin app) {
        this.app = app;
    }

    public void aplicarRutas() {
        Server server = app.server().server();
        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        server.setHandler(contextHandlerCollection);

        try {
            HttpContext context = build(server, "/ws");

            //El o los servicios que estoy agrupando en ese contexto
            // Para acceder al wsdl en http://localhost:7000/ws/EstudianteWebServices?wsdl
            EstudianteWebServices estudianteWebServices = new EstudianteWebServices();
            Endpoint endpoint = Endpoint.create(estudianteWebServices);
            endpoint.publish(context);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private HttpContext build(Server server, String contextString) throws Exception {
        JettyHttpServer jettyHttpServer = new JettyHttpServer(server, true);
        JettyHttpContext ctx = (JettyHttpContext) jettyHttpServer.createContext(contextString);
        Method method = JettyHttpContext.class.getDeclaredMethod("getJettyContextHandler");
        method.setAccessible(true);
        HttpSpiContextHandler contextHandler = (HttpSpiContextHandler) method.invoke(ctx);
        contextHandler.start();
        return ctx;
    }
}
