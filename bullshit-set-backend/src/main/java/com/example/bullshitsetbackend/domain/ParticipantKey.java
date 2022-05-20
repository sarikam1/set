package com.example.bullshitsetbackend.domain;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipantKey implements Serializable {

    @Column(name = "player_id")
    Long playerId;

    @Column(name = "game_id")
    Long gameId;

    public ParticipantKey(Long playerId, Long gameId) {
        this.playerId = playerId;
        this.gameId = gameId;
    }

    public ParticipantKey() {
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipantKey)) return false;
        ParticipantKey that = (ParticipantKey) o;
        return Objects.equals(getGameId(), that.getGameId()) &&
                Objects.equals(getPlayerId(), that.getPlayerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getPlayerId());
    }
}
