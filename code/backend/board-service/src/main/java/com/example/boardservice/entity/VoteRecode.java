package com.example.boardservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class VoteRecode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_item_id")
    private VoteItem voteItem;


    public VoteRecode(Long userId, Vote vote, VoteItem voteItem) {
        this.userId = userId;
        this.vote = vote;
        this.voteItem = voteItem;
    }
}
