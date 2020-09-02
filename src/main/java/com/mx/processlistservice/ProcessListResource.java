import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.common.io.CharStreams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Path("/process-list")
@Produces(MediaType.APPLICATION_JSON)
public class ProcessListResource {

    private final int maxLength;
    private final AtomicLong counter;

    Logger log = LoggerFactory.getLogger(ProcessListResource.class);

    public ProcessListResource(int maxLength) {
        this.maxLength = maxLength;
        this.counter = new AtomicLong();
    }

    @SuppressWarnings("RestParamTypeInspection")
    @GET
    @Timed
    public List<Proc> listProcesses(@QueryParam("contains") Optional<String> contains) {
        List<Proc> processes = new ArrayList<Proc>();
        String query = contains.or("");

        try {
         // Get processes from terminal
            Process p = Runtime.getRuntime().exec("ps -e");
            BufferedReader input =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
            List<String> lines = CharStreams.readLines(input);

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);

                if (line.contains(query)) {
                    processes.add(new Proc(counter.getAndIncrement(), line.substring(0,
                        Math.min(line.length(), maxLength))));
                }
            }

            input.close();
        } catch (Exception e) {
            log.error("exception in listTasks method", e);
        }
        return processes;
    }
}
