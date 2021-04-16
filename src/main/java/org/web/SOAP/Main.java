package org.web.SOAP;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.openapi.OpenApiPlugin;
import org.web.SOAP.Controllers.StudentController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config ->{
            //config.addStaticFiles("/publico"); //desde la carpeta de resources
            config.registerPlugin(new RouteOverviewPlugin("/rutas")); //aplicando plugins de las rutas
            config.enableCorsForAllOrigins();

        });

        //El contexto SOAP debe estar creando antes de inicio del servidor.
        new StudentController(app).aplicarRutas();

        //
        app.start(7000);
    }
}
