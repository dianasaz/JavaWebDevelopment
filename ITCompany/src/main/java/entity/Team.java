package entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TeamService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Team {
    private final Logger logger = LogManager.getLogger(Team.class);
    private List<Employee> team;

    Team(){}

    public Team(List<Employee> team) {
        this.team = team;
    }

    public List<Employee> getTeam() {
        return team;
    }

    public void setTeam(List<Employee> team) {
        if (team != null)
        this.team = team;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team1 = (Team) o;
        return Objects.equals(team, team1.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }

    @Override
    public String toString() {
        return "Team{" +
                "team=" + team +
                '}';
    }

    public int size(){
        return team.size();
    }
}
