package org.example.Logic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.Common.Result;
import org.example.Exception.FileProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

@Api(value = "ApiController", tags = "API Controller")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ApiController {

    @ApiOperation(value = "Process uploaded CSV file", notes = "Process an uploaded CSV file and find the minimum integer location and value.")
    @PostMapping(path = "/process", produces = "application/json")
    public ResponseEntity<Result> processFile(@RequestParam("file") MultipartFile file) {
        Instant startTime = Instant.now();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 0;
            int minLineNumber = -1;
            int minValue = Integer.MAX_VALUE;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] parts = line.split(",");
                if (parts.length < 2) continue;
                try {
                    int value = Integer.parseInt(parts[1].trim());
                    if (value < minValue) {
                        minValue = value;
                        minLineNumber = lineNumber;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }

            if (minLineNumber == -1) {
                throw new FileProcessingException("No minimum integer found in the file.");
            }

            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            long processingTimeMs = duration.toMillis();

            Result result = new Result(minLineNumber, minValue);
            result.setProcessingTimeMs(processingTimeMs);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new FileProcessingException("Error processing file: " + e.getMessage(), e);
        }
    }
}
