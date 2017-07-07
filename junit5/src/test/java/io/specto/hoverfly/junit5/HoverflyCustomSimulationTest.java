package io.specto.hoverfly.junit5;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

import io.specto.hoverfly.junit5.api.HoverflySimulate;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@HoverflySimulate(source = PingPongHoverflySimulation.class)
@ExtendWith({HoverflySimulateResolver.class})
public class HoverflyCustomSimulationTest {

    @Test
    public void shouldImportSimulationFromCustomConfiguration() throws IOException {

        final OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder().url("http://www.my-test.com/api/bookings/1")
            .build();

        final Response response = client.newCall(request).execute();

        assertThatJson(response.body().string()).node("bookingId").isEqualTo(1);

    }

}