package com.gl.training.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class OSUtils {

    private static final Logger log = LogManager.getLogger(OSUtils.class);

    private static boolean runCommand(@NotNull String... cmds) throws IOException, InterruptedException {
        if (log.isDebugEnabled()) {
            log.debug("Running command: '{}'", Arrays.stream(cmds).collect(Collectors.joining(" ")));
        }
        return Runtime.getRuntime().exec(cmds).waitFor() == 0;
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    public static void killProcess(@NotNull String name) throws IllegalStateException {
        if (!isWindows()) {
            throw new IllegalStateException("Method is not implemented for this OS");
        }
        try {
            runCommand("taskkill", "/F", "/T", "/IM", name);
        } catch (IOException | InterruptedException e) {
            log.trace(e.getMessage(), e);
        }
    }
}
