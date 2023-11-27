package com.example.boardservice.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Post post;

    private Long clubId;

    private Long writerId;

    private List<VoteItem> voteItems;

    private List<VoteRecode> voteRecodes;

    private LocalDateTime localDateTime;

    private Boolean isMultipleSelection;

    private Boolean isBlind;

    private boolean isAddItem;



}
