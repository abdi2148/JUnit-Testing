package com.abdi2148.junit5.mockito;

import java.time.LocalDateTime;

public class Order {
    private LocalDateTime creationDate;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

// Simple order class that has just a creation date