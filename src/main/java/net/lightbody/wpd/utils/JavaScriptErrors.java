package net.lightbody.wpd.utils;

import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaScriptErrors {
    private List<JavaScriptError> errors = new ArrayList<JavaScriptError>();

    public JavaScriptErrors(JavascriptExecutor driver) {
        @SuppressWarnings("unchecked") List<Map<String, Object>> errors = (List<Map<String, Object>>) driver.executeScript("return window.errors;");
        if (errors == null) {
            return;
        }

        for (Map<String, Object> error : errors) {
            this.errors.add(new JavaScriptError(error));
        }
    }

    public List<JavaScriptError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return errors.toString();
    }

    public static void wire(JavascriptExecutor driver) {
        driver.executeScript("window.onerror = function(m,u,l) { if (!window.errors) { window.errors = new Array(); } window.errors.push({message: m, url: u, line: l}); }");
    }

    public static class JavaScriptError {
        private String message;
        private String url;
        private long lineNumber;

        private JavaScriptError(Map<String, Object> error) {
            message = (String) error.get("message");
            url = (String) error.get("url");
            if (error.containsKey("line")) {
                lineNumber = (Long) error.get("line");
            }
        }

        public String getMessage() {
            return message;
        }

        public String getUrl() {
            return url;
        }

        public long getLineNumber() {
            return lineNumber;
        }

        @Override
        public String toString() {
            return message + " (" + url + ":" + lineNumber + ")";
        }
    }
}
