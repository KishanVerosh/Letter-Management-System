package com.uoc.lms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "letter")
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String senderAddress;

    @Column(columnDefinition = "TEXT")
    private String receiverAddress;

    @Column(unique = true)
    private String referenceNumber;

    private String letterStatus;

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getLetterStatus() {
        return letterStatus;
    }

    public void setLetterStatus(String letterStatus) {
        this.letterStatus = letterStatus;
    }
}
