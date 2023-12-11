package com.example.boardservice.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private Long clubId;

    private Long writerId;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<VoteItem> voteItems;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<VoteRecode> voteRecodes;

    private LocalDateTime localDateTime;

    private Boolean isMultipleSelection;

    private Boolean isBlind;

    private boolean isAddItem;



}
