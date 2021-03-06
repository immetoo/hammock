/*
 * Copyright 2016 Hammock and its contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ws.ament.hammock.web.tck;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import ws.ament.hammock.HammockRuntime;
import ws.ament.hammock.web.spi.StartWebServer;
import ws.ament.hammock.web.spi.WebServerConfiguration;

import java.io.File;
import javax.servlet.http.HttpServletResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Arquillian.class)
public abstract class FilterTest {
    public static JavaArchive createArchive(Class<?>...classes) {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(DefaultFilter.class, HammockRuntime.class, MessageProvider.class, WebServerConfiguration.class, StartWebServer.class)
                .addClasses(classes)
                .addAsManifestResource(new FileAsset(new File("src/main/resources/META-INF/beans.xml")), "beans.xml");
    }

    @Test
    public void shouldBootWebServerWithOnlyFilter() throws Exception {
        given()
            .baseUri("http://localhost:8080/")
        .expect()
            .statusCode(HttpServletResponse.SC_OK)
            .body(is("Hello, world!"))
        .when()
            .get("/");
    }

    @Test
    public void shouldBootWebServerWithOnlyFilterHttps() throws Exception {
        given()
            .baseUri("https://localhost:8443/")
            .relaxedHTTPSValidation()
        .expect()
            .statusCode(HttpServletResponse.SC_OK)
            .body(is("Hello, world!"))
        .when()
            .get("/");
    }
}
