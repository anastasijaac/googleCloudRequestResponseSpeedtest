package org.example.Common;

public class Result {
    private int lineNumber;
    private int value;
    private long processingTimeMs;

    // Konstruktor mit zwei Parametern
    public Result(int lineNumber, int value) {
        this.lineNumber = lineNumber;
        this.value = value;
    }

    // Standardkonstruktor
    public Result() {
        // Wird benötigt, falls du Objekte ohne Parameter erstellen möchtest oder für Frameworks, die Reflexion verwenden
    }


    // Getter und Setter für alle Felder
    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getProcessingTimeMs() {
        return processingTimeMs;
    }

    public void setProcessingTimeMs(long processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }

}