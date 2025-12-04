package com.voting.votingapp.request;

import com.voting.votingapp.entity.Poll;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Vote {

    private Long pollId;
    private int optionIndex;

}