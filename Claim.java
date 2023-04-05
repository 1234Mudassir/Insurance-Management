package com.example.insurance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "claims")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimNumber;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date claimDate;

    private String claimStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private InsurancePolicy