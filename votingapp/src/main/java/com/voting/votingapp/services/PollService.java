package com.voting.votingapp.services;

import com.voting.votingapp.entity.OptionVote;
import com.voting.votingapp.entity.Poll;
import com.voting.votingapp.repositories.PollRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {


    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll) {

        return  pollRepository.save(poll);

    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
        return pollRepository.findById(id);
    }

    public void vote(Long pollId, int optionIndex) {
        //get poll from DB
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(()->new RuntimeException("poll not found"));

        //get all options
        List<OptionVote> options = poll.getOptions();

        //If index for vote is valid, show error
        if(optionIndex < 0 || optionIndex >= options.size()){
            throw new RuntimeException("option index out of bounds");
        }

        //get selected Option
        OptionVote selectedOption = options.get(optionIndex);

        //Increment vote for selected option
        selectedOption.setVoteCount(selectedOption.getVoteCount()+1);

        //save incremented vote option into the DB
        pollRepository.save(poll);
    }
}