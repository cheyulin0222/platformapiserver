package com.arplanets.platform.log;

import com.arplanets.platform.exception.ErrorType;
import com.arplanets.platform.log.enums.ActionType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Map;

import static com.arplanets.platform.exception.ErrorType.SYSTEM;
import static com.arplanets.platform.log.enums.BasicActionType.CREATE_LOG;

@Slf4j
@RequiredArgsConstructor
public class LoggingService {

    private final LogContext logContext;
    private final ObjectMapper objectMapper;

    public void info(String message, ActionType actionType) {
        log(LogLevel.INFO, message, actionType, null, null);
    }

    public void info(String message, ActionType actionType, Map<String, Object> context) {
        log(LogLevel.INFO, message, actionType, context, null);
    }

    public void error(String message, ActionType actionType, ErrorType errorType) {
        ErrorData errorData = ErrorData.builder()
                .message(message)
                .errorType(errorType)
                .build();

        log(LogLevel.ERROR, message, actionType, null, errorData);
    }

    public void error(String message, ActionType actionType, ErrorType errorType, Map<String, Object> context) {
        ErrorData errorData = ErrorData.builder()
                .message(message)
                .errorType(errorType)
                .build();

        log(LogLevel.ERROR, message, actionType, context, errorData);
    }

    public void error(String message, ActionType actionType, ErrorType errorType, Throwable error) {
        ErrorData errorData = ErrorData.builder()
                .message(message + " | " + error.getMessage())
                .errorType(errorType)
                .stackTrace(Arrays.toString(error.getStackTrace()))
                .build();

        log(LogLevel.ERROR, message, actionType, null, errorData);
    }

    public void error(String message, ActionType actionType, ErrorType errorType, Throwable error, Map<String, Object> context) {
        ErrorData errorData = ErrorData.builder()
                .message(message + " | " + error.getMessage())
                .errorType(errorType)
                .stackTrace(Arrays.toString(error.getStackTrace()))
                .build();

        log(LogLevel.ERROR, message, actionType, context, errorData);
    }

    private void log(LogLevel level, String message, ActionType actionType, Map<String, Object> context, ErrorData errorData) {
        try {
            LogMessage logMessage = LogMessage.builder()
                    .logSn(logContext.getLogSn())
                    .projectId(logContext.getProjectId())
                    .stage(logContext.getActiveProfile())
                    .instanceId(logContext.getInstanceId())
                    .sessionId(logContext.getSessionId())
                    .requestId(logContext.getRequestId())
                    .method(logContext.getMethod())
                    .uri(logContext.getURI())
                    .logLevel(level.name().toLowerCase())
                    .actionType(actionType.getAction())
                    .className(logContext.getClassName())
                    .methodName(logContext.getMethodName())
                    .context(context)
                    .message(message)
                    .errorData(errorData)
                    .version(logContext.getGitVersion())
                    .timestamp(new Timestamp(
                            LocalDateTime.now(ZoneId.of("Asia/Taipei"))
                                    .toInstant(ZoneOffset.ofHours(8))
                                    .toEpochMilli()
                    ))
                    .build();

            String jsonLog = objectMapper.writeValueAsString(logMessage);
            boolean enableMessageLogging = "dev".equals(logContext.getActiveProfile()) || "test".equals(logContext.getActiveProfile());


            switch (level) {
                case INFO -> {
                    if (enableMessageLogging) {
                        log.info("{}", message);
                    }
                    log.info("{}", jsonLog);
                }
                case ERROR -> {
                    if (enableMessageLogging) {
                        log.error("{}", message);
                    }
                    log.error("{}", jsonLog);
                }
                default -> {
                    if (enableMessageLogging) {
                        log.debug("{}", message);
                    }
                    log.debug("{}", jsonLog);
                }
            }
        } catch (JsonProcessingException e) {
            Logger.error("Error creating JSON log", CREATE_LOG, SYSTEM, e);
        }
    }


}
