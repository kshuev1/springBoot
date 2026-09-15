package ru.netology.springBootDemo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MonitoringController {

    private final Counter controllerRequests;
    private final MemoryConsumer memoryConsumer;

    public MonitoringController(
            MeterRegistry meterRegistry,
            MemoryConsumer memoryConsumer
    ) {
        this.memoryConsumer = memoryConsumer;

        this.controllerRequests = Counter.builder("controller_requests_total")
                .description("Total number of calls to monitoring controller")
                .register(meterRegistry);
    }

    @GetMapping("/success")
    public ResponseEntity<String> success() {
        controllerRequests.increment();

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/not-found")
    public ResponseEntity<String> notFound() {
        controllerRequests.increment();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Resource not found");
    }

    @GetMapping("/error")
    public ResponseEntity<String> error() {
        controllerRequests.increment();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error");
    }

    @GetMapping("/metric")
    public ResponseEntity<String> metric() {
        controllerRequests.increment();

        memoryConsumer.consumeMemory();

        return ResponseEntity.ok(
                "Request processed. Allocated blocks: "
                        + memoryConsumer.getAllocatedBlocks()
        );
    }
}