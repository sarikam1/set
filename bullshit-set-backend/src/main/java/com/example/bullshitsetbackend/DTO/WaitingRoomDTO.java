package com.example.bullshitsetbackend.DTO;

import com.example.bullshitsetbackend.domain.Participant;
import com.example.bullshitsetbackend.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WaitingRoomDTO {
    public GameStatus gameStatus;
    public List<Participant> participants;
}
